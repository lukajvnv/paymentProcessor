package com.project.paymentRequestHandler.dto;

public class PaymentTypeFormFieldDto {
	
	private Long fieldId;
	
	private String fieldName;
	
	private String fieldTypeBack;
	
	private String fieldTypeFront;
	
	private Object fieldValue;

	public PaymentTypeFormFieldDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentTypeFormFieldDto(Long fieldId, String fieldName, String fieldTypeBack, String fieldTypeFront,
			Object fieldValue) {
		super();
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.fieldTypeBack = fieldTypeBack;
		this.fieldTypeFront = fieldTypeFront;
		this.fieldValue = fieldValue;
	}

	public PaymentTypeFormFieldDto(Long fieldId, String fieldName, String fieldTypeBack, String fieldTypeFront) {
		super();
		this.fieldId = fieldId;
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

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	

}
