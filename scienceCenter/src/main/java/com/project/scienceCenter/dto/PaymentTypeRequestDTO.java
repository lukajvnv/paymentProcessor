package com.project.scienceCenter.dto;

import javax.validation.constraints.NotNull;

public class PaymentTypeRequestDTO {

	@NotNull
	private Long sellerId;
	
	
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
