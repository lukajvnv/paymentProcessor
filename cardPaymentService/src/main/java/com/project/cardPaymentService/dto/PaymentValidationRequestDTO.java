package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

public class PaymentValidationRequestDTO {

	private String merchantId;
	private String merchantPassword;
	private Float amount;
	private Long merchantOrderId;
	
	private Timestamp merchantTimeStamp; //ok?
	
	private String successUrl;
	private String failedUrl;
	private String errorUrl;
}
