package com.project.payPalHandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payPalHandler.model.FieldMetadata;

public interface IFieldMetadataRepository extends JpaRepository<FieldMetadata, Long>{

}
