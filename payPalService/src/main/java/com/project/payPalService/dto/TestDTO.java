package com.project.payPalService.dto;

public class TestDTO {

	private String currency;
	
	private float amount;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public TestDTO(String currency, float amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}

	public TestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
