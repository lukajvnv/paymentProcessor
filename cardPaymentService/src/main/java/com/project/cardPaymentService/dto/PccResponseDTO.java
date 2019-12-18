package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

public class PccResponseDTO {
	
	private String outcome;
	
	private Long acquirerOrderId;
	private Timestamp acquirerTimestamp;
	private Long issuerOrderId;
	private Timestamp issuerTimestamp;

}
