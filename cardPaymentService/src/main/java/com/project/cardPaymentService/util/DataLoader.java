package com.project.cardPaymentService.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.CardAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.repository.UnityOfWork;
import com.project.cardPaymentService.service.AuthorizationService;

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
		
//		AuthorizationService s = new AuthorizationService();
//		s.generateSensitiveData();
	}
	
	private void createInitialPaymentRequest() {
		@SuppressWarnings("deprecation")
		Timestamp timestamp = new Timestamp(2019, 11, 19, 1, 20, 8, 1);
		PaymentRequest req1 = new PaymentRequest(-1L, "casopisA", 4444f, 5151L, timestamp, "fka", "kfalj", "jflka");
		req1.setPaymentId(799806098L);
		
		
		//unityOfWork.getPaymentRequestRepository().save(req1);
	}
	
	private void createCardAccount() {
		CardAccount acc1 = new CardAccount();
		CardAccount acc2 = new CardAccount();
	}
	
	private void createBankAccount() {
		BankAccount bank1 = new BankAccount("555-54546-56", "Casopis A", "casopisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=", "8bflvLaZDvLPwjLjf9Ns2g==", 4444f);
		unityOfWork.getBankAccountRepository().save(bank1);
		
		BankAccount bank2 = new BankAccount("451-2345412-99", "Casopis B", "casopisB", "okXaW4aDRLzw1BD2L/s+JkQJtwFOOxmLZwt8RiUHAyM=", "sPssSO3OuKXATln+TBLtZA==", 10000f);
		unityOfWork.getBankAccountRepository().save(bank2);
		
		BankAccount bank3 = new BankAccount("785-23545121-66", "Casopis C", "casopisC", "SDLcsfaocMjMgueNJ9BckoXNqYHWE9Lr5GdbZGVAcLc=", "fVEo7X9RaBCTomdT/wYf1g==", 555f);
		unityOfWork.getBankAccountRepository().save(bank3);
		
		BankAccount bank4 = new BankAccount("785-23544458-88", "kupac A", "kupacA", "q4N1VDks+NhaTUEkTHUeBuR/eXp9tLFV+XEaUY0joA4=", "BdIDr4w0iTugtZzP7ET9kQ==", 450f);
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
	}

}
