package com.project.payPalHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.payPalHandler.model.DbTransaction;

@Repository
public interface ITransactionRepository extends JpaRepository<DbTransaction, Long>{
	
	DbTransaction findByPaymentId(String paymentId);
}
