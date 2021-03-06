package com.project.cardPaymentService.controller;

import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.project.cardPaymentService.Proba;
import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentCardResponseDTO;
import com.project.cardPaymentService.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentService.dto.PccRequestDTO;
import com.project.cardPaymentService.dto.PccResponseDTO;
import com.project.cardPaymentService.dto.TxCheckDto;
import com.project.cardPaymentService.exception.AuthentificationException;
import com.project.cardPaymentService.exception.NotEnoughMoney;
import com.project.cardPaymentService.exception.PaymentRequestException;
import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.service.TransactionService;
import com.project.cardPaymentService.service.ValidationService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	TransactionService transactionService;
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);
		
	@RequestMapping(path="/initPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentValidationResponseDTO> testPost(@Valid @RequestBody PaymentValidationRequestDTO request, BindingResult result) {
			PaymentValidationResponseDTO response;
			
			logger.info("PaymentRequest initialized");
			try {
				if (result.hasErrors()) {
					logger.error("PaymentRequest failed: invalid input");
					response = new PaymentValidationResponseDTO(TxStatus.FAILED);
					return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
					}
				response = validationService.validateRequest(request);
				
				 
				 return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
			} catch (ValidationException e) {
				logger.error("PaymentRequest failed due to invalid input data", e);
				 //response = new PaymentValidationResponseDTO(TxStatus.FAILED)
				 //return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);

			} catch (AuthentificationException e) {
				 logger.error("PaymentRequest failed due to unsucessful authentification", e);
				 //return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);

			} catch (Exception e) {
				 logger.error("PaymentRequest failed due to unknown error", e);
				 //return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);

			}
			response = new PaymentValidationResponseDTO(TxStatus.FAILED);
			return new ResponseEntity<PaymentValidationResponseDTO>(response, HttpStatus.OK);
		
	}
		
	
	@PostMapping("/pay")
	  public ResponseEntity<PaymentCardResponseDTO> pay(@Valid @RequestBody PaymentCardRequestDTO request, BindingResult bindingResult) {
		PaymentRequest paymentReq = null;
		try {
			paymentReq = validationService.getPaymentRequest(request);
			
			if(paymentReq.isSubmitted()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			if(paymentReq.getRequestStatus() == TxStatus.ERROR) {
				logger.error("Time is up! You cannot longer pay your transaction");
				PaymentCardResponseDTO res = new PaymentCardResponseDTO();
				res.setRedirectUrl(paymentReq.getErrorUrl());
				return new ResponseEntity<PaymentCardResponseDTO>(res, HttpStatus.REQUEST_TIMEOUT);
			}
			
			
		} catch (PaymentRequestException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		PaymentCardResponseDTO response = new PaymentCardResponseDTO(paymentReq.getMerchantOrderId(), paymentReq.getPaymentId());
	    try {
	    	
	    	logger.info("Payment action has been initialized");
			if (bindingResult.hasErrors()) {
			  logger.error("Payment action failed due to invalid input");
			  throw new Exception(bindingResult.getAllErrors().get(0).getDefaultMessage());
			}
			
			paymentReq.setSubmitted(true);
			validationService.savePaymentRequest(paymentReq);
			
			
			response = transactionService.tX(request, paymentReq, response);
			paymentReq.setRequestStatus(response.getOutcome());
	    	validationService.savePaymentRequest(paymentReq);
			logger.info("Payment action finished successfully");
			transactionService.sendTxToKp(response.getTx());
		    return new ResponseEntity<PaymentCardResponseDTO>(response, HttpStatus.OK);
		}  catch (Exception e) {
			logger.error("Payment action failed due to unknown error", e);
			paymentReq.setRequestStatus(TxStatus.ERROR);
	    	validationService.savePaymentRequest(paymentReq);
	    	Tx errorTx = transactionService.saveTxLog(TxStatus.ERROR, paymentReq.getAmount(), "ERROR", "NONE" , "NONE", "NONE", "NONE", paymentReq.getPaymentId()
	    			, paymentReq.getMerchantTimestamp(), response.getMerchantOrderId(), null, null);
			logger.info("Payment action finished due to unknown error");
			response.setTx(errorTx);
			response.setRedirectUrl(paymentReq.getErrorUrl());
			response.setOutcome(TxStatus.ERROR);
			transactionService.sendTxToKp(errorTx);
		}
	    
	    return new ResponseEntity<PaymentCardResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	  }
	
	@PostMapping("/payViaPcc")
	  public ResponseEntity<PccResponseDTO> paylViaPcc(@Valid @RequestBody PccRequestDTO request, BindingResult bindingResult) {
		PccResponseDTO response = new PccResponseDTO();

	    try {
	    	response.setClientBankAccount(request.getClientBankAccount());
	    	response.setClientName(request.getClientName());
			response.setAcquirerOrderId(request.getAcquirerOrderId());
			response.setAcquirerTimestamp(request.getAcquirerTimestamp());
	    	logger.info("Payment [PCC] action has been initialized");
			if (bindingResult.hasErrors()) {
			  logger.error("Payment [PCC] action failed due to invalid input");
			  throw new Exception();
			}
			
			response = transactionService.handleSendMoneyTx(request, response);
			logger.info("Payment [PCC] action finished successfully");
		}  catch (Exception e) {
			logger.error("Payment [PCC] action failed due to unknown error", e);
			response.setStatus(TxStatus.ERROR);
		}
	    
	    return new ResponseEntity<PccResponseDTO>(response, HttpStatus.OK);
	    
	  }
	
	private static final String CRON_EXP_EVERY_ONE_MINUTE = "0 */1 * ? * *";
	private static final String CRON_EXP_EVERY_FIVE_MINUTE = "0 */5 * ? * *";
	private static final long DELAY_EXP_EVERY_30_S = 30000;
	private static final long DELAY_EXP_EVERY_ONE_MINUTE = 60000;
	private static final long DELAY_EXP_EVERY_FIVE_MINUTE = 300000;
	
		@Scheduled(fixedDelay = DELAY_EXP_EVERY_FIVE_MINUTE)
		@GetMapping("/checkPaymentRequest")
		public void checkPaymentRequest() {
			logger.info("Check payment requests initialized");
			List<PaymentRequest> nonPaidList = validationService.retrieveNonPaidRequest();
			logger.info("Check payment requests nonPaidList retrieved");
			for (PaymentRequest request: nonPaidList) {
				request.setRequestStatus(TxStatus.ERROR);
				validationService.savePaymentRequest(request);
				BankAccount sellerAccount = validationService.getAccount(request.getMerchantUsername());
				Tx sellerTx = transactionService.saveTxLog(TxStatus.ERROR, request.getAmount(), "Error during transaction", "UNKNOWN", "UNKNOWN", sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), request.getPaymentId(),
						request.getMerchantTimestamp(), request.getMerchantOrderId(), null, null);
				logger.info("Save tx with error in db, message: {}", sellerTx.getTxDescription());
				transactionService.sendTxToKp(sellerTx);
			}
			logger.info("Check payment requests finished");
		}
		
		@PostMapping("/checkTx")
		public ResponseEntity<Tx> checkTx(@RequestBody TxCheckDto txcheck) {
			Tx tx = null;
			try {
				logger.info("Check tx initialized");
				tx = validationService.getTx(txcheck.getPaymentId(), txcheck.getMerchantOrderId());
				if(tx == null) {
					logger.error("Check tx : tx not found");
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				logger.error("Check tx ended with exception");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			logger.info("Check tx finished");
			return new ResponseEntity<Tx>(tx, HttpStatus.OK);
		}
	
}
