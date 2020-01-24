package com.project.paymentRequestHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>  {

	ShoppingCart findBySellerId(Long id);
}
