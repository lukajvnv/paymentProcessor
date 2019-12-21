package com.project.bitcoinService.controller;


import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bitcoinService.dto.BitCoinRequestDTO;
import com.project.bitcoinService.dto.BitCoinResponseDTO;
import com.project.bitcoinService.dto.TestDTO;

import java.io.IOException;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping("/bitCoin")
public class BitCoinController {

	private Logger logger = LoggerFactory.getLogger(BitCoinController.class);
	
	@RequestMapping(path="/initPaymentGet", method = RequestMethod.GET)//, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() { //umesto test bice zahtev za placanje
		logger.info("Init payment");
		//System.out.println("DTO: " + btcDTO.getPrice_currency());
		String pom = "";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "ab7fzPdN49-xBVoY_LdSifCZiVrqCbdcfjWdweJS");
		ResponseEntity<String> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders", HttpMethod.GET,
				new HttpEntity<String>(pom, headers), String.class);

		//restTemplate.getForEntity("https://google.rs", String.class);
		//ResponseEntity<String> response = restTemplate.getForEntity("https://api-sandbox.coingate.com/v2/orders", String.class);
		//ResponseEntity<String> response =  restTemplate.postForEntity("https://api.coingate.com/v2/orders", btcDTO, String.class);
		
		return new ResponseEntity<Object>(responseEntity,HttpStatus.OK);
	}
	
	/**
	 * Metoda koja gadja Create Order na coingate-u
	 * */
	@RequestMapping(path="/createOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createOrder(@RequestBody BitCoinRequestDTO btcDTO) { 
		logger.info("Init payment");
		
		String authToken = "Bearer ab7fzPdN49-xBVoY_LdSifCZiVrqCbdcfjWdweJS";
		
		
		//RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.getForEntity("https://google.rs", String.class);
		//restTemplate.getForEntity("https://api.coingate.com/v2/ping", String.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authToken);	   
		
		ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders", HttpMethod.POST,
				new HttpEntity<Object>(btcDTO, headers), Object.class);
		
		logger.info(responseEntity.getBody().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		BitCoinResponseDTO btcResponse = new BitCoinResponseDTO();

		btcResponse = mapper.convertValue(responseEntity.getBody(), BitCoinResponseDTO.class);
		
		String paymentUrl = btcResponse.getPayment_url();
		
		logger.info(paymentUrl);
		
		//BitCoinResponseDTO response = parser.parseList(responseEntity.getBody().toString());
		
		//BitCoinResponseDTO responseDTO =  responseEntity.;
		
		//String pageToRedirec = responseDTO.getPayment_url();
		
		//restTemplate.exchange
		//ResponseEntity<String> response =  restTemplate.postForEntity("https://api-sandbox.coingate.com/v2/orders", btcDTO, String.class);
		
		return new ResponseEntity<Object>(responseEntity,HttpStatus.OK);
	}
}
