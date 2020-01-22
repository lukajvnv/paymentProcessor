package com.project.cardPaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cardPaymentService.util.Generator;

public interface IdGeneratorRepository extends JpaRepository<Generator, Long> {

}
