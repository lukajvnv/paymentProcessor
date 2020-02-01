package com.project.payPalHandler.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentRequestDTO {

	private Long sellerId;
	
	private Float amount;
	
	private String url;
	
	

	public PaymentRequestDTO(Long sellerId, Float amount) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
	}
	
	

	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}



	public PaymentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PaymentRequestDTO(@NotNull Long sellerId, @NotNull Float amount, @NotBlank String url) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
		this.url = url;
	}
	
	
	
}