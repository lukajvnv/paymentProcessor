package com.project.bitcoinHandler.dto;

import javax.validation.constraints.NotNull;

public class PaymentRequestDTO {

	@NotNull
	private Long sellerId;
	
	@NotNull
	private Float amount;

	public PaymentRequestDTO(Long sellerId, Float amount) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
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
	
	
	
}
