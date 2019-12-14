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
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej paymentRequest radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandler() {
		
		 RestTemplate restTemplate = new RestTemplate();
		//zuul se gubi: Caused by: javax.net.ssl.SSLPeerUnverifiedException: Certificate for <192.168.1.3> doesn't match any of the subject alternative names: []
		// a kad se inijektuje restTemplate bean onda: no instances available for localhost
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/cardPaymentHandler/test", String.class);
		
		 //neinjektovan template pokusaj na servis bez zuul: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target: ovo sad radi
		//injektovan java.lang.IllegalStateException: No instances available for localhost
//		ResponseEntity<String> response1 =  restTemplate.getForEntity("https://localhost:8763/test", String.class);
		
		
		
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card handler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/card", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/cardPaymentHandler/test", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card handler post radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/bitcoin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> bitcoinHandler() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/bitcoinHandler/test", String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv bitcoin handler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/bitcoin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> bitcoinHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/bitcoinHandler/test", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv bitcoin handler post radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/payPal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> payPalHandler() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8762/payPalHandler/test", String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv payPal handler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/payPal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> payPalHandlerPost(@RequestBody Proba proba) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8762/payPalHandler/test", proba, String.class);
		return new ResponseEntity<>(new String("Okej paymentRequest poziv payPal handler post radi"), HttpStatus.OK);
	}
	
}
