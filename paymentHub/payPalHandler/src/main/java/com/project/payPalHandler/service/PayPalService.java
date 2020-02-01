package com.project.payPalHandler.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.project.payPalHandler.dto.CreatePaymentResponseDTO;
import com.project.payPalHandler.dto.PaymentRequestDTO;
import com.project.payPalHandler.dto.PaymentResponseDTO;
import com.project.payPalHandler.dto.SubscriptionRequestDto;
import com.project.payPalHandler.model.DbTransaction;
import com.project.payPalHandler.model.Seller;
import com.project.payPalHandler.model.Subscription;
import com.project.payPalHandler.repository.ISellerRepository;
import com.project.payPalHandler.repository.ISubscriptionRepository;
import com.project.payPalHandler.repository.ITransactionRepository;
import com.project.payPalHandler.util.PaymentStatus;
import com.project.payPalHandler.repository.ITransactionRepository;

@Service
public class PayPalService {

	private ITransactionRepository _transactionRepository;
	private ISellerRepository _sellerRepository;
	private ISubscriptionRepository _subscriptionRepository;
	
	public static final String clientID = "AcI4uBHVrHz3OFAt5OTzoBcz68-gU6rR4x0UM6GfgFLEufIwsfYBvjeEMDL2tz6eT0BiXSiO--BTcf_Z";
	public static final String clientSecret = "EOR-cyCwpmFHd2Ut53JKCGFE6s7eehXdja-fEQuH1qsbtqBI5YdSsJyQHResoghimdINQbKOlzXFI3JZ";
	
	public PayPalService(ITransactionRepository transactionRepository, ISellerRepository sellerRepository, ISubscriptionRepository subscriptionRepository) {
		_transactionRepository = transactionRepository;
		_sellerRepository = sellerRepository;
		_subscriptionRepository = subscriptionRepository;
	}
	
	public PaymentResponseDTO startTransaction(PaymentRequestDTO request) {
		
		DbTransaction savedTransaction = new DbTransaction();
		savedTransaction = _transactionRepository.save(savedTransaction);
		
		Seller seller = _sellerRepository.findBySellerId(request.getSellerId());
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(request.getAmount().toString());
		
		Payee payee = new Payee();
        payee.setEmail(seller.getEmail());

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setPayee(payee);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		
		payment.setIntent("authorize");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("https://localhost:8765/payPal/cancelledPayment/" + savedTransaction.getId());
		redirectUrls.setReturnUrl("https://localhost:8765/payPal/approvedPayment");
		payment.setRedirectUrls(redirectUrls);
		
		Payment createdPayment = null;
		
	    APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");
		
		try {
		    createdPayment = payment.create(apiContext);
		    

		    // For debug purposes only: System.out.println(createdPayment.toString());
		} catch (PayPalRESTException e) {
		    // Handle errors
		} catch (Exception ex) {
		    // Handle errors
		}
		
		savedTransaction.setAmount(request.getAmount().toString());
		savedTransaction.setCurrency(amount.getCurrency());
		savedTransaction.setSeller(seller);
		savedTransaction.setPaymentId(createdPayment.getId());
		savedTransaction.setPaymentStatus(PaymentStatus.IN_PROGRESS);
		savedTransaction = _transactionRepository.save(savedTransaction);
			
		PaymentResponseDTO response = new PaymentResponseDTO();
		response.setPaymentId(savedTransaction.getId());
		response.setPaymentUrl(createdPayment.getLinks().get(1).getHref());
		return response;
	}
	
