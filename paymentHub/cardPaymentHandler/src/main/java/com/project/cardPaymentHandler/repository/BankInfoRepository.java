package com.project.cardPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentHandler.model.BankInfo;

public interface BankInfoRepository extends JpaRepository<BankInfo, Long>{

	BankInfo findByBankAccountInNationalBank(String string);
}
