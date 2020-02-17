package com.project.payPalHandler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.payPalHandler.model.DbTransaction;
import com.project.payPalHandler.util.PaymentStatus;

@Repository
public interface ITransactionRepository extends JpaRepository<DbTransaction, Long>{
	
	DbTransaction findByPaymentId(String paymentId);
	
	List<DbTransaction> findAllByPaymentStatus(PaymentStatus status);
}
