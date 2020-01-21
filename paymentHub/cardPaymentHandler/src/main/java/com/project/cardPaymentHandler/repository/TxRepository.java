package com.project.cardPaymentHandler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.model.TxStatus;

public interface TxRepository extends JpaRepository<Tx, Long> {

	Tx findByMerchantOrderId(Long orderId);
	Tx findByPaymentId(Long paymentId);
	Tx findByMerchantOrderIdAndPaymentId(Long orderId, Long paymentId);
	
	List<Tx> findByStatus(TxStatus status);

}
