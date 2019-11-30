package com.project.scienceCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.scienceCenter.service.TestService;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		System.out.println(testService.callPaymentHub());
		return new ResponseEntity<>(new String("Okej NC radi"), HttpStatus.OK);
	}
	
}
