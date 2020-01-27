package com.project.paymentRequestHandler.dto;

public class ShoppingCartDTO {
	
	private Long kPClientIdentifier;
	private Float totalAmount;
	
	public Long getkPClientIdentifier() {
		return kPClientIdentifier;
	}
	public void setkPClientIdentifier(Long kPClientIdentifier) {
		this.kPClientIdentifier = kPClientIdentifier;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public ShoppingCartDTO(Long kPClientIdentifier, Float totalAmount) {
		super();
		this.kPClientIdentifier = kPClientIdentifier;
		this.totalAmount = totalAmount;
	}
	public ShoppingCartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
