package com.project.bitcoinHandler.repository;


import org.springframework.stereotype.Repository;

import com.project.bitcoinHandler.model.SellerBitcoinInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BitcoinRepository extends JpaRepository<SellerBitcoinInfo, Long> {
	
	SellerBitcoinInfo findByidMagazine(Long idMagazine);
	
}
