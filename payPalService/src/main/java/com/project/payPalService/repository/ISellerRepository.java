package com.project.payPalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payPalService.model.Seller;

public interface ISellerRepository extends JpaRepository<Seller, Long> {
	

}
