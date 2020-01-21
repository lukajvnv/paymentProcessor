package com.project.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pcc.model.BankInfo;

public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
	
	BankInfo findByBankPanNumber(String pan);

}
