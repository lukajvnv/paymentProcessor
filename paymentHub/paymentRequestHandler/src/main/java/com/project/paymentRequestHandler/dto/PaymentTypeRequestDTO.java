package com.project.paymentRequestHandler.dto;

import javax.validation.constraints.NotNull;

public class PaymentTypeRequestDTO {

	@NotNull
	private Long sellerId;
	
	@NotNull
	private Long orderId; //random izgenerisani orderId po kojem ce se kasnije redirektovati na stranicu za placanje
	
	
	
	public PaymentTypeRequestDTO(@NotNull Long sellerId, @NotNull Long orderId) {
		super();
		this.sellerId = sellerId;
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public PaymentTypeRequestDTO(Long sellerId) {
		super();
		this.sellerId = sellerId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public PaymentTypeRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
