package com.project.syncService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.syncService.model.WebshopClient;

public interface WebshopClientRepository extends JpaRepository<WebshopClient, Long> {

	WebshopClient findByClientHost(String host);
}
