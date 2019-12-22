package com.project.cardPaymentService.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentCardResponseDTO;
import com.project.cardPaymentService.exception.NotEnoughMoney;
import com.project.cardPaymentService.exception.PaymentRequestException;
import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.CardAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.repository.UnityOfWork;
import com.project.cardPaymentService.util.DateConverter;

@Service
public class TransactionService {
	
	@Autowired
	private UnityOfWork unityOfWork;
	
	@Value("${bankPanNum}")
	private String bankPanNum;
	
	private Logger logger = LoggerFactory.getLogger(TransactionService.class);

	
	public boolean canReserveMoney(BankAccount account, Float money) {
		return account.getBalance() > money;
	}
	
	public Object handleTx(BankAccount sellerAccount, BankAccount buyerAccount, PaymentRequest request) throws NotEnoughMoney {
		logger.info("Tx at buyer's account started");
		boolean canReserve = canReserveMoney(buyerAccount, request.getAmount());
		if(!canReserve) {
			logger.error("Tx at buyer's account failed: not enough money at the account");
			saveTxLog(TxStatus.FAILED, request.getAmount(), "Not enough money at the account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber());
			throw new NotEnoughMoney();
		}
		
		buyerAccount.setBalance(buyerAccount.getBalance() - request.getAmount());
		saveAccount(buyerAccount);
		saveTxLog(TxStatus.SUCCESS, request.getAmount(), "Money taken from buyer account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber());
		logger.info("Money taken from buyer account");
		
		sellerAccount.setBalance(sellerAccount.getBalance() + request.getAmount());
		saveAccount(sellerAccount);
		saveTxLog(TxStatus.SUCCESS, request.getAmount(), "Money added to the seler account", buyerAccount.getBankAccountName(), buyerAccount.getBankAccountNumber(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber());
		logger.info("Money added to the seler account");
		
		return null;
	}
	
	public Object handleSendMoneyTx(BankAccount buyerAccount, PaymentRequest request) {
		return null;
	}
	
	public Object handleReceiveMoneyTx() {
		return null;
	}
	
	
	public PaymentCardResponseDTO tX(PaymentCardRequestDTO request, PaymentRequest req, PaymentCardResponseDTO response) throws PaymentRequestException, NotEnoughMoney{
		try {
			BankAccount sellerAccount = unityOfWork.getBankAccountRepository().findByBankAccountUsername(req.getMerchantUsername());
			
			boolean isAcquaerIssuerSame = isAcquaerIssuerSame(request);
			if(isAcquaerIssuerSame) {
				response.setAcquirerTimestamp(DateConverter.encodeT(new Timestamp(System.currentTimeMillis())));
				response.setAcquirerOrderId(generatePaymentId());
				BankAccount buyerAccount = getBankAccountByCard(request);
				Object obj = handleTx(sellerAccount, buyerAccount, req);
			}
			
			
			response.setRedirectUrl(req.getSuccessUrl());
			response.setOutcome(TxStatus.SUCCESS.toString());
			return response;
		} catch (NotEnoughMoney e) {
			response.setRedirectUrl(req.getFailedUrl());
			response.setOutcome(TxStatus.FAILED.toString());
			return response;
		} catch (Exception e) {
			response.setRedirectUrl(req.getErrorUrl());
			response.setOutcome(TxStatus.ERROR.toString());
			return response;
		} 
	}
	
	private boolean isAcquaerIssuerSame(PaymentCardRequestDTO request) {
		String issuerBankPan = request.getPan().substring(1, 7);
		return bankPanNum.equals(issuerBankPan);
	}
	
	private Tx saveTxLog(TxStatus status, Float amountOfMoney, String txDescription, String senderName, String senderAccountNum, String recieverName, String recieverAccountNum) {
		Tx tx = new Tx(new Timestamp(System.currentTimeMillis()), status, amountOfMoney, txDescription, senderName, senderAccountNum, recieverName, recieverAccountNum);
		return unityOfWork.getTxRepository().save(tx);
	}
	
	private BankAccount saveAccount(BankAccount account) {
		return unityOfWork.getBankAccountRepository().save(account);
	}
	
	private BankAccount getBankAccountByCard(PaymentCardRequestDTO request) {
		Date until = DateConverter.decode(request.getValidUntil());
		CardAccount account = unityOfWork.getCardAccountRepository().findByPanAndSecurityCodeAndCardHolderNameAndValidUntil(request.getPan(), request.getSecurityCode(), request.getCardHolderName(), until);
		return account.getBankAccount();
	}
	
	private long generatePaymentId() {
		Random random = new Random(System.nanoTime());
		long randomInt = random.nextLong();
		return randomInt;
	}

}
