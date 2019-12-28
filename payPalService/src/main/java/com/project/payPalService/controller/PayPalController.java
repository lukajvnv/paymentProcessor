package com.project.payPalService.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Payment;
import com.project.payPalService.dto.TestDTO;
import com.project.payPalService.service.PayPalService;

@RestController
@RequestMapping()
public class PayPalController {

	
	private final PayPalService _ppservice;
	
	public PayPalController(PayPalService ppService) {
		_ppservice = ppService;
	}
	
	@PostMapping
	public void transaction(@RequestBody TestDTO request) {
		 _ppservice.startTransaction(request);
	}
	
	
	
}
