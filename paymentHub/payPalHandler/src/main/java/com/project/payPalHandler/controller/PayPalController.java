package com.project.payPalHandler.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.project.payPalHandler.service.PayPalService;

@RestController
@RequestMapping("/payPal")
public class PayPalController {

	
	private final PayPalService _ppservice;
	
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
	public void getApprovedPayment(@RequestParam String paymentId, @RequestParam String token, @RequestParam String PayerID, HttpServletResponse response) {
		String redirectUrl = _ppservice.executeTransaction(paymentId, token, PayerID);

        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@GetMapping("/cancelledPayment/{id}")
	public void getCancelledPayment(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String redirectUrl = _ppservice.cancelledPayment(id);
        response.sendRedirect(redirectUrl);
    }
	
	@PostMapping("/subscription")
    public PaymentResponseDTO subscription(@RequestBody SubscriptionRequestDto subscriptionRequest) throws MalformedURLException, UnsupportedEncodingException, PayPalRESTException {
        return _ppservice.startSubscription(subscriptionRequest);
    }
	
	@GetMapping("/subscription")
    public void getProcessPayment(@RequestParam String token) throws PayPalRESTException {
        _ppservice.executeSubscription(token);
    }
	
	
}
