package com.project.cardPaymentService.controller;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

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
import org.thymeleaf.TemplateEngine;

import com.project.cardPaymentService.Proba;
import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentCardResponseDTO;
import com.project.cardPaymentService.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentService.exception.AuthentificationException;
import com.project.cardPaymentService.exception.NotEnoughMoney;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.service.TransactionService;
import com.project.cardPaymentService.service.ValidationService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	 TemplateEngine htmlTemplateEngine;
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	TransactionService transactionService;
	
	private Logger logger = LoggerFactory.getLogger(CardController.class);
	
//	@RequestMapping(path="/initPayment", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> testPost(@Valid @RequestBody PaymentValidationRequestDTO request, BindingResult result) {
//			PaymentValidationResponseDTO response;
//			logger.info("PaymentRequest initialized");
//			try {
//				if (result.hasErrors()) {
//					logger.error("PaymentRequest failed: invalid input");
//					  return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
//					}
//				response = validationService.validateRequest(request);
//				
//				 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
//				 ctx.setVariable("paymentId", response.getPaymentId());
//				 ctx.setVariable("merchantId", request.getMerchantId());
//				 // Rendered template in String, You can now return in a JSON property
//				 String htmlContent = this.htmlTemplateEngine.process("html/card_info_form.html", ctx);
//
//				 logger.info("PaymentRequest finished successfully");
//				 return new ResponseEntity<String>(htmlContent, HttpStatus.OK);
//			} catch (ValidationException e) {
//				logger.error("PaymentRequest failed due to invalid input data", e);
//				
//			} catch (AuthentificationException e) {
//				 logger.error("PaymentRequest failed due to unsucessful authentification", e);
//			} catch (Exception e) {
//				 logger.error("PaymentRequest failed due to unknown error", e);
//			}
//		
//			return new ResponseEntity<String>("Greska",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
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
	
	/*
	 * @RequestMapping(path="/initPayment", method = RequestMethod.GET) public
	 * String testPost() {
	 * 
	 * PaymentValidationRequestDTO request = new PaymentValidationRequestDTO();
	 * PaymentValidationResponseDTO response = new PaymentValidationResponseDTO();
	 * request.setMerchantId("flak"); response.setPaymentId(5l); final
	 * org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
	 * ctx.setVariable("paymentId", response.getPaymentId());
	 * ctx.setVariable("merchantId", request.getMerchantId());
	 * 
	 * // Rendered template in String, You can now return in a JSON property String
	 * htmlContent = this.htmlTemplateEngine.process("html/card_info_form.html",
	 * ctx);
	 * 
	 * return htmlContent;
	 * 
	 * }
	 */
	
	@RequestMapping(path="/initPayment", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> initPayment() {
			
				PaymentValidationRequestDTO request = new PaymentValidationRequestDTO();
				PaymentValidationResponseDTO response = new PaymentValidationResponseDTO();
				request.setMerchantId("flak");
				response.setPaymentId(5l);
				final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
				 ctx.setVariable("paymentId", response.getPaymentId());
				 ctx.setVariable("merchantId", request.getMerchantId());

				 // Rendered template in String, You can now return in a JSON property
				 String htmlContent = this.htmlTemplateEngine.process("html/card_info_form.html", ctx);

				 return new ResponseEntity<String>(htmlContent, HttpStatus.OK);
			
	}
	
	
	@PostMapping("/pay")
	  public ResponseEntity<PaymentCardResponseDTO> pay(@Valid @RequestBody PaymentCardRequestDTO request, BindingResult bindingResult) {

	    try {
	    	PaymentRequest paymentReq = validationService.getPaymentRequest(request);
			PaymentCardResponseDTO response = new PaymentCardResponseDTO(paymentReq.getMerchantOrderId(), paymentReq.getPaymentId());
	    	logger.info("Payment action has been initialized");
			if (bindingResult.hasErrors()) {
			  logger.error("Payment action failed due to invalid input");
			  
			}
			response = transactionService.tX(request, paymentReq, response);
			logger.info("Payment action finished successfully");
		    return new ResponseEntity<PaymentCardResponseDTO>(response, HttpStatus.OK);
		} catch (NotEnoughMoney e) {
			logger.error("Payment action failed due to not enough money at buyer's account", e);
			
		} catch (Exception e) {
			logger.error("Payment action failed due to unknown error", e);
			
		}
	    
	    return new ResponseEntity<PaymentCardResponseDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	    
	  }
	
	
	
	
	
}
