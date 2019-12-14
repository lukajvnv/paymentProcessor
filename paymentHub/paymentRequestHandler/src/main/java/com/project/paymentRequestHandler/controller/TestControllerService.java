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
import org.springframework.web.client.RestTemplate;

import com.project.paymentRequestHandler.Proba;

@RestController
@RequestMapping("/service")
@CrossOrigin
public class TestControllerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej paymentRequest serivce radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandler() {
		
		 RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/cardPaymentHandler/test/service", String.class);
		
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card serivce radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/card", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/cardPaymentHandler/test/service", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card serivce post radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/bitcoin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> bitcoinHandler() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/bitcoinHandler/test/service", String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv bitcoin serivce radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/bitcoin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> bitcoinHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/bitcoinHandler/test/service", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv bitcoin serivce post radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/payPal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> payPalHandler() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/payPalHandler/test/service", String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv payPal serivce radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/payPal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> payPalHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/payPalHandler/test/service", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv payPal serivce post radi"), HttpStatus.OK);
	}
	
}
