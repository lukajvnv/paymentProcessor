package com.project.cardPaymentHandler.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardPaymentHandler.dto.PaymentRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);

	
	@RequestMapping(path="/pay",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> pay() {
		
		
		return new ResponseEntity<>(new String("Okej cardHandler get radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path="/pay",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentValidationResponseDTO> payPost(@RequestBody PaymentRequestDTO request) {
		logger.info("Pay initialized");
		PaymentValidationResponseDTO response = new PaymentValidationResponseDTO();
		try {
			response =  cardService.pay(request);
			logger.info("Pay initialized ended successfully");
			
			return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.error("Pay initialized ended with errors");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path="/saveTx",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tx> saveTx(@RequestBody Tx request) {
		logger.info("saved tx");
		request = cardService.saveTx(request);
	
		return new ResponseEntity<>(new Tx(), HttpStatus.OK);
	}
}