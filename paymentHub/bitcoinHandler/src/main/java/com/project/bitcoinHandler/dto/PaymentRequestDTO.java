package com.project.bitcoinHandler.dto;

import javax.validation.constraints.NotNull;

public class PaymentRequestDTO {

	@NotNull
	private Long sellerId;
	
	@NotNull
	private Float amount;
	
	@NotNull
	private Long orderId;
	
	

	public PaymentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentRequestDTO(@NotNull Long sellerId, @NotNull Float amount, @NotNull Long orderId) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
		this.orderId = orderId;
	}

	public PaymentRequestDTO(Long sellerId, Float amount) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
