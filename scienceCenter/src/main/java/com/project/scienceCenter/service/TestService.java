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
		RestTemplate restTemplate = new RestTemplate();

		String[] paths = new String[] {"", "/card", "/bitcoin", "/payPal"};
		
		
		for(String path : paths) {
			ResponseEntity<String> res =  restTemplate.getForEntity("https://localhost:8762/requestHandler/test" + path, String.class);
			System.out.println("#####################################33");
			System.out.println(res.getBody());
			System.out.println("#####################################33");

		}
		
		return "RESI";
	}
	
	public String callPaymentHubServices() {
		RestTemplate restTemplate = new RestTemplate();

		String[] paths = new String[] {"", "/card", "/bitcoin", "/payPal"};
		
		
		for(String path : paths) {
			ResponseEntity<String> res =  restTemplate.getForEntity("https://localhost:8762/requestHandler/service" + path, String.class);
			System.out.println("#####################################33");
			System.out.println(res.getBody());
			System.out.println("#####################################33");
		}
		
		return "RESI";
	}

}
