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
	private BankInfoRepository bankInfoRepository;
	
	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	private TxRepository txRepository;

	public BankAccountRepository getBankAccountRepository() {
		return bankAccountRepository;
	}

	public BankInfoRepository getBankInfoRepository() {
		return bankInfoRepository;
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

}
