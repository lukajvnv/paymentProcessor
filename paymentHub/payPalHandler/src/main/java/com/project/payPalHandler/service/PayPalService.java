package com.project.payPalHandler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.project.payPalHandler.dto.PaymentRequestDTO;
import com.project.payPalHandler.dto.PaymentResponseDTO;
import com.project.payPalHandler.model.DbTransaction;
import com.project.payPalHandler.model.Seller;
import com.project.payPalHandler.repository.ISellerRepository;
import com.project.payPalHandler.repository.ITransactionRepository;
import com.project.payPalHandler.repository.ITransactionRepository;

@Service
public class PayPalService {

	private ITransactionRepository _transactionRepository;
	private ISellerRepository _sellerRepository;
	
	public static final String clientID = "AcI4uBHVrHz3OFAt5OTzoBcz68-gU6rR4x0UM6GfgFLEufIwsfYBvjeEMDL2tz6eT0BiXSiO--BTcf_Z";
	public static final String clientSecret = "EOR-cyCwpmFHd2Ut53JKCGFE6s7eehXdja-fEQuH1qsbtqBI5YdSsJyQHResoghimdINQbKOlzXFI3JZ";
	
	public PayPalService(ITransactionRepository transactionRepository, ISellerRepository sellerRepository) {
		_transactionRepository = transactionRepository;
		_sellerRepository = sellerRepository;
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

            dbTransaction.setSuccessful(true);
            _transactionRepository.save(dbTransaction);

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return dbTransaction.getRedirectUrl();
	}
	
	public String cancelledPayment(Long id) {
        DbTransaction dbTransaction = _transactionRepository.getOne(id);
        dbTransaction.setSuccessful(false);
        _transactionRepository.save(dbTransaction);

        return dbTransaction.getRedirectUrl();
    }
}

