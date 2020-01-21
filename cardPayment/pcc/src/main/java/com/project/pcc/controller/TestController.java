package com.project.pcc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.pcc.dto.Proba;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping
	public ResponseEntity<String> testCall(){
		return new ResponseEntity<String>("Dobrodosli u PCC", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Proba> testCallProba(@RequestBody Proba request){
		return new ResponseEntity<Proba>(request, HttpStatus.OK);
	}
	
	@PostMapping(path = "/callOtherBank")
	public ResponseEntity<Proba> testCallOtherBankProba(@RequestBody Proba request){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Proba> response = null;
		try {
			response = restTemplate.postForEntity("https://localhost:8851/test/callFromPcc", request, Proba.class);
		} catch (RestClientException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		 return new ResponseEntity<Proba>(response.getBody(), HttpStatus.OK);
	}

}