	public String executeTransaction(String paymentId, String token, String payerId) {

		DbTransaction dbTransaction = _transactionRepository.findByPaymentId(paymentId);
	
        APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        try {
            Payment executedPayment = payment.execute(apiContext, paymentExecution);

            Authorization authorization = executedPayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();

            Amount amount = new Amount();
            amount.setTotal(dbTransaction.getAmount());
            amount.setCurrency(dbTransaction.getCurrency());

            Capture capture = new Capture();
            capture.setAmount(amount);

            capture.setIsFinalCapture(true);

            authorization.capture(apiContext, capture);

            dbTransaction.setPaymentStatus(PaymentStatus.COMPLETED);

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return dbTransaction.getRedirectUrl();
	}
	
	public String cancelledPayment(Long id) {
        DbTransaction dbTransaction = _transactionRepository.getOne(id);
        dbTransaction.setPaymentStatus(PaymentStatus.CANCELED);
        _transactionRepository.save(dbTransaction);

        return dbTransaction.getRedirectUrl();
    }
	
	public PaymentResponseDTO startSubscription (SubscriptionRequestDto request) throws PayPalRESTException, MalformedURLException, UnsupportedEncodingException {
		Plan plan = new Plan(request.getSubject(),"Template creation.", "fixed");
		
		PaymentDefinition paymentDefinition = new PaymentDefinition();
        paymentDefinition.setName("Regular payments.");
        paymentDefinition.setType("REGULAR");
        paymentDefinition.setFrequency(request.getFrequency().toUpperCase());
        paymentDefinition.setFrequencyInterval(request.getInterval());
        paymentDefinition.setCycles(request.getCycles());
        
        Currency amount = new Currency();
        amount.setCurrency("USD");
        amount.setValue(String.valueOf(request.getAmount()));
        paymentDefinition.setAmount(amount);

        List<PaymentDefinition> paymentDefinitions=new ArrayList<>();
        paymentDefinitions.add(paymentDefinition);

        plan.setPaymentDefinitions(paymentDefinitions);
        
        MerchantPreferences merchantPreferences = new MerchantPreferences();

        merchantPreferences.setSetupFee(amount);
        merchantPreferences.setCancelUrl("https://localhost:8765/payPal/cancelledPayment/" + plan.getId());
        merchantPreferences.setReturnUrl("https://localhost:8765/payPal/subscription");
        merchantPreferences.setMaxFailAttempts("0");
        merchantPreferences.setAutoBillAmount("YES");
        merchantPreferences.setInitialFailAmountAction("Continue");
        
        plan.setMerchantPreferences(merchantPreferences);
        
		APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");
		
		Plan createdPlan = plan.create(apiContext);
		
		List<Patch> patches = new ArrayList<>();
		
		patches.add(patch(Collections.singletonMap("state", "ACTIVE")));
		
		createdPlan.update(apiContext, patches);
		String planId = createdPlan.getId();
		
		Agreement agreement = new Agreement();
		agreement.setName("TEST Subscription agreement");
		agreement.setDescription("Testing subscription agreement implementation");
//		agreement.setStartDate(LocalDate.now().plusDays(1L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")).replace(" ", "T") + "Z");
		agreement.setStartDate("2020-02-02T23:23:23Z");
//		agreement.setStartDate(LocalDate.now().plusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
		
		Plan planAgreement = new Plan();
		planAgreement.setId(planId);
        agreement.setPlan(planAgreement);
        
        Payer payer = new Payer();

        payer.setPaymentMethod("paypal");

        agreement.setPayer(payer);

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setLine1("Kraljevica Marka 3");
        shippingAddress.setCity("Novi Sad");
        shippingAddress.setState("Serbia");
        shippingAddress.setPostalCode("21000");
        shippingAddress.setCountryCode("RS");

        agreement.setShippingAddress(shippingAddress);
        
        Agreement createdAgreement = agreement.create(apiContext);
        
        Seller seller = _sellerRepository.findBySellerId(request.getSellerId());
        
        Subscription subscription = new Subscription();
        
        subscription.setAmount(request.getAmount());
        subscription.setCycles(request.getCycles());
        subscription.setFrequency(request.getFrequency());
        subscription.setFrequencyInterval(request.getInterval());
        subscription.setPlanId(planId);
        subscription.setRedirectUrl(request.getRedirectUrl());
        subscription.setSubject(request.getSubject());
        subscription.setSeller(seller);
        
        URL url = getApprovalUrl(createdAgreement.getLinks());
        
        String agreementToken = parseAgreementToken(url);
        
        subscription.setToken(agreementToken);
        
        subscription = _subscriptionRepository.save(subscription);
        
        PaymentResponseDTO response = new PaymentResponseDTO();
		response.setPaymentId(subscription.getId());
		response.setPaymentUrl(String.valueOf(url));
        
		return response;
	}
	 
	 public static Patch patch(Map<String, String> value) {
	        Patch patch = new Patch();

	        patch.setOp("replace");
	        patch.setPath("/");
	        patch.setValue(value);

	        return patch;
	    }

	public void executeSubscription(String token) throws PayPalRESTException {
	        Subscription subscription = _subscriptionRepository.findByToken(token);
	        _subscriptionRepository.save(subscription);
	        APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");


	        Agreement activatedAgreement = Agreement.execute(apiContext, token);

	 }
	
	public static URL getApprovalUrl(List<Links> links) throws MalformedURLException {
        URL url = null;

        for (Links link : links) {
            if ("approval_url".equalsIgnoreCase(link.getRel())) {
                url = new URL(link.getHref());
                break;
            }
        }

        return url;
    }
	
	public static String parseAgreementToken(URL url) {
        String retVal = "";
        String token = url.getQuery().split("&")[1];

        String[] tokenParam = token.split("=");

        if (tokenParam[0].equalsIgnoreCase("token")) {
            retVal = tokenParam[1];
        }

        return retVal;
    }
}

