package com.project.customPaymentHandler.dto;

import javax.validation.constraints.NotNull;

public class PaymentRequestDTO {

	@NotNull
	private Long sellerId;
	
	@NotNull
	private Float amount;
	
	@NotNull
	private Long orderId;

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	
	
	
}
