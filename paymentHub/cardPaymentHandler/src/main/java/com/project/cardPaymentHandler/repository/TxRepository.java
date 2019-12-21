package com.project.cardPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentHandler.model.Tx;

public interface TxRepository extends JpaRepository<Tx, Long> {

}
