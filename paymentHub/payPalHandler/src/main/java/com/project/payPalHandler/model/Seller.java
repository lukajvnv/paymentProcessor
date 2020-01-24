package com.project.payPalHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seller {
	
	@Id
	@Column
	private Long sellerId;
	
	@Column
	private Long sellerIdentifier;  //FK 
	
	@Column
	private String email;
	
	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(Long sellerId, String email) {
		super();
		this.sellerId = sellerId;
		this.email = email;
	}
	
	

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}

	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}

}
