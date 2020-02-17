package com.project.cardPaymentService.service;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentCardResponseDTO;
import com.project.cardPaymentService.dto.PccRequestDTO;
import com.project.cardPaymentService.dto.PccResponseDTO;
import com.project.cardPaymentService.exception.NotEnoughMoney;
import com.project.cardPaymentService.exception.PaymentRequestException;
import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.CardAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.repository.UnityOfWork;
import com.project.cardPaymentService.util.DateConverter;
import com.project.cardPaymentService.util.Generator;

@Service
public class TransactionService {
	
	@Autowired
	private UnityOfWork unityOfWork;
	
	@Autowired
	private AuthorizationService authService;
	
	@Value("${bankPanNum}")
	private String bankPanNum;
	
	private Logger logger = LoggerFactory.getLogger(TransactionService.class);

	
	public boolean canReserveMoney(BankAccount account, Float money) {
		return account.getBalance() > money;
	}
	
	public PaymentCardResponseDTO handleTx(BankAccount sellerAccount, BankAccount buyerAccount, PaymentRequest request, PaymentCardResponseDTO response) throws NotEnoughMoney {
		logger.info("Tx at buyer's account started");
		boolean canReserve = canReserveMoney(buyerAccount, request.getAmount());
		if(!canReserve) {
			logger.error("Tx at buyer's account failed: not enough money at the account");
			Tx failedTx = saveTxLog(TxStatus.FAILED, request.getAmount(), "Not enough money at the account", buyerAccount.getBankAccountName(), 
					buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), request.getPaymentId()
					, request.getMerchantTimestamp(), request.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
			// sendTxToKp(failedTx);
			response.setTx(failedTx);
			response.setRedirectUrl(request.getFailedUrl());
			response.setOutcome(TxStatus.FAILED);
			return  response;
			//throw new NotEnoughMoney();
		}
		
		buyerAccount.setBalance(buyerAccount.getBalance() - request.getAmount());
		saveAccount(buyerAccount);
		Tx buyerTx = saveTxLog(TxStatus.SUCCESS, request.getAmount(), "Money taken from buyer account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), request.getPaymentId()
				, request.getMerchantTimestamp(), request.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
		logger.info("Money taken from buyer account");
		
		sellerAccount.setBalance(sellerAccount.getBalance() + request.getAmount());
		saveAccount(sellerAccount);
		Tx sellerTx = saveTxLog(TxStatus.SUCCESS, request.getAmount(), "Money added to the seler account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), request.getPaymentId()
				, request.getMerchantTimestamp(), request.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
		logger.info("Money added to the seler account");
		//sendTxToKp(sellerTx);
		response.setTx(sellerTx);
		response.setRedirectUrl(request.getSuccessUrl());
		response.setOutcome(TxStatus.SUCCESS);
		
		return response;
	}
	
	public PccResponseDTO handleSendMoneyTx(PccRequestDTO request, PccResponseDTO response) throws NotEnoughMoney{
		BankAccount buyerAccount = getBankAccountByCard(request.getPaymentCardRequest().getPan(), request.getPaymentCardRequest().getSecurityCode(), request.getPaymentCardRequest().getCardHolderName(), DateConverter.decode(request.getPaymentCardRequest().getValidUntil()));
		if(buyerAccount == null) {
			logger.error("Tx at buyer's account failed: not enough money at the account");
			Tx failedTx = saveTxLog(TxStatus.ERROR, request.getAmount(), "Not enough money at the account", null, null, request.getClientName(), request.getClientBankAccount(), response.getIssuerOrderId()
					, null, null, response.getAcquirerTimestamp(), response.getAcquirerOrderId());
			response.setStatus(TxStatus.ERROR);
			return response;
		}
		
		logger.info("handleSendMoneyTx: Tx at buyer's account started");
		boolean canReserve = canReserveMoney(buyerAccount, request.getAmount());
		
		response.setIssuerTimestamp(new Timestamp(System.currentTimeMillis()));
		response.setIssuerOrderId(generateUnique10Digit());
		
		if(!canReserve) {
			logger.error("Tx at buyer's account failed: not enough money at the account");
			Tx failedTx = saveTxLog(TxStatus.FAILED, request.getAmount(), "Not enough money at the account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), request.getClientName(), request.getClientBankAccount(), response.getIssuerOrderId()
					, null, null, response.getAcquirerTimestamp(), response.getAcquirerOrderId());
			response.setStatus(TxStatus.FAILED);
			return response;
			//throw new NotEnoughMoney();
		}
		
		buyerAccount.setBalance(buyerAccount.getBalance() - request.getAmount());
		saveAccount(buyerAccount);
		Tx buyerTx = saveTxLog(TxStatus.SUCCESS, request.getAmount(), "Money taken from buyer account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), request.getClientName(), request.getClientBankAccount(), response.getIssuerOrderId()
				, null, null, response.getAcquirerTimestamp(), response.getAcquirerOrderId());
		logger.info("Money taken from buyer account");
		response.setStatus(TxStatus.SUCCESS);
		
		
		return response;
	}
	
	public Object handleReceiveMoneyTx() {
		return null;
	}
	
	
	public PaymentCardResponseDTO tX(PaymentCardRequestDTO request, PaymentRequest req, PaymentCardResponseDTO response) throws PaymentRequestException, NotEnoughMoney, Exception{
		BankAccount sellerAccount = unityOfWork.getBankAccountRepository().findByBankAccountUsername(req.getMerchantUsername());
		if(sellerAccount == null) {
			throw new Exception();
		}
		
		try {
			
			boolean isAcquaerIssuerSame = isAcquaerIssuerSame(request);
			if(isAcquaerIssuerSame) {
				//response.setAcquirerTimestamp(DateConverter.encodeT(new Timestamp(System.currentTimeMillis())));
				response.setAcquirerTimestamp(new Timestamp(System.currentTimeMillis()));
				response.setAcquirerOrderId(generateUnique10Digit());
				BankAccount buyerAccount = getBankAccountByCard(request.getPan(), request.getSecurityCode(), request.getCardHolderName(), DateConverter.decode(request.getValidUntil()));
				if(buyerAccount == null) {
					throw new Exception();
				}
				
				response = handleTx(sellerAccount, buyerAccount, req, response);
				
				return response;
			}else {
				//PccRequestDTO newRequest = new PccRequestDTO(generateUnique10Digit(), new Timestamp(System.currentTimeMillis()), request);
				PccRequestDTO newRequest = new PccRequestDTO(generateUnique10Digit(), new Timestamp(System.currentTimeMillis()), req.getAmount(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), request);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<PccResponseDTO> pccResponse = restTemplate.postForEntity("https://localhost:8951/pay", newRequest, PccResponseDTO.class);
				PccResponseDTO pccResponseBody = pccResponse.getBody();
				switch (pccResponseBody.getStatus()) {
					case SUCCESS:
						response.setAcquirerOrderId(pccResponseBody.getAcquirerOrderId());
						//response.setAcquirerTimestamp(DateConverter.encodeT(pccResponseBody.getAcquirerTimestamp()));
						response.setAcquirerTimestamp(pccResponseBody.getAcquirerTimestamp());
						
						sellerAccount.setBalance(sellerAccount.getBalance() + req.getAmount());
						saveAccount(sellerAccount);
						Tx sellerTx = saveTxLog(TxStatus.SUCCESS, req.getAmount(), "Money added to the seler account", pccResponseBody.getClientName(),pccResponseBody.getClientBankAccount(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), req.getPaymentId()
								, req.getMerchantTimestamp(), req.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
						logger.info("Money added to the seler account");
						response.setTx(sellerTx);
						response.setRedirectUrl(req.getSuccessUrl());
						response.setOutcome(TxStatus.SUCCESS);
						//sendTxToKp(sellerTx);
						return response;
					case FAILED: {
						response.setAcquirerOrderId(pccResponseBody.getAcquirerOrderId());
						response.setAcquirerTimestamp(pccResponseBody.getAcquirerTimestamp());
						Tx failedTx = saveTxLog(TxStatus.FAILED, req.getAmount(), "Not enough money at the account", pccResponseBody.getClientName(),pccResponseBody.getClientBankAccount(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), req.getPaymentId()
								, req.getMerchantTimestamp(), req.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
						logger.info("Not enough money at the account");
						response.setTx(failedTx);
						response.setRedirectUrl(req.getFailedUrl());
						response.setOutcome(TxStatus.FAILED);
						return response;
						//throw new NotEnoughMoney();
					}
					case ERROR:{
						response.setAcquirerOrderId(pccResponseBody.getAcquirerOrderId());
						response.setAcquirerTimestamp(pccResponseBody.getAcquirerTimestamp());
						throw new Exception();			
					}
					default:
						response.setAcquirerOrderId(pccResponseBody.getAcquirerOrderId());
						response.setAcquirerTimestamp(pccResponseBody.getAcquirerTimestamp());
						throw new Exception();
				}
				
			}
			
//			response.setRedirectUrl(req.getSuccessUrl());
//			response.setOutcome(TxStatus.SUCCESS);
//			return response;
		} catch (Exception e) {
			Tx errorTx = saveTxLog(TxStatus.ERROR, req.getAmount(), "ERROR WITH PROCESSING CARD DATA or through paying process", "NONE" , "NONE", sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber(), req.getPaymentId()
					, req.getMerchantTimestamp(), req.getMerchantOrderId(), response.getAcquirerTimestamp(), response.getAcquirerOrderId());
			logger.error("ERROR WITH PROCESSING CARD DATA or through paying process");
			response.setTx(errorTx);
			response.setRedirectUrl(req.getErrorUrl());
			response.setOutcome(TxStatus.ERROR);
			return response;
		} 
	}
	
	private boolean isAcquaerIssuerSame(PaymentCardRequestDTO request) {
		String issuerBankPan = request.getPan().substring(1, 7);
		return bankPanNum.equals(issuerBankPan);
	}
	
	public Tx saveTxLog(TxStatus status, Float amountOfMoney, String txDescription, String senderName, String senderAccountNum, String recieverName, 
			String recieverAccountNum, long paymentId, Timestamp merchantTimestamp, Long merchantOrderId, Timestamp acquirerTimestamp, Long acquirerOrderId) {
		Tx tx = new Tx(new Timestamp(System.currentTimeMillis()), status, amountOfMoney, txDescription, senderName, 
				senderAccountNum, recieverName, recieverAccountNum, paymentId);
		Tx txx = new Tx(new Timestamp(System.currentTimeMillis()), status, amountOfMoney, txDescription, paymentId, senderName, senderAccountNum, recieverName, recieverAccountNum, 
				merchantTimestamp, merchantOrderId, acquirerTimestamp, acquirerOrderId);
		txx = unityOfWork.getTxRepository().save(txx);
		
				
		return txx;
	}
	
	public Tx sendTxToKp(Tx tx) {
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Send tx to kp");
		ResponseEntity<Tx> response;
		try {
			response = restTemplate.postForEntity("https://localhost:8763/card/saveTx", tx, Tx.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			return new Tx();
		}
	
		return response.getBody();
	}
	
	@Async
	public Tx testAsync(Tx tx) throws InterruptedException {
		Thread.sleep(10000);
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Send tx to kp");
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8763/test", String.class);
		
		return new Tx();
	}
	
	public Tx testSync(Tx tx) throws InterruptedException {
		Thread.sleep(10000);
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Send tx to kp");
		ResponseEntity<String> response =  restTemplate.getForEntity("https://localhost:8763/test", String.class);
		
		return new Tx();
	}
	
	private BankAccount saveAccount(BankAccount account) {
		return unityOfWork.getBankAccountRepository().save(account);
	}
	
	private BankAccount getBankAccountByCard(String pan, String securityCode, String cardHolderName, Date until) {
		//CardAccount account = unityOfWork.getCardAccountRepository().findByPanAndSecurityCodeAndCardHolderNameAndValidUntil(pan, securityCode, cardHolderName, until);
		CardAccount account = unityOfWork.getCardAccountRepository().findBySecurityCodeAndCardHolderNameAndValidUntil(securityCode, cardHolderName, until);
		
		if(!authService.authenticate(pan, account.getPan(), account.getSalt())) {
			return null;
		}
		
		return account.getBankAccount();
	}
	
	private long generateUnique10Digit() {
//		Random random = new Random(System.nanoTime());
//		long number = random.nextLong();
		Generator g = unityOfWork.getIdGeneratorRepository().save(new Generator());
		long number = g.getGeneratedId();
		return number;
	}

}
