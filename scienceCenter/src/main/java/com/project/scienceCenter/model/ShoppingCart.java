package com.project.scienceCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private Long sellerId;
	
	@Column
	private Double totalAmount;
	
	@Column
	private String url;
	
	

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(Long id, Long sellerId, Double totalAmount) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.totalAmount = totalAmount;
	}

	public ShoppingCart(Long id, Long sellerId, Double totalAmount, String url) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.totalAmount = totalAmount;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
	
}
