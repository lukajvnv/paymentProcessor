package com.project.cardPaymentService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnityOfWork {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Autowired
	private CardAccountRepository cardAccountRepository;
	
	
	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	private TxRepository txRepository;
	
	@Autowired
	private IdGeneratorRepository idGeneratorRepository;

	public BankAccountRepository getBankAccountRepository() {
		return bankAccountRepository;
	}

	
	public PaymentRequestRepository getPaymentRequestRepository() {
		return paymentRequestRepository;
	}

	public CardAccountRepository getCardAccountRepository() {
		return cardAccountRepository;
	}

	public TxRepository getTxRepository() {
		return txRepository;
	}


	public IdGeneratorRepository getIdGeneratorRepository() {
		return idGeneratorRepository;
	}


	public void setIdGeneratorRepository(IdGeneratorRepository idGeneratorRepository) {
		this.idGeneratorRepository = idGeneratorRepository;
	}

}
