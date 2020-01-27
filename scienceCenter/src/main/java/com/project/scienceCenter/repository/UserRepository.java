package com.project.scienceCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.scienceCenter.model.UserA;

public interface UserRepository extends JpaRepository<UserA, Long> {

}
