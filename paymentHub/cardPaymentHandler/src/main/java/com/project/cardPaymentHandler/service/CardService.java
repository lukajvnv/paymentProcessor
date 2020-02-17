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
import com.project.cardPaymentHandler.dto.TxInfoDto;
import com.project.cardPaymentHandler.model.BankInfo;
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
		tx.setOrderId(request.getOrderId());
		tx.setSellerId(request.getSellerId());
		tx.setPaymentId(generateMerchantOrderId());
		saveTx(tx);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PaymentValidationResponseDTO> validationResponse;
		try {
			validationResponse = restTemplate.postForEntity(bank.getServiceUrl() + "/initPayment", newRequest, PaymentValidationResponseDTO.class);
			PaymentValidationResponseDTO responseObject =  validationResponse.getBody();
			
			switch (responseObject.getTxStatus()) {
				case SUCCESS:
					logger.info("pay init service ended successfully");
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
					responseObject.setPaymentId(tx.getPaymentId());
					break;
				default:
					throw new RestClientException("");
			}
			
			logger.info("pay init ended successfully");
			
			return responseObject;

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			PaymentValidationResponseDTO errorResponse = new PaymentValidationResponseDTO(newRequest.getErrorUrl(), tx.getPaymentId(), TxStatus.ERROR);
			//cuvanje sa sve payment id-jem
			Tx txSErr = unityOfWork.getTxRepository().findByMerchantOrderId(newRequest.getMerchantOrderId());
			txSErr.setStatus(TxStatus.ERROR);
			saveTx(txSErr);
			return errorResponse;
		}
		
		

	}
	
	public Tx checkTx(long paymentId, long merchantOrderId, boolean shouldSent) {
		Tx tx = getTx(merchantOrderId, paymentId);
		
		RestTemplate restTemplate = new RestTemplate();
		
		TxCheckDto request = new TxCheckDto(paymentId, merchantOrderId);
		//TODO: TX VEZA KA PRODAVCU->KLIJENT,BANKA->SERVIS BANKE
		ResponseEntity<Tx> txFromServiceResponse;
		try {
			logger.info("Examine request with payment Id: {}, and merchantId: {} to the bank service ", paymentId, merchantOrderId);
			txFromServiceResponse = restTemplate.postForEntity("https://localhost:8841/card/checkTx", request, Tx.class);
			Tx txFromService = txFromServiceResponse.getBody();
			
			tx.setStatus(txFromService.getStatus());
			tx.setTxDescription(txFromService.getTxDescription());
			tx.setAcquirerOrderId(txFromService.getAcquirerOrderId());
			tx.setAcquirerTimestamp(txFromService.getAcquirerTimestamp());
			tx.setSenderName(txFromService.getSenderName());
			tx.setSenderAccountNum(txFromService.getSenderAccountNum());
			logger.info("Retrieved request with payment Id: {}, and merchantId: {} from bank service ", paymentId, merchantOrderId);

			
			saveTx(tx);
			
			logger.info("Saved request with payment Id: {}, and merchantId: {} to the bank card handler ", paymentId, merchantOrderId);

			if(shouldSent) {
				sendToSC(tx);
			}
			
			return tx;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			tx.setStatus(TxStatus.ERROR);
			saveTx(tx);
			if(shouldSent) {
				sendToSC(tx);
			}
			return tx;
		}
		
	}
	
	public void checkTxs() {
		logger.info("Start checking unkwnodn tx-s");
		List<Tx> unknownStatusTxs = unityOfWork.getTxRepository().findByStatus(TxStatus.UNKNOWN);
		logger.info("Unknown txs are retrieved");
		for(Tx unknownTx: unknownStatusTxs) {
			checkTx(unknownTx.getPaymentId(), unknownTx.getMerchantOrderId(), true);
		}
		logger.info("Checking txs is finished");

	}
	
	public Tx checkTxParticular(long paymentId) {
		logger.info("Start checking unkwnodn tx-s");
		Tx tx = unityOfWork.getTxRepository().findByPaymentId(paymentId);
		logger.info("Unknown txs are retrieved");
		
		if(tx == null) {
			Tx txNull = new Tx();
			txNull.setStatus(TxStatus.ERROR);
			return txNull;
		}
		
		if(tx.getStatus().equals(TxStatus.UNKNOWN)) {
			Tx txs = checkTx(tx.getPaymentId(), tx.getMerchantOrderId(), false);
			//ako je unknown...
			return txs;
		}
		
		logger.info("Checking txs is finished");
		return tx;
	}
	
	
	public void sendToSC(Tx tx) {
		RestTemplate restTemplate = new RestTemplate();
		TxInfoDto request = new TxInfoDto(tx.getPaymentId(), tx.getStatus(), "https://localhost:8763/card");
		logger.info("Saved request with payment Id: {}, and merchantId: {} to the bank card handler ", tx.getPaymentId(), tx.getMerchantOrderId());

		ResponseEntity<TxInfoDto> response = restTemplate.postForEntity("https://localhost:8111/request/updateTxAfterPaymentIsFinished", request, TxInfoDto.class);
		
		logger.info("Saved request with payment Id: {}, and merchantId: {} to the bank card handler ", tx.getPaymentId(), tx.getMerchantOrderId());
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
	
	public Tx getTxByOrderId(Long orderId) {
		return unityOfWork.getTxRepository().findByOrderId(orderId);
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
