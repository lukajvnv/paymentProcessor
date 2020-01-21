package com.project.cardPaymentService.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PaymentCardRequestDTO {
	
	@NotBlank
	@Size(min=10, max=16)
	private String pan;
	
	@NotBlank
	@Size(min=3, max=4)
	private String securityCode;
	
	@NotBlank
	private String cardHolderName;
	
	@NotBlank
	private String validUntil;
	
//	@NotBlank
//	private String merchantUsername;
	
	@NotNull
	private Long paymentId;
	
	
	
	public PaymentCardRequestDTO(String pan, String securityCode, String cardHolderName, String validUntil
			/*,String merchantUsername*/, Long paymentId) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.validUntil = validUntil;
		//this.merchantUsername = merchantUsername;
		this.paymentId = paymentId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}
//	public String getMerchantUsername() {
//		return merchantUsername;
//	}
//	public void setMerchantUsername(String merchantUsername) {
//		this.merchantUsername = merchantUsername;
//	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	
	
}
