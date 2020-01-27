package com.project.scienceCenter.dto;

public class ShoppingCartDto {
	
	private long cartId;
	
	private long sellerId;

	public ShoppingCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartDto(long cartId) {
		super();
		this.cartId = cartId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	

}
