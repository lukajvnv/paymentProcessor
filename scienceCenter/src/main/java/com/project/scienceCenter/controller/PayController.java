package com.project.scienceCenter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.scienceCenter.dto.PaymentRequestDTO;
import com.project.scienceCenter.dto.PaymentResponseDTO;
import com.project.scienceCenter.dto.PaymentTypeRequestDTO;
import com.project.scienceCenter.dto.PaymentTypeResponseDTO;

@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		return new ResponseEntity<>(new String("Okej NC radi poziv KP kako treba"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/paymentTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentTypeResponseDTO> cardHandlerPost(@RequestBody PaymentTypeRequestDTO request) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PaymentTypeResponseDTO> response = restTemplate.postForEntity("https://localhost:8762/requestHandler/request/paymentTypes", request, PaymentTypeResponseDTO.class);
		
		return new ResponseEntity<PaymentTypeResponseDTO>(response.getBody(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/buy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponseDTO> cardHandlerPost(@RequestBody PaymentRequestDTO request) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PaymentResponseDTO> response = restTemplate.postForEntity(request.getUrl(), request, PaymentResponseDTO.class);
		
		return new ResponseEntity<PaymentResponseDTO>(response.getBody(), HttpStatus.OK);
	}

}
