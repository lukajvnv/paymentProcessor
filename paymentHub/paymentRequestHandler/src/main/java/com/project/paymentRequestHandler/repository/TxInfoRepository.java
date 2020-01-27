package com.project.paymentRequestHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.model.TxInfo;

public interface TxInfoRepository extends JpaRepository<TxInfo, Long> {

	TxInfo findByOrderId(long orderId);
	TxInfo findByPaymentId(long paymentId);
	TxInfo findByOrderIdAndPaymentId(long orderId, long paymentId);
	TxInfo findByPaymentIdAndServiceWhoHandlePayment(long paymentId, String serviceWhoHandlePayment);


}
