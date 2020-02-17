package com.project.cardPaymentService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentService.model.Tx;

public interface TxRepository extends JpaRepository<Tx, Long>{

	Tx findByMerchantOrderId(Long orderId);
	Tx findByPaymentId(Long paymentId);
//	Tx findByMerchantOrderIdAndPaymentId(Long orderId, Long paymentId);
	List<Tx> findByMerchantOrderIdAndPaymentId(Long orderId, Long paymentId);

}
