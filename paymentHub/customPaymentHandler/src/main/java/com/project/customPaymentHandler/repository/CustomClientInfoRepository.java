package com.project.customPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.customPaymentHandler.model.CustomSellerClientInfo;

public interface CustomClientInfoRepository extends JpaRepository<CustomSellerClientInfo, Long>{

	CustomSellerClientInfo findBySellerIdentifier(Long id);

}
