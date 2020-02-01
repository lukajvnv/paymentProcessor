package com.project.payPalHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payPalHandler.model.Subscription;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {

	Subscription findOneById(Long id);

	Subscription findByToken(String token);
}
