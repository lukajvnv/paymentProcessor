package com.project.paymentRequestHandler.model;

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
	private Double totalAmount;
	
	@Column
	private Long sellerId;
	
	@Column
	private Long orderId;

	
	
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(Long id, Double totalAmount, Long sellerId, Long orderId) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.sellerId = sellerId;
		this.orderId = orderId;
	}

	public ShoppingCart(Long id, Double totalAmount, Long sellerId) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.sellerId = sellerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	

	
}
