package com.project.payPalService.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	@Column
	private UUID transactionId;
	
	@Column
	private String customerId;
	
	@Column
	private String sellerId;
	
	@Column
	private String price;
	
	@Column 
	private String currency;
	
	@Column
	private String payPalReference;
	
	@Column
	private String status;

	public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayPalReference() {
		return payPalReference;
	}

	public void setPayPalReference(String payPalReference) {
		this.payPalReference = payPalReference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Transaction(UUID transactionId, String customerId, String sellerId, String price, String currency,
			String payPalReference, String status) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.sellerId = sellerId;
		this.price = price;
		this.currency = currency;
		this.payPalReference = payPalReference;
		this.status = status;
	}

	public Transaction() {
		super();
	}	
	
}
