package com.project.bitcoinHandler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
import com.project.bitcoinHandler.dto.TxInfoDto;
import com.project.bitcoinHandler.dto.BitCoinResponseDTO;
import com.project.bitcoinHandler.dto.CheckoutResponseDTO;
import com.project.bitcoinHandler.model.SellerBitcoinInfo;
import com.project.bitcoinHandler.model.Tx;
import com.project.bitcoinHandler.repository.BitcoinRepository;
import com.project.bitcoinHandler.repository.TxRepository;
import com.project.bitcoinHandler.util.TxStatus;
import com.project.bitcoinHandler.util.TxStatusReqHandler;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bitcoinHandler.*;

@RestController
@RequestMapping("/bitCoin")
public class BitCoinController {

	private Logger logger = LoggerFactory.getLogger(BitCoinController.class);
	
	public static String CREATE_ORDER_API = "https://api-sandbox.coingate.com/v2/orders";
	
	public static Integer STATIC_ID = 224652;
	public static String STATIC_TOKEN = "ab7fzPdN49-xBVoY_LdSifCZiVrqCbdcfjWdweJS";
	public static Boolean IS_CREATE = false;
	
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
		ResponseEntity<String> responseEntity = new RestTemplate().exchange(CREATE_ORDER_API, HttpMethod.GET,
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
		
		Tx tx = createTransaction(btcResponse,sbi);
		txRepo.save(tx);
		
		TxInfoDto txInfo = new TxInfoDto();
		txInfo.setOrderId(btcDTO.getOrderId());
		txInfo.setServiceWhoHandlePayment("https://localhost:8764");
		txInfo.setPaymentId(tx.getorder_id()); //ovde se nalazi orderId koji je i na coingate-u
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentInit", txInfo, TxInfoDto.class);
		
		//BitCoinResponseDTO response = parser.parseList(responseEntity.getBody().toString());
		
		//BitCoinResponseDTO responseDTO =  responseEntity.;
		
		//String pageToRedirec = responseDTO.getPayment_url();
		
		//restTemplate.exchange
		//ResponseEntity<String> response =  restTemplate.postForEntity("https://api-sandbox.coingate.com/v2/orders", btcDTO, String.class);
		
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		responseDTO.setPaymentUrl(paymentUrl);
		responseDTO.setPaymentId(1l);

		STATIC_ID =  btcResponse.getId();
		STATIC_TOKEN = authToken;
		IS_CREATE = true;
		
		//scheduleFixedDelayTask();
		//scheduleFixedDelayTask(btcResponse.getId(), authToken);
		
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
	
	
	/*
	 * Kreiranje transakcije na bekendu u momentu kad korisnik kreira
	 * */
	private Tx createTransaction(BitCoinResponseDTO btcDTO, SellerBitcoinInfo sbi) {
		
		Tx tx = new Tx();
		
		
		tx.setDate(new Date());
		tx.setAmountOfMoney(btcDTO.getPrice_amount());
		tx.setStatus(TxStatus.PENDING);
		tx.setRecieverAddress(btcDTO.getPayment_url());
		tx.setorder_id(btcDTO.getId()); //ovaj id je na coin gate-u i moram ga cuvati u transakciji
		tx.setTxDescription("Porudzbina je kreirana od strane korisnika");
		tx.setSbi(sbi);
		
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
	
	/*
	 * Metoda koja svakih 2 min (15 sekundi radi demonstracije rada) proverava da li je transakcija prosla 
	 * na coingate-u.
	 * */
	@Scheduled(fixedDelay = 15000)
	public void scheduleFixedDelayTask() {
		
		List<Tx> listaTx = txRepo.findAll();
		
		for (Tx tx : listaTx) {
			if(tx.getStatus().equals(TxStatus.PENDING)) {
				System.out.println(
					      "Fixed delay task - " + System.currentTimeMillis() / 20000);
				//to be implemented
				
				String auth = "Bearer " + tx.getSbi().getBitcoinAddress();
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", auth);
				
				Integer orderId = tx.getorder_id();
				
				GetOrderResponseDTO getOrderDTO = new GetOrderResponseDTO();
				
			    ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders/" + orderId, HttpMethod.GET,
						new HttpEntity<Object>(getOrderDTO, headers), Object.class);
			    
			    
			    ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				GetOrderResponseDTO gorResponse = new GetOrderResponseDTO();
		
				gorResponse = mapper.convertValue(responseEntity.getBody(), GetOrderResponseDTO.class);
		
			    
			    System.out.println("Order status: " + gorResponse.getStatus());
				
			    if(gorResponse.getStatus().equals("paid") || 
			    		gorResponse.getStatus().equals("invalid") || 
			    		gorResponse.getStatus().equals("expired") || 
			    		gorResponse.getStatus().equals("canceled")) {
			    	
			    	//naredne tri linije mi nisu nista jasne zasto sam ovo radio pre mesec dana
			    	Tx tx2 = new Tx();
		    		tx2 = txRepo.findByusername(gorResponse.getId());
		    		System.out.println("TX: " + tx2.getorder_id());
		    		
		    		RestTemplate restTemplate = new RestTemplate();
		    		
		    		TxInfoDto txInfo;
			    	
			    	if(gorResponse.getStatus().equals("paid")) {
			    		
			    		tx.setStatus(TxStatus.PAID);
			    		txInfo = new TxInfoDto(tx.getorder_id(), TxStatusReqHandler.SUCCESS, "https://localhost:8764");
			    			
			    	} else if(gorResponse.getStatus().equals("invalid")) {
			    		tx.setStatus(TxStatus.FAILED);
			    		txInfo = new TxInfoDto(tx.getorder_id(), TxStatusReqHandler.FAILED, "https://localhost:8764");
			    	} else if(gorResponse.getStatus().equals("expired")){
			    		tx.setStatus(TxStatus.EXPIRED);
			    		txInfo = new TxInfoDto(tx.getorder_id(), TxStatusReqHandler.FAILED, "https://localhost:8764");
			    	} else {
			    		tx.setStatus(TxStatus.CANCELED);
			    		txInfo = new TxInfoDto(tx.getorder_id(), TxStatusReqHandler.ERROR, "https://localhost:8764");
			    	}
			    	
			    	txRepo.save(tx);
		    		ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentIsFinished", txInfo, TxInfoDto.class);
		    	
			    	
			    	
			    	
			    	
			    }
				
				
				
				
				
			} else {
				System.out.println("Nikom nista");
				logger.info("Scheduled is working...");
			}
		}
		
		System.out.println("Nema transakcija :(");
		
	/*	if(IS_CREATE) {
			System.out.println(
				      "Fixed delay task - " + System.currentTimeMillis() / 20000);
						    	
		    String authToken = STATIC_TOKEN;
		    
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", authToken);
			
			System.out.println("authtoken : " + authToken);
		    
			GetOrderResponseDTO getOrderDTO = new GetOrderResponseDTO();
			
		    ResponseEntity<Object> responseEntity = new RestTemplate().exchange("https://api-sandbox.coingate.com/v2/orders/" + STATIC_ID, HttpMethod.GET,
					new HttpEntity<Object>(getOrderDTO, headers), Object.class);
		    
		    
		    ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			GetOrderResponseDTO gorResponse = new GetOrderResponseDTO();
	
			gorResponse = mapper.convertValue(responseEntity.getBody(), GetOrderResponseDTO.class);
	
		    
		    System.out.println("Order status: " + gorResponse.getStatus());
		    
		    if(gorResponse.getStatus().equals("paid") || gorResponse.getStatus().equals("invalid") || gorResponse.getStatus().equals("expired")) {
		    	
		    	/*Tx tx = new Tx();
	    		tx = txRepo.findByOrder_Id(gorResponse.getId());
	    		System.out.println("TX: " + tx.getorder_id());
		    	
		    	if(gorResponse.getStatus().equals("paid")) {
		    		//promenimo u bazi
		    		//txRepo.getOne((Integer)gorResponse.getId());
		    		tx.setStatus(TxStatus.PAID);
		    		
		    	} else if(gorResponse.getStatus().equals("invalid")) {
		    		tx.setStatus(TxStatus.FAILED);
		    	} else {
		    		tx.setStatus(TxStatus.EXPIRED);
		    	}
		    	
		    	txRepo.save(tx);*/
		    /*	
		    	IS_CREATE = false;
		    }
					    
				    
		} else {
			System.out.println("Nikom nista");
			logger.info("Scheduled is working...");
		}*/
		
	    
	    
	}
	
}
