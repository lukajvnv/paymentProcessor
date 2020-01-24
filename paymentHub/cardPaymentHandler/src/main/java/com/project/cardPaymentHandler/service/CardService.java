package com.project.cardPaymentHandler.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.cardPaymentHandler.dto.PaymentRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentHandler.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentHandler.dto.TxCheckDto;
import com.project.cardPaymentHandler.model.BankInfo;
import com.project.cardPaymentHandler.model.FieldMetadata;
import com.project.cardPaymentHandler.model.SellerBankInfo;
import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.model.TxStatus;
import com.project.cardPaymentHandler.util.DateConverter;
import com.project.cardPaymentHandler.util.Generator;

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
		//cuvanje inicijalnog tx
		Tx tx = createTx(TxStatus.UNKNOWN, newRequest.getAmount(), account.getSellerClientName(), account.getSellerBankAccountNumber(), 0L, DateConverter.decodeT(newRequest.getMerchantTimeStamp()), newRequest.getMerchantOrderId(), DateConverter.decodeT(newRequest.getMerchantTimeStamp()), -1l);
		saveTx(tx);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PaymentValidationResponseDTO> validationResponse;
		try {
			validationResponse = restTemplate.postForEntity(bank.getServiceUrl() + "/initPayment", newRequest, PaymentValidationResponseDTO.class);
			PaymentValidationResponseDTO responseObject =  validationResponse.getBody();
			
			switch (responseObject.getTxStatus()) {
				case SUCCESS:
					logger.info("pay init service ended successfully");
					//cuvanje sa error statusom
					Tx txS = unityOfWork.getTxRepository().findByMerchantOrderId(newRequest.getMerchantOrderId());
					txS.setPaymentId(responseObject.getPaymentId());
					saveTx(txS);
					break;
				case FAILED:
					logger.error("pay init service operations failed in bank service");
					//cuvanje sa error statusom
					Tx txSErr = unityOfWork.getTxRepository().findByMerchantOrderId(newRequest.getMerchantOrderId());
					txSErr.setStatus(TxStatus.ERROR);
					saveTx(txSErr);
					responseObject.setPaymentUrl(newRequest.getErrorUrl());
					responseObject.setTxStatus(TxStatus.ERROR);
					break;
				default:
					throw new RestClientException("");
			}
			
			logger.info("pay init ended successfully");
			
			return responseObject;

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PaymentValidationResponseDTO errorResponse = new PaymentValidationResponseDTO(newRequest.getErrorUrl(), 0, TxStatus.ERROR);
			//cuvanje sa sve payment id-jem
			Tx txSErr = unityOfWork.getTxRepository().findByMerchantOrderId(newRequest.getMerchantOrderId());
			txSErr.setStatus(TxStatus.ERROR);
			saveTx(txSErr);
			return errorResponse;
		}
		
		

	}
	
	public void checkTx(long paymentId, long merchantOrderId) {
		Tx tx = getTx(merchantOrderId, paymentId);
		
		RestTemplate restTemplate = new RestTemplate();
		
		TxCheckDto request = new TxCheckDto(paymentId, merchantOrderId);
		//TODO: TX VEZA KA PRODAVCU->KLIJENT,BANKA->SERVIS BANKE
		ResponseEntity<Tx> txFromServiceResponse;
		try {
			txFromServiceResponse = restTemplate.postForEntity("https://localhost:8841/card/checkTx", request, Tx.class);
			Tx txFromService = txFromServiceResponse.getBody();
			
			tx.setStatus(txFromService.getStatus());
			tx.setTxDescription(txFromService.getTxDescription());
			tx.setAcquirerOrderId(txFromService.getAcquirerOrderId());
			tx.setAcquirerTimestamp(txFromService.getAcquirerTimestamp());
			tx.setSenderName(txFromService.getSenderName());
			tx.setSenderAccountNum(txFromService.getSenderAccountNum());
			
			saveTx(tx);
			sendToSC(tx);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.setStatus(TxStatus.ERROR);
			saveTx(tx);
			sendToSC(tx);
		}
		
	}
	
	public void checkTxs() {
		List<Tx> unknownStatusTxs = unityOfWork.getTxRepository().findByStatus(TxStatus.UNKNOWN);
		for(Tx unknownTx: unknownStatusTxs) {
			checkTx(unknownTx.getPaymentId(), unknownTx.getMerchantOrderId());
		}
	}
	
	
	public void sendToSC(Tx tx) {
		
	}
	
	public Tx saveTx(Tx tx) {
		//tx.setTxId(-1l);
		Tx txSaved = this.unityOfWork.getTxRepository().save(tx);
		return txSaved;
	}
	
	public Tx createTx(TxStatus status, Float amount, String receiverName, String receiverAccountNum, long paymentId, Timestamp merchantTimestamp, Long merchantOrderId,  Timestamp acquirerTimestamp, Long acquirerOrderId) {
		Tx txxs = new Tx(new Timestamp(System.currentTimeMillis()), status, amount, "", paymentId, "", "", receiverName, receiverAccountNum, merchantTimestamp, merchantOrderId, acquirerTimestamp, acquirerOrderId);
		return txxs;
	}
	
	public Tx getTx(Long merchantOrderId, Long paymentId) {
		return unityOfWork.getTxRepository().findByMerchantOrderIdAndPaymentId(merchantOrderId, paymentId);
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
		Long merchantOrderId = generateMerchantOrderId();
		
		PaymentValidationRequestDTO validationRequest = new PaymentValidationRequestDTO(merchantId, merchantPassword, request.getAmount(), merchantOrderId, merchantTimeStampS, account.getTxSuccessUrl(), account.getTxFailedUrl(), account.getTxErrorUrl());

		return validationRequest;
	}
	
	private long generateMerchantOrderId() {
//		Random random = new Random(System.nanoTime());
//		long number = random.nextLong();
		Generator g = unityOfWork.getIdGeneratorRepository().save(new Generator());
		long number = g.getGeneratedId();
		return number;
	}
}
