package com.project.scienceCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.model.MagazineEdition;

public interface MagazineEditionRepository extends JpaRepository<MagazineEdition, Long> {

//	MagazineEdition findByMagazineOrderByPublishingDateDesc(Magazine magazine);
	 List<MagazineEdition> findByMagazineOrderByPublishingDateDesc(Magazine magazine);

}
