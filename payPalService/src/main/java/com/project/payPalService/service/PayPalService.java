package com.project.payPalService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.project.payPalService.dto.TestDTO;
import com.project.payPalService.model.Seller;

@Service
public class PayPalService {

	public static final String clientID = "AcI4uBHVrHz3OFAt5OTzoBcz68-gU6rR4x0UM6GfgFLEufIwsfYBvjeEMDL2tz6eT0BiXSiO--BTcf_Z";
	public static final String clientSecret = "EOR-cyCwpmFHd2Ut53JKCGFE6s7eehXdja-fEQuH1qsbtqBI5YdSsJyQHResoghimdINQbKOlzXFI3JZ";
	
	public void startTransaction(TestDTO testDTO) {
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal("25.00");

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("https://example.com/cancel");
		redirectUrls.setReturnUrl("https://localhost:4200/view-magazine");
		payment.setRedirectUrls(redirectUrls);
		
//		Client client = new Client();
		Payment createdPayment = null;
		
		try {
		    APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");
		    createdPayment = payment.create(apiContext);
		    

		    // For debug purposes only: System.out.println(createdPayment.toString());
		} catch (PayPalRESTException e) {
		    // Handle errors
		} catch (Exception ex) {
		    // Handle errors
		}	
	}
	
	public void executeTransaction( ) {
		
	}
}
