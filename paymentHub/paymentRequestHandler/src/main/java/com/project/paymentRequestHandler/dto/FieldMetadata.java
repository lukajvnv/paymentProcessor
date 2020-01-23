package com.project.paymentRequestHandler.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class FieldMetadata {

	private Long fieldId;
	
	private String fieldName;
	
	private String fieldTypeBack;
	
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
