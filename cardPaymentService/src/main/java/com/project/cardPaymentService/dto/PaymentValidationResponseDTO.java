package com.project.cardPaymentService.dto;

public class PaymentValidationResponseDTO {
	
	private String paymentUrl;
	private long paymentId;
	
	
	public PaymentValidationResponseDTO(String paymentUrl, int paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}
	public PaymentValidationResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
