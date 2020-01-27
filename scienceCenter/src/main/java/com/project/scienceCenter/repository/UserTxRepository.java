package com.project.scienceCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.scienceCenter.model.UserA;
import com.project.scienceCenter.model.UserTx;

public interface UserTxRepository extends JpaRepository<UserTx, Long> {

	UserTx findBykPClientIdentifier(long kpIdentifier);
}
