package com.project.customPaymentHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.customPaymentHandler.util.Generator;

public interface IdGeneratorRepository extends JpaRepository<Generator, Long> {

}
