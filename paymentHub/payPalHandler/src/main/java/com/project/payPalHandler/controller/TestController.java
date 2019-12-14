package com.project.payPalHandler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.payPalHandler.Proba;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej paypalHandler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testPost(@RequestBody Proba proba) {
		
		
		return new ResponseEntity<>(new String("Okej paypalHandler post radi"), HttpStatus.OK);
	}
	
	@RequestMapping( path="/service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testService() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8843/test", String.class);
		
		return new ResponseEntity<>(new String("Okej payPalService get radi"), HttpStatus.OK);
	}
	
	@RequestMapping( path="/service", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testServicePost(@RequestBody Proba proba) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response =  restTemplate.postForEntity("https://localhost:8843/test", proba,  String.class);

		return new ResponseEntity<>(new String("Okej payPalService post radi"), HttpStatus.OK);
	}
	
}
