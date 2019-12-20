package com.project.cardPaymentService.service;

import java.util.Random;

import javax.xml.bind.ValidationException;

import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentService.exception.AuthentificationException;
import com.project.cardPaymentService.exception.PaymentRequestException;
import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.repository.UnityOfWork;

@Service
public class ValidationService {
	
	@Autowired
	private UnityOfWork unityOfWork;
	
	@Autowired
	private AuthorizationService authService;
	
	@Value("${serviceUrl}")
	private String url;
	
	
	  private Logger logger = LoggerFactory.getLogger(ValidationService.class);
	 
	
	public boolean validateAcccount(String username, String password) {
		BankAccount account = unityOfWork.getBankAccountRepository().findByBankAccountUsername(username);
		if(account == null) {
			return false;
		}
		return authService.authenticate(password, account.getBankAccountPassword(), account.getSalt());
	}
	
	public boolean validatePaymentRequest(PaymentValidationRequestDTO request) {
		return true;
	}
	
	public PaymentValidationResponseDTO returnValidationResponseAnswer() {
		return new PaymentValidationResponseDTO(url, generatePaymentId());
	}
	
	private int generatePaymentId() {
		Random random = new Random(System.nanoTime());
		int randomInt = random.nextInt(1000000000);
		return randomInt;
	}
	
	public PaymentValidationResponseDTO validateRequest(PaymentValidationRequestDTO request) throws ValidationException, AuthentificationException{
		boolean validRequest = validatePaymentRequest(request);
		if(!validRequest) {
			throw new ValidationException("Request validation exception");
		}
		boolean validAuthentification = validateAcccount(request.getMerchantId(), request.getMerchantPassword());
		if(!validAuthentification) {
			throw new AuthentificationException();
		}
		
		PaymentValidationResponseDTO response = returnValidationResponseAnswer();
		savePaymentRequest(new PaymentRequest(request, response.getPaymentId()));
		
		return response;
	}
	
	private void savePaymentRequest(PaymentRequest request) {
		unityOfWork.getPaymentRequestRepository().save(request);
	}
	
	public PaymentRequest getPaymentRequest(PaymentCardRequestDTO request) throws PaymentRequestException {
		PaymentRequest req = unityOfWork.getPaymentRequestRepository().findByPaymentIdAndMerchantUsername(request.getPaymentId(), request.getMerchantUsername());
		if(req == null) {
			throw new PaymentRequestException();
		}
		return req;
	}
	
	public Object enoughMoney() {
		return null;
	}
	
	public Object validateCardRequest() {
		return null;
	}
	

	
	
	
	
	

}
