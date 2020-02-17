package com.project.paymentRequestHandler.dto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

public class NewClientDto {
	
	@NotNull
	private long newClientId;
	private Map<Long, PaymentTypeFormDto> forms;
	private List<PaymentTypeDTO> paymentTypes;
	
	public NewClientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewClientDto(long newClientId, Map<Long, PaymentTypeFormDto> forms) {
		super();
		this.newClientId = newClientId;
		this.forms = forms;
	}
	
	

	public NewClientDto(long newClientId, List<PaymentTypeDTO> paymentTypes) {
		super();
		this.newClientId = newClientId;
		this.paymentTypes = paymentTypes;
	}

	public long getNewClientId() {
		return newClientId;
	}

	public void setNewClientId(long newClientId) {
		this.newClientId = newClientId;
	}

	public Map<Long, PaymentTypeFormDto> getForms() {
		return forms;
	}

	public void setForms(Map<Long, PaymentTypeFormDto> forms) {
		this.forms = forms;
	}

	public List<PaymentTypeDTO> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentTypeDTO> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	
}
