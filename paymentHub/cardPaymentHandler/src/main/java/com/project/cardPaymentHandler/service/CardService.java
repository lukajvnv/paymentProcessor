package com.project.cardPaymentHandler.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.cardPaymentHandler.dto.PaymentRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentHandler.model.BankInfo;
import com.project.cardPaymentHandler.model.SellerBankInfo;
import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.util.DateConverter;

@Service
public class CardService {

	@Autowired
	private UnityOfWork unityOfWork;
	
	@Autowired
	private SymetricCryptography cryptoService;
	
	
	
	private Logger logger = LoggerFactory.getLogger(CardService.class);

	
	public PaymentValidationResponseDTO pay(PaymentRequestDTO request) throws IOException {
		logger.info("pay init");
		
		SellerBankInfo account = getAccount(request.getSellerId());
		if(account == null) {
			throw new IOException();
		}
		
		String bankNum= getBankFromSellerAccount(account.getSellerBankAccountNumber());
		BankInfo bank = findBank(bankNum);
		
		PaymentValidationRequestDTO newRequest = createServiceRequest(request, account);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PaymentValidationResponseDTO> validationResponse =  restTemplate.postForEntity("https://localhost:8841/card/initPayment", newRequest, PaymentValidationResponseDTO.class);
		
		PaymentValidationResponseDTO responseObject;
		responseObject =  validationResponse.getBody();
		
		switch (responseObject.getTxStatus()) {
			case SUCCESS:
				logger.info("pay init service ended successfully");

				break;
			case ERROR:
				logger.error("pay init service ended with errors");

				break;	
			case FAILED:
				logger.error("pay init service operations failed");

				break;
		default:
			break;
		}
		
		logger.info("pay init ended successfully");

		return responseObject;
	}
	
	public Tx saveTx(Tx tx) {
		tx.setTxId(-1l);
		Tx txSaved = this.unityOfWork.getTxRepository().save(tx);
		return txSaved;
	}
	
	public SellerBankInfo getAccount(long id) {
		return this.unityOfWork.getSellerBankInfoRepository().findBySellerIdentifier(id);
	}
	
	public BankInfo findBank(String number) {
		return this.unityOfWork.getBankInfoRepository().findByBankAccountInNationalBank(number);
	}
	
	public String getBankFromSellerAccount(String bankAccountNum) {
		String bank = bankAccountNum.substring(0, 3);
		return bank;
	}
	
	public PaymentValidationRequestDTO createServiceRequest(PaymentRequestDTO request, SellerBankInfo account) throws IOException {
		String merchantId = cryptoService.findInfo(account.getSellerUsername());
		String merchantPassword = cryptoService.findInfo(account.getSellerPassword());
		Timestamp merchantTimeStamp = new Timestamp(System.currentTimeMillis());
		String merchantTimeStampS =  DateConverter.encodeT(merchantTimeStamp);
		Long merchantOrderId = generatePaymentId();
		
		PaymentValidationRequestDTO validationRequest = new PaymentValidationRequestDTO(merchantId, merchantPassword, request.getAmount(), merchantOrderId, merchantTimeStampS, account.getTxSuccessUrl(), account.getTxFailedUrl(), account.getTxErrorUrl());

		return validationRequest;
	}
	
	private long generatePaymentId() {
		Random random = new Random(System.nanoTime());
		long randomInt = random.nextLong();
		return randomInt;
	}
}
