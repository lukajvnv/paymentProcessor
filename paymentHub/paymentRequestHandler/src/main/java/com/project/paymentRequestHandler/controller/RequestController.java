package com.project.paymentRequestHandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.paymentRequestHandler.dto.PaymentTypeRequestDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;
import com.project.paymentRequestHandler.service.RequestService;

@RestController
@RequestMapping("/request")
@CrossOrigin
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@RequestMapping(path = "/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandler() {
		
		
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card handler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/paymentTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentTypeResponseDTO> cardHandlerPost(@RequestBody PaymentTypeRequestDTO request) {
		
		PaymentTypeResponseDTO response = requestService.getSupportedPaymentTypes(request);
		
		return new ResponseEntity<PaymentTypeResponseDTO>(response, HttpStatus.OK);
	}
	
	

}
