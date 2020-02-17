package com.project.cardPaymentService.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentService.model.CardAccount;

public interface CardAccountRepository extends JpaRepository<CardAccount, Long> {
	CardAccount findByPanAndSecurityCodeAndCardHolderNameAndValidUntil(String pan, String securityCode, String cardholderName, Date validUntil);

	CardAccount findBySecurityCodeAndCardHolderNameAndValidUntil(String securityCode, String cardholderName, Date validUntil);

}
