package com.project.cardPaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cardPaymentService.model.BankAccount;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	
	BankAccount findByBankAccountUsername(String username);

}
