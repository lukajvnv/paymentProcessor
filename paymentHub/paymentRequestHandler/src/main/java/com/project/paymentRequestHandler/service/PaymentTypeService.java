package com.project.paymentRequestHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	public PaymentType saveNewPaymentType(PaymentType entity) {
		return paymentTypeRepository.save(entity);
	}
}
