package com.project.bitcoinHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bitcoinHandler.model.Tx;

@Repository
public interface TxRepository extends JpaRepository<Tx, Long> {
	Tx findByusername(Integer username);
}
