package com.project.payPalHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.payPalHandler.model.Seller;

@Repository
public interface ISellerRepository extends JpaRepository<Seller, Long>{
	
	Seller findBySellerId(Long sellerId);
}
