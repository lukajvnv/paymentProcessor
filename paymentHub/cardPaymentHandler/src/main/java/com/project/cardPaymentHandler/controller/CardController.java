package com.project.cardPaymentHandler.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.cardPaymentHandler.dto.PaymentRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentHandler.dto.TxInfoDto;
import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);
	
	
	@RequestMapping(path="/pay",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> pay() {
		
		return new ResponseEntity<>(new String("Okej cardHandler get radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path="/pay",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentValidationResponseDTO> payPost(@RequestBody PaymentRequestDTO request, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.info("Pay initialized stop -> invalid input");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		Tx txWithThisOrderIdAlreadyInit = cardService.getTxByOrderId(request.getOrderId());
		if(txWithThisOrderIdAlreadyInit != null) {
			logger.error("Tx already initialized with this orderId");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Pay initialized");
		PaymentValidationResponseDTO response = new PaymentValidationResponseDTO();
		try {
			response =  cardService.pay(request);
			logger.info("Pay initialized ended successfully");
			
			RestTemplate restTemplate = new RestTemplate();
			
			TxInfoDto txInfo = new TxInfoDto();
			txInfo.setOrderId(request.getOrderId());
			txInfo.setServiceWhoHandlePayment("https://localhost:8763/card");
			txInfo.setPaymentId(response.getPaymentId());
			txInfo.setStatus(response.getTxStatus());
			
			try {
				ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentInit", txInfo, TxInfoDto.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
			}
			
			return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.error("Pay initialized ended with errors");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path="/saveTx",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tx> saveTx(@RequestBody Tx request) {
		logger.info("saved tx");
		//update postojeceg inicijalnog tx
		//cuvanje sa sve payment id-jem
		Tx txS = cardService.getTx(request.getMerchantOrderId(), request.getPaymentId());
		if(txS == null) {
			return new ResponseEntity<>(new Tx(), HttpStatus.OK); 
		}
		
		txS.setStatus(request.getStatus());
		txS.setTxDescription(request.getTxDescription());
		txS.setAcquirerOrderId(request.getAcquirerOrderId());
		txS.setAcquirerTimestamp(request.getAcquirerTimestamp());
		txS.setSenderName(request.getSenderName());
		txS.setSenderAccountNum(request.getSenderAccountNum());
		
		//saveTx(txS);
		request = cardService.saveTx(txS);
		
		//callback to NC
		RestTemplate restTemplate = new RestTemplate();
		
		TxInfoDto txInfo = new TxInfoDto(request.getPaymentId(), request.getStatus(), "https://localhost:8763/card");
		
		try {
			ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentIsFinished", txInfo, TxInfoDto.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return new ResponseEntity<>(new Tx(), HttpStatus.OK);
		}
	
		return new ResponseEntity<>(new Tx(), HttpStatus.OK);
	}
	
	
//	@GetMapping(path="/checkTx")
//	public void checkTx() {
//		long paymentId = 1234567896l;
//		long merchantOrderId = 4561230258l;
//		
//		cardService.checkTx(paymentId, merchantOrderId);
//		
//	}
	
	@PostMapping(path="/checkTx")
	public ResponseEntity<TxInfoDto> checkTx(@RequestBody TxInfoDto request ) {
		Tx tx = cardService.checkTxParticular(request.getPaymentId());
		request.setStatus(tx.getStatus());
		return new ResponseEntity<TxInfoDto>(request, HttpStatus.OK);
	}
	
	private static final String CRON_EXP_EVERY_ONE_MINUTE = "0 */1 * ? * *";
	private static final String CRON_EXP_EVERY_FIVE_MINUTE = "0 */5 * ? * *";
	private static final String CRON_EXP_EVERY_SIX_MINUTE = "0 */6 * ? * *";
	private static final long DELAY_EXP_EVERY_30_S = 30000;
	private static final long DELAY_EXP_EVERY_ONE_MINUTE = 60000;
	private static final long DELAY_EXP_EVERY_FIVE_MINUTE = 300000;
	
	@Scheduled(cron = CRON_EXP_EVERY_SIX_MINUTE)
//	@Scheduled(fixedDelay = DELAY_EXP_EVERY_30_S)
	@GetMapping(path="/checkTxs")
	public void checkTxs() {
		cardService.checkTxs();
		
	}
	
	
}