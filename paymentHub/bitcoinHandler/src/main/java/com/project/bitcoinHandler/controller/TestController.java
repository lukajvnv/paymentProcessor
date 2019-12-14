package com.project.bitcoinHandler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.bitcoinHandler.Proba;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej bitCoin radi"), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testPost(@RequestBody Proba proba) {
		
		
		return new ResponseEntity<>(new String("Okej bitCoin post radi"), HttpStatus.OK);
	}
	
	@RequestMapping( path="/service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testService() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8842/test", String.class);
		
		return new ResponseEntity<>(new String("Okej bitCoinService get radi"), HttpStatus.OK);
	}
	
	@RequestMapping( path="/service", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testServicePost(@RequestBody Proba proba) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8842/test", proba,  String.class);

		return new ResponseEntity<>(new String("Okej bitCoinService post radi"), HttpStatus.OK);
	}
	
}
