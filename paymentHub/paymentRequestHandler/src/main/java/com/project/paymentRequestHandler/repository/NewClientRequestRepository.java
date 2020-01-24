package com.project.paymentRequestHandler.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentRequestHandler.model.NewClientRequest;

public interface NewClientRequestRepository extends JpaRepository<NewClientRequest, Long> {

}
