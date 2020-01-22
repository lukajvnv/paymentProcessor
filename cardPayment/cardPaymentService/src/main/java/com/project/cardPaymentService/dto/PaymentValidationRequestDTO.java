package com.project.cardPaymentService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentValidationRequestDTO {

	@NotBlank
	private String merchantId;
	
	@NotBlank
	private String merchantPassword;
	
	@NotNull
	private Float amount;
	
	@NotNull
	private Long merchantOrderId;
	
	@NotBlank
	private String merchantTimeStamp; //ok?
	
	@NotBlank
	private String successUrl;
	
	@NotBlank
	private String failedUrl;
	
	@NotBlank
	private String errorUrl;
	
	
	
	public PaymentValidationRequestDTO(String merchantId, String merchantPassword, Float amount, Long merchantOrderId,
			String merchantTimeStamp, String successUrl, String failedUrl, String errorUrl) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimeStamp = merchantTimeStamp;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
	}
	public PaymentValidationRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Long getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public String getMerchantTimeStamp() {
		return merchantTimeStamp;
	}
	public void setMerchantTimeStamp(String merchantTimeStamp) {
		this.merchantTimeStamp = merchantTimeStamp;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getFailedUrl() {
		return failedUrl;
	}
	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}
	public String getErrorUrl() {
		return errorUrl;
	}
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	
}
