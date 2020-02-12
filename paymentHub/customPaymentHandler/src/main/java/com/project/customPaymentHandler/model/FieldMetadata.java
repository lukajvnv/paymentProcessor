package com.project.customPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FieldMetadata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long fieldId;
	
	@Column
	private String fieldName;
	
	@Column
	private String fieldTypeBack;
	
	@Column
	private String fieldTypeFront;

	public FieldMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldMetadata(Long fieldId, String fieldName, String fieldTypeBack, String fieldTypeFront) {
		super();
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.fieldTypeBack = fieldTypeBack;
		this.fieldTypeFront = fieldTypeFront;
	}

	public FieldMetadata(String fieldName, String fieldTypeBack, String fieldTypeFront) {
		super();
		this.fieldName = fieldName;
		this.fieldTypeBack = fieldTypeBack;
		this.fieldTypeFront = fieldTypeFront;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldTypeBack() {
		return fieldTypeBack;
	}

	public void setFieldTypeBack(String fieldTypeBack) {
		this.fieldTypeBack = fieldTypeBack;
	}

	public String getFieldTypeFront() {
		return fieldTypeFront;
	}

	public void setFieldTypeFront(String fieldTypeFront) {
		this.fieldTypeFront = fieldTypeFront;
	}
	
	
}
