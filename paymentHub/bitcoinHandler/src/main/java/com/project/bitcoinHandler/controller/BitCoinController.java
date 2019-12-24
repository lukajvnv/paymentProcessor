package com.project.bitcoinHandler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bitcoinHandler.dto.CreateOrderRequestDTO;
import com.project.bitcoinHandler.dto.GetOrderResponseDTO;
import com.project.bitcoinHandler.dto.PaymentRequestDTO;
import com.project.bitcoinHandler.dto.PaymentResponseDTO;
import com.project.bitcoinHandler.dto.BitCoinResponseDTO;
import com.project.bitcoinHandler.dto.CheckoutResponseDTO;
import com.project.bitcoinHandler.model.SellerBitcoinInfo;
import com.project.bitcoinHandler.model.Tx;
import com.project.bitcoinHandler.repository.BitcoinRepository;
import com.project.bitcoinHandler.repository.TxRepository;
import com.project.bitcoinHandler.util.TxStatus;

import java.io.IOException;
import java.util.Date;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/bitCoin")
public class BitCoinController {

	private Logger logger = LoggerFactory.getLogger(BitCoinController.class);
	
	@Autowired
	private BitcoinRepository bitcoinRepo;
	
	@Autowired
	private TxRepository txRepo;
	
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
	public ResponseEntity<PaymentResponseDTO> createOrder(@RequestBody PaymentRequestDTO btcDTO) { 
		logger.info("Init payment");
		
		SellerBitcoinInfo sbi = bitcoinRepo.findByidMagazine(btcDTO.getSellerId());
		
		System.out.println("ADDRESS  " + sbi.getBitcoinAddress());
		
		String token = sbi.getBitcoinAddress();
		
		String authToken = "Bearer " + token;
		//trebali bismo da kreiramo transakciju koja ce biti u stanju "pending" sve dok korisnik ne plati ili mu ne istekne odredjeno vreme
		
		//txRepo.save(tx);
		//RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.getForEntity("https://google.rs", String.class);
		//restTemplate.getForEntity("https://api.coingate.com/v2/ping", String.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authToken);	 
		
		System.out.println("Amount: " + btcDTO.getAmount());
		
		CreateOrderRequestDTO order = new CreateOrderRequestDTO("1111", btcDTO.getAmount(), "BTC", "DO_NOT_CONVERT", "Title", "Description", "https://localhost:4200/success", "https://localhost:4200/failed", "https://localhost:4200/success", "token");
		
		ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders", HttpMethod.POST,
				new HttpEntity<Object>(order, headers), Object.class);
		
		logger.info(responseEntity.getBody().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		BitCoinResponseDTO btcResponse = new BitCoinResponseDTO();

		btcResponse = mapper.convertValue(responseEntity.getBody(), BitCoinResponseDTO.class);
		
		String paymentUrl = btcResponse.getPayment_url();
		
		logger.info(paymentUrl);
		
		Tx tx = createTransaction(btcResponse);
		txRepo.save(tx);
		//BitCoinResponseDTO response = parser.parseList(responseEntity.getBody().toString());
		
		//BitCoinResponseDTO responseDTO =  responseEntity.;
		
		//String pageToRedirec = responseDTO.getPayment_url();
		
		//restTemplate.exchange
		//ResponseEntity<String> response =  restTemplate.postForEntity("https://api-sandbox.coingate.com/v2/orders", btcDTO, String.class);
		
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		responseDTO.setPaymentUrl(paymentUrl);
		responseDTO.setPaymentId(1l);
		
		return new ResponseEntity<PaymentResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@RequestMapping(path="/getOrder/{order_id}/{idMagazine}", method = RequestMethod.GET)
	public ResponseEntity<GetOrderResponseDTO> getOrder(@PathVariable Integer order_id, @PathVariable Long idMagazine) {
		GetOrderResponseDTO order = new GetOrderResponseDTO();
		
		SellerBitcoinInfo sbi = bitcoinRepo.findByidMagazine(idMagazine);
		
		System.out.println("ADDRESS  " + sbi.getBitcoinAddress());
		
		String token = sbi.getBitcoinAddress();
		
		String authToken = "Bearer " + token;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authToken);
		
		ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders/" + Integer.toString(order_id), HttpMethod.GET,
				new HttpEntity<Object>(order, headers), Object.class);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		order = mapper.convertValue(responseEntity.getBody(), GetOrderResponseDTO.class);
		
		logger.info(order.toString());
		
		return new ResponseEntity<GetOrderResponseDTO>(order,HttpStatus.OK);
	}
	
	
	
	public Tx createTransaction(BitCoinResponseDTO btcDTO) {
		
		Tx tx = new Tx();
		
		
		tx.setDate(new Date());
		tx.setAmountOfMoney(btcDTO.getPrice_amount());
		tx.setStatus(TxStatus.PENDING);
		tx.setRecieverAddress(btcDTO.getPayment_url());
		tx.setorder_id(btcDTO.getId()); //ovaj id je na coin gate-u i moram ga cuvati u transakciji
		
		//trebace ovde jos da se setuje id korisnika koji je kreirao porudzbinu kako bi kasnije mogao da getuje sve
		//njegove transakcije
		
		
		return tx;
		
	}
	
	/*
	 * Metoda za konkretno placanje, to be implemented
	 * */
	@RequestMapping(path="/checkout/{order_id}/{idMagazine}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> checkout(@PathVariable Integer order_id,  @PathVariable Long idMagazine) { 
		logger.info("Init checkout");
		
		CheckoutResponseDTO checkoutDTO = new CheckoutResponseDTO();
		
		SellerBitcoinInfo sbi = bitcoinRepo.findByidMagazine(idMagazine);
		
		System.out.println("ADDRESS  " + sbi.getBitcoinAddress());
		
		String token = sbi.getBitcoinAddress();
		
		String authToken = "Bearer " + token;
		//trebali bismo da kreiramo transakciju koja ce biti u stanju "pending" sve dok korisnik ne plati ili mu ne istekne odredjeno vreme
		
		//txRepo.save(tx);
		//RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.getForEntity("https://google.rs", String.class);
		//restTemplate.getForEntity("https://api.coingate.com/v2/ping", String.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authToken);	   
		
		ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders" + Integer.toString(order_id), HttpMethod.POST,
				new HttpEntity<Object>(checkoutDTO, headers), Object.class);
		
		logger.info(responseEntity.getBody().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		CheckoutResponseDTO btcResponse = new CheckoutResponseDTO();

		btcResponse = mapper.convertValue(responseEntity.getBody(), CheckoutResponseDTO.class);
		
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
