package com.project.cardPaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cardPaymentService.model.BankInfo;


public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {

}
