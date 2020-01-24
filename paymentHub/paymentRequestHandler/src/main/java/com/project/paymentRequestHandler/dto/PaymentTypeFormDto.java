package com.project.paymentRequestHandler.dto;

import java.util.List;
import java.util.Map;

public class PaymentTypeFormDto {
	
//	private PaymentTypeDTO paymentType;
//	
//	private Map<String, PaymentTypeFormFieldDto> formFields;
//
//	public PaymentTypeFormDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public PaymentTypeFormDto(PaymentTypeDTO paymentType, Map<String, PaymentTypeFormFieldDto> formFields) {
//		super();
//		this.paymentType = paymentType;
//		this.formFields = formFields;
//	}
//
//	public PaymentTypeDTO getPaymentType() {
//		return paymentType;
//	}
//
//	public void setPaymentType(PaymentTypeDTO paymentType) {
//		this.paymentType = paymentType;
//	}
//
//	public Map<String, PaymentTypeFormFieldDto> getFormFields() {
//		return formFields;
//	}
//
//	public void setFormFields(Map<String, PaymentTypeFormFieldDto> formFields) {
//		this.formFields = formFields;
//	}
	
	private PaymentTypeDTO paymentType;
	
	private List<PaymentTypeFormFieldDto> formFields;

	public PaymentTypeFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentTypeFormDto(PaymentTypeDTO paymentType, List<PaymentTypeFormFieldDto> formFields) {
		super();
		this.paymentType = paymentType;
		this.formFields = formFields;
	}

	public PaymentTypeDTO getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeDTO paymentType) {
		this.paymentType = paymentType;
	}

	public List<PaymentTypeFormFieldDto> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<PaymentTypeFormFieldDto> formFields) {
		this.formFields = formFields;
	}

	

	
	
	
}
