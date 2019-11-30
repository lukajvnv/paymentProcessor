package com.project.scienceCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String callPaymentHub() {
		ResponseEntity<String> response =  restTemplate.getForEntity("http://localhost:8762/requestHandler/test", String.class);
		return response.getBody();
	}

}
