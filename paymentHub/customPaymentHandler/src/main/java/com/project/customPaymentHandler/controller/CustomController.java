package com.project.customPaymentHandler.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.customPaymentHandler.dto.PaymentRequestDTO;
import com.project.customPaymentHandler.dto.PaymentValidationResponseDTO;
import com.project.customPaymentHandler.dto.TxInfoDto;
import com.project.customPaymentHandler.model.CustomSellerClientInfo;
import com.project.customPaymentHandler.model.Tx;
import com.project.customPaymentHandler.model.TxStatus;
import com.project.customPaymentHandler.repository.CustomClientInfoRepository;
import com.project.customPaymentHandler.repository.IdGeneratorRepository;
import com.project.customPaymentHandler.util.Generator;

@RestController
@RequestMapping("/custom")
public class CustomController {
	
	@Autowired
	private IdGeneratorRepository idGeneratorRepo;
	
	@Autowired
	private CustomClientInfoRepository customClientRepo;
	
	private Logger logger = LoggerFactory.getLogger(CustomController.class);
	
	
	@RequestMapping(path="/pay",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> pay() {
		
		return new ResponseEntity<>(new String("Okej cardHandler get radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path="/pay",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> payPost(@Valid @RequestBody PaymentRequestDTO request, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Pay initialized");
		PaymentValidationResponseDTO response = new PaymentValidationResponseDTO();
		
		//ovo nekako resiti da bude genericki
		
		CustomSellerClientInfo sellerAccount = customClientRepo.findBySellerIdentifier(request.getSellerId());
		if(sellerAccount == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		response.setPaymentUrl(sellerAccount.getTxSuccessUrl());
		
		try {
						
			RestTemplate restTemplate = new RestTemplate();
			
			long paymentId = generateUniqueDigits(); 
			
			TxInfoDto txInfo = new TxInfoDto();
			txInfo.setOrderId(request.getOrderId());
			txInfo.setServiceWhoHandlePayment("https://localhost:8766/custom");
			txInfo.setPaymentId(paymentId);
			
			ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentInit", txInfo, TxInfoDto.class);
			
			
			
			logger.info("Pay initialized ended");

			Tx req = new Tx ();
			req.setPaymentId(paymentId);
			req.setStatus(TxStatus.SUCCESS);
			req.setRecieverName(sellerAccount.getSellerIdentifier().toString());
			req.setAmountOfMoney(request.getAmount());
			
			saveTx(req);
			
			return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		logger.error("Pay initialized ended with errors");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<Tx> saveTx(Tx request) {
		logger.info("Payment finished Tx init");

		logger.info("Tx ended sucessfully, amount:  {} , clientSeller : {} has started", request.getAmountOfMoney(), request.getRecieverName());

		//callback to NC
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			TxInfoDto txInfo = new TxInfoDto(request.getPaymentId(), request.getStatus(), "https://localhost:8766/custom");
			
			ResponseEntity<TxInfoDto> r = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentIsFinished", txInfo, TxInfoDto.class);

			logger.info("Payment finishid succcefully");

			
			return new ResponseEntity<>(new Tx(), HttpStatus.OK);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("Payment finishid with errors");

			
			return new ResponseEntity<>(new Tx(), HttpStatus.OK);
		}
	}
	
	
	@PostMapping(path="/checkTx")
	public ResponseEntity<TxInfoDto> checkTx(@RequestBody TxInfoDto request ) {
		request.setStatus(TxStatus.SUCCESS);
		return new ResponseEntity<TxInfoDto>(request, HttpStatus.OK);
	}
	
	private static final String CRON_EXP_EVERY_ONE_MINUTE = "0 */1 * ? * *";
	private static final String CRON_EXP_EVERY_FIVE_MINUTE = "0 */5 * ? * *";
	private static final long DELAY_EXP_EVERY_ONE_MINUTE = 60000;
	private static final long DELAY_EXP_EVERY_FIVE_MINUTE = 300000;
	
	
	private long generateUniqueDigits() {
//		Random random = new Random(System.nanoTime());
//		long number = random.nextLong();
		Generator g = idGeneratorRepo.save(new Generator());
		long number = g.getGeneratedId();
		return number;
	}
	
}