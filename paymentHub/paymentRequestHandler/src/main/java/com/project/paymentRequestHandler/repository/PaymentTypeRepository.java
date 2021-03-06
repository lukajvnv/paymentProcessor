package com.project.paymentRequestHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}
