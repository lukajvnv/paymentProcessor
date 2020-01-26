package com.project.paymentRequestHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.util.Generator;

public interface IdGeneratorRepository extends JpaRepository<Generator, Long> {

}
