package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

public class PaymentCardResponseDTO {
	
	private PccResponseDTO pccResponse;
	
	private Long paymentId;
	private Long merchantOrderId;
	private Long acquirerOrderId;
	private Timestamp acquirerTimestamp;
	
	private String outcome;

}
