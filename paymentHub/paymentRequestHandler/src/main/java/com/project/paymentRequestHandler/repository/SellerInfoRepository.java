package com.project.paymentRequestHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.model.SellerInfo;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, Long> {

	SellerInfo findBySellerDBId(long id);
}
