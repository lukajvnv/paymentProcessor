package com.project.payPalHandler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payPalHandler.model.DbTransaction;
import com.project.payPalHandler.model.Subscription;
import com.project.payPalHandler.util.PaymentStatus;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {

	Subscription findOneById(Long id);

	Subscription findByToken(String token);
	
	List<Subscription> findAllByPaymentStatus(PaymentStatus status);
}
