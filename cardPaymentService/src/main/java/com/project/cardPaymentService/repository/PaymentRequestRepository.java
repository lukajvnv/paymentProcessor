package com.project.cardPaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentService.model.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
	
	PaymentRequest findByPaymentIdAndMerchantUsername(long paymentId, String merchantUsername);

}
