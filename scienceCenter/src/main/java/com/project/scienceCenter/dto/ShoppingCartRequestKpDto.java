package com.project.scienceCenter.dto;

public class ShoppingCartRequestKpDto {
	
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
	public ShoppingCartRequestKpDto(Long kPClientIdentifier, Float totalAmount) {
		super();
		this.kPClientIdentifier = kPClientIdentifier;
		this.totalAmount = totalAmount;
	}
	public ShoppingCartRequestKpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
