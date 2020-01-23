package com.project.cardPaymentHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cardPaymentHandler.repository.BankInfoRepository;
import com.project.cardPaymentHandler.repository.FieldMetadataRepository;
import com.project.cardPaymentHandler.repository.IdGeneratorRepository;
import com.project.cardPaymentHandler.repository.SellerBankInfoRepository;
import com.project.cardPaymentHandler.repository.TxRepository;

@Service
public class UnityOfWork {

	@Autowired
	private BankInfoRepository bankInfoRepository;
	
	@Autowired
	private SellerBankInfoRepository sellerBankInfoRepository;
	
	@Autowired
	private TxRepository txRepository;
	
	@Autowired
	private IdGeneratorRepository idGeneratorRepository;
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;

	public BankInfoRepository getBankInfoRepository() {
		return bankInfoRepository;
	}

	public SellerBankInfoRepository getSellerBankInfoRepository() {
		return sellerBankInfoRepository;
	}

	public TxRepository getTxRepository() {
		return txRepository;
	}

	public IdGeneratorRepository getIdGeneratorRepository() {
		return idGeneratorRepository;
	}

	public FieldMetadataRepository getFieldMetadataRepository() {
		return fieldMetadataRepository;
	}
	
}
