package com.project.payPalHandler.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.base.rest.PayPalRESTException;
import com.project.payPalHandler.dto.PaymentRequestDTO;
import com.project.payPalHandler.dto.PaymentResponseDTO;
import com.project.payPalHandler.dto.SubscriptionRequestDto;
import com.project.payPalHandler.dto.TxInfoDto;
import com.project.payPalHandler.dto.TxStatus;
import com.project.payPalHandler.service.PayPalService;

@RestController
@RequestMapping("/payPal")
public class PayPalController {

	
	private final PayPalService _ppservice;
	
	private static final String EVERY_10_MINUTES = "* 0/10 * * * ?";
	
	@Autowired
	public PayPalController(PayPalService ppService) {
		_ppservice = ppService;
	}
	
	@PostMapping
	public PaymentResponseDTO transaction(@RequestBody PaymentRequestDTO request) {
		return _ppservice.startTransaction(request);
	}
	
	@PostMapping("/execute")
	public ResponseEntity execute(@RequestBody PaymentRequestDTO request) {
		try {
			_ppservice.startTransaction(request);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/approvedPayment")
	public ResponseEntity<?> getApprovedPayment(@RequestParam String paymentId, @RequestParam String token, @RequestParam String PayerID, HttpServletResponse response) {
		String redirectUrl = _ppservice.executeTransaction(paymentId, token, PayerID);

        try {
            response.sendRedirect(redirectUrl);
    		return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", redirectUrl).build();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
	}
	
	@GetMapping("/cancelledPayment/{id}")
	public void getCancelledPayment(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String redirectUrl = _ppservice.cancelledPayment(id);
        response.sendRedirect(redirectUrl);
    }
	
	@GetMapping("/cancelledSubscritpion/{id}")
	public void getCancelledSubscription(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String redirectUrl = _ppservice.cancelledSubscription(id);
        response.sendRedirect(redirectUrl);
    }
	
	@PostMapping("/subscription")
    public PaymentResponseDTO subscription(@RequestBody SubscriptionRequestDto subscriptionRequest) throws MalformedURLException, UnsupportedEncodingException, PayPalRESTException {
        return _ppservice.startSubscription(subscriptionRequest);
    }
	
	@GetMapping("/execute-subscription")
    public void getProcessPayment(@RequestParam String token) throws PayPalRESTException {
        _ppservice.executeSubscription(token);
    }
	
	@PostMapping(path="/checkTx")
	public ResponseEntity<TxInfoDto> checkTx(@RequestBody TxInfoDto request ) {
		request.setStatus(TxStatus.SUCCESS);
		return new ResponseEntity<TxInfoDto>(request, HttpStatus.OK);
	}
	
	@Scheduled(cron = EVERY_10_MINUTES)
	public void changeStatusPayment() throws PayPalRESTException {
		_ppservice.checkPaymentStatus();
	}
	
	@Scheduled(cron = EVERY_10_MINUTES)
	public void changeSubscriptionStatus() throws PayPalRESTException {
		_ppservice.checkSubscriptionStatus();
	}
}
