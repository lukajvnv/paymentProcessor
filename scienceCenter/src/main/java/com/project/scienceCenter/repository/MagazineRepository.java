package com.project.scienceCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.scienceCenter.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long>{

}
