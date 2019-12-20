package com.project.cardPaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentService.model.Tx;

public interface TxRepository extends JpaRepository<Tx, Long>{

}
