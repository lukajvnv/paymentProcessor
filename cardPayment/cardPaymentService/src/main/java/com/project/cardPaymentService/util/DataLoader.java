package com.project.cardPaymentService.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.CardAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.repository.UnityOfWork;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private UnityOfWork unityOfWork;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		createInitialPaymentRequest();
		// createCardAccount();
		createBankAccount();
		createTx();
		
		testQuery();
		
//		AuthorizationService s = new AuthorizationService();
//		s.generateSensitiveData();
	}
	
	private void createInitialPaymentRequest() {
		@SuppressWarnings("deprecation")
		Timestamp timestamp = new Timestamp(120, 11, 19, 1, 20, 8, 1);
		PaymentRequest req1 = new PaymentRequest(-1L, "casopisA", 4444444f, 5151L, timestamp, "a", "kfalj", "jflka", TxStatus.SUCCESS);
		req1.setCreated(timestamp);
		req1.setPaymentId(799806098L);
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp2 = new Timestamp(120, 0, 21, 1, 43, 8, 1);
		PaymentRequest req2 = new PaymentRequest(-1L, "casopisA", 4444f, 5151L, timestamp2, "b", "kfalj", "jflka", TxStatus.UNKNOWN);
		req2.setCreated(timestamp2);
		req2.setPaymentId(799836098L);
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp3 = new Timestamp(120, 0, 21, 1, 47, 4, 1);
		PaymentRequest req3 = new PaymentRequest(-1L, "casopisA", 4444f, 5151L, timestamp3, "c", "kfalj", "jflka", TxStatus.UNKNOWN);
		req3.setCreated(timestamp3);
		req3.setPaymentId(796806098L);
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp4 = new Timestamp(120, 0, 20, 19, 20, 4, 1);
		PaymentRequest req4 = new PaymentRequest(-1L, "casopisA", 4444f, 5151L, timestamp4, "d", "kfalj", "jflka", TxStatus.SUCCESS);
		req4.setCreated(timestamp4);
		req4.setPaymentId(799801298L);
		
		
		unityOfWork.getPaymentRequestRepository().save(req1);
		unityOfWork.getPaymentRequestRepository().save(req2);
		unityOfWork.getPaymentRequestRepository().save(req3);
		unityOfWork.getPaymentRequestRepository().save(req4);

	}
	
	private void createCardAccount() {
		CardAccount acc1 = new CardAccount();
		CardAccount acc2 = new CardAccount();
	}
	
	private void createBankAccount() {
		BankAccount bank1 = new BankAccount("745-578979123-45", "Casopis A", "casopisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=", "8bflvLaZDvLPwjLjf9Ns2g==", 4444f);
		unityOfWork.getBankAccountRepository().save(bank1);
		
		BankAccount bank2 = new BankAccount("745-556321789-54", "Casopis B", "casopisB", "okXaW4aDRLzw1BD2L/s+JkQJtwFOOxmLZwt8RiUHAyM=", "sPssSO3OuKXATln+TBLtZA==", 10000f);
		unityOfWork.getBankAccountRepository().save(bank2);
		
		BankAccount bank3 = new BankAccount("745-56355589-54", "Casopis C", "casopisC", "SDLcsfaocMjMgueNJ9BckoXNqYHWE9Lr5GdbZGVAcLc=", "fVEo7X9RaBCTomdT/wYf1g==", 555f);
		unityOfWork.getBankAccountRepository().save(bank3);
		
		BankAccount bank4 = new BankAccount("745-23544458-88", "kupac A", "kupacA", "q4N1VDks+NhaTUEkTHUeBuR/eXp9tLFV+XEaUY0joA4=", "BdIDr4w0iTugtZzP7ET9kQ==", 450f);
		unityOfWork.getBankAccountRepository().save(bank4);

		
		@SuppressWarnings("deprecation")
		CardAccount acc1 = new CardAccount("4512365653214568", "123", "Casopis A", new Date(122, 11, 3), bank1);
		unityOfWork.getCardAccountRepository().save(acc1);
		
		@SuppressWarnings("deprecation")
		CardAccount acc2 = new CardAccount("5512365555555", "4444", "Casopis B", new Date(121, 11, 19), bank2);
		unityOfWork.getCardAccountRepository().save(acc2);
		
		@SuppressWarnings("deprecation")
		CardAccount acc3 = new CardAccount("5512365444555", "3333", "kupac A", new Date(121, 11, 19), bank4);
		unityOfWork.getCardAccountRepository().save(acc3);
		
		
		//Druga banka
		
//		BankAccount bank5 = new BankAccount("456-54546-56", "Kupac B", "kupacB", "zoz9ZF2VdOLaQNO0oLdRmRnqTKxJ9WHOEUzoSDsAMaw=", "4Fr7g9LqptWsGlczBsaJBA==", 4544f);
//		unityOfWork.getBankAccountRepository().save(bank5);
//		
//		BankAccount bank6 = new BankAccount("456-2345412-99", "Kupac C", "kupacC", "xmVtrLS21ncix83tYo/tkLlI1aJarmN1K+NSf6UYRXE=", "2rMbsEFiFtUevHw8Isvi6w==", 10000f);
//		unityOfWork.getBankAccountRepository().save(bank6);
//		
//		BankAccount bank7 = new BankAccount("456-23545121-66", "Kupac D", "kupacD", "svL20fraOYf4HzH0kWYPXummIOgI/v6oDy6bgBIgPmQ=", "PCBezlaQsNg/gaiU3G5Hxw==", 555f);
//		unityOfWork.getBankAccountRepository().save(bank7);
//		
//		
//
//		
//		@SuppressWarnings("deprecation")
//		CardAccount acc4 = new CardAccount("4864236653214568", "888", "Kupac B", new Date(122, 11, 3), bank5);
//		unityOfWork.getCardAccountRepository().save(acc4);
//		
//		@SuppressWarnings("deprecation")
//		CardAccount acc5 = new CardAccount("5864236555555", "777", "Kupac C", new Date(123, 5, 5), bank6);
//		unityOfWork.getCardAccountRepository().save(acc5);
//		
//		@SuppressWarnings("deprecation")
//		CardAccount acc6 = new CardAccount("5864236444555", "9996", "Kupac D", new Date(121, 11, 19), bank7);
//		unityOfWork.getCardAccountRepository().save(acc6);
	}
	
	private void createTx() {
		@SuppressWarnings("deprecation")
		Timestamp timestamp1 = new Timestamp(120, 11, 19, 1, 20, 8, 1);
		long paymentId1 = 1234567896l;
		long merchantOrderId1 = 4561230258l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp2 = new Timestamp(120, 0, 21, 1, 43, 8, 1);
		long paymentId2 = 1536987452l;
		long merchantOrderId2 = 3698527530l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp3 = new Timestamp(120, 0, 21, 1, 47, 4, 1);
		long paymentId3 = 1237414582l;
		long merchantOrderId3 = 4522230258l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp4 = new Timestamp(120, 0, 20, 19, 20, 4, 1);
		long paymentId4 = 4234567896l;
		long merchantOrderId4 = 4563330258l;
		
		Tx tx1 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.FAILED, 500f, "txDescription", paymentId1, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp1, merchantOrderId1, timestamp1, merchantOrderId1);
		Tx tx2 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.ERROR, 500f, "txDescription", paymentId2, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp2, merchantOrderId2, timestamp2, merchantOrderId2);
		Tx tx3 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.SUCCESS, 500f, "txDescription", paymentId3, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp3, merchantOrderId3, timestamp3, merchantOrderId3);
		Tx tx4 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.UNKNOWN, 500f, "txDescription", paymentId4, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp4, merchantOrderId4, timestamp4, merchantOrderId4);
	
		unityOfWork.getTxRepository().save(tx1);
		unityOfWork.getTxRepository().save(tx2);
		unityOfWork.getTxRepository().save(tx3);
		unityOfWork.getTxRepository().save(tx4);
	}
	
	private void testQuery() {
		//300K -< 5min
//		List<PaymentRequest> list = unityOfWork.getPaymentRequestRepository().findByRequestStatus(TxStatus.UNKNOWN);
		long now = System.currentTimeMillis();
		List<PaymentRequest> list = unityOfWork.getPaymentRequestRepository().findByRequestStatus(TxStatus.UNKNOWN).stream().filter(r -> now - r.getCreated().getTime() < 300000).collect(Collectors.toList());
       // System.out.println("fa");
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		System.out.println(now.getTime() - list.get(0).getCreated().getTime()); 
	}

}
