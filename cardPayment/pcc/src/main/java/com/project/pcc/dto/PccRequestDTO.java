package com.project.pcc.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class PccRequestDTO {
	
	@NotNull
	private Long acquirerOrderId;
	
	@NotNull
	private Timestamp acquirerTimestamp;
	
	@NotNull
	private Float amount;
	
	private String clientName;
	private String clientBankAccount; 
	
	
	private PaymentCardRequestDTO paymentCardRequest;
	
	public PccRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PccRequestDTO(Long acquirerOrderId, Timestamp acquirerTimestamp, PaymentCardRequestDTO paymentCardRequest) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.paymentCardRequest = paymentCardRequest;
	}
	
	

	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public PaymentCardRequestDTO getPaymentCardRequest() {
		return paymentCardRequest;
	}

	public void setPaymentCardRequest(PaymentCardRequestDTO paymentCardRequest) {
		this.paymentCardRequest = paymentCardRequest;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientBankAccount() {
		return clientBankAccount;
	}

	public void setClientBankAccount(String clientBankAccount) {
		this.clientBankAccount = clientBankAccount;
	}

	public PccRequestDTO(@NotNull Long acquirerOrderId, Timestamp acquirerTimestamp, Float amount, String clientName,
			String clientBankAccount) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.amount = amount;
		this.clientName = clientName;
		this.clientBankAccount = clientBankAccount;
	}
	
	

}
