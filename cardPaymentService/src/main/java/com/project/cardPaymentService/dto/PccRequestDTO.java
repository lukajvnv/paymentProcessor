package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

public class PccRequestDTO {
	
	private Long acquirerOrderId;
	private Timestamp acquirerTimestamp;
	
	private PaymentCardRequestDTO paymentCardRequest;

}
