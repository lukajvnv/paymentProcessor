package com.project.cardPaymentHandler.repository;


import org.springframework.stereotype.Repository;

import com.project.cardPaymentHandler.model.SellerBankInfo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TestRepository extends JpaRepository<SellerBankInfo, Long> {

}
