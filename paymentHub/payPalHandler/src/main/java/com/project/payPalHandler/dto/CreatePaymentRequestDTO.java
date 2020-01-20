package com.project.payPalHandler.dto;

public class CreatePaymentRequestDTO {

	private String merchantName;
	
	private Long amount;
	
	private String redirectUrl;

	public CreatePaymentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreatePaymentRequestDTO(String merchantName, Long amount, String redirectUrl) {
		super();
		this.merchantName = merchantName;
		this.amount = amount;
		this.redirectUrl = redirectUrl;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
