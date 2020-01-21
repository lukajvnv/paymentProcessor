package com.project.cardPaymentService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.TxStatus;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
	
	PaymentRequest findByPaymentIdAndMerchantUsername(long paymentId, String merchantUsername);
	PaymentRequest findByPaymentId(long paymentId);
	
//	@Query("SELECT p FROM PaymentRequest p WHERE p.requestStatus=?1 AND p.created > CURRENT_TIMESTAMP - 14400")
//    List<PaymentRequest> findAllByStatusNotPaid(TxStatus status);

    List<PaymentRequest> findByRequestStatus(TxStatus status);

}
