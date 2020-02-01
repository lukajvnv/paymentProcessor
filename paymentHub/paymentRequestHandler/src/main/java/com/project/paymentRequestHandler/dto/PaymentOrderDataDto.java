package com.project.paymentRequestHandler.dto;

import com.project.paymentRequestHandler.model.ShoppingCart;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;

public class PaymentOrderDataDto {

	private ShoppingCart shoppingCart;
	private PaymentTypeResponseDTO paymentTypes;
	public PaymentOrderDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentOrderDataDto(ShoppingCart shoppingCart, PaymentTypeResponseDTO paymentTypes) {
		super();
		this.shoppingCart = shoppingCart;
		this.paymentTypes = paymentTypes;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public PaymentTypeResponseDTO getPaymentTypes() {
		return paymentTypes;
	}
	public void setPaymentTypes(PaymentTypeResponseDTO paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
	
}
