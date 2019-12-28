package com.project.payPalHandler.dto;

public class CreatePaymentResponseDTO {

	private Long paymentId;
	
	private String redirectUrl;

	public CreatePaymentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreatePaymentResponseDTO(Long paymentId, String redirectUrl) {
		super();
		this.paymentId = paymentId;
		this.redirectUrl = redirectUrl;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
