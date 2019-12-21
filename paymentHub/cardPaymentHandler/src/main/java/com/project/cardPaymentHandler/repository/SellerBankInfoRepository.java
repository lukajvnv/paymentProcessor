package com.project.cardPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentHandler.model.SellerBankInfo;

public interface SellerBankInfoRepository extends JpaRepository<SellerBankInfo, Long> {
	SellerBankInfo findBySellerIdentifier(Long id);
}
