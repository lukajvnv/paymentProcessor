package com.project.scienceCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.scienceCenter.model.UserTxItem;

public interface UserTxItemRepository extends JpaRepository<UserTxItem, Long> {

}
