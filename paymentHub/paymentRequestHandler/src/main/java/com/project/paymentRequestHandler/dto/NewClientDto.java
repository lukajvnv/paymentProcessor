package com.project.paymentRequestHandler.dto;

import java.util.Map;

public class NewClientDto {
	
	private long newClientId;
	private Map<Long, PaymentTypeFormDto> forms;
	
	public NewClientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewClientDto(long newClientId, Map<Long, PaymentTypeFormDto> forms) {
		super();
		this.newClientId = newClientId;
		this.forms = forms;
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

	
}
