package com.project.payPalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payPalService.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {

}
