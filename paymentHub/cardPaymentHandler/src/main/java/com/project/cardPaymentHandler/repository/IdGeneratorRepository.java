package com.project.cardPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentHandler.util.Generator;

public interface IdGeneratorRepository extends JpaRepository<Generator, Long> {

}
