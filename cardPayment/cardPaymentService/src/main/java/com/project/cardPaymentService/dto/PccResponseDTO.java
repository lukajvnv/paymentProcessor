package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

import com.project.cardPaymentService.model.TxStatus;

public class PccResponseDTO {
	
	private TxStatus status;
	// private String outcome;
	
	private Long acquirerOrderId;
	private Timestamp acquirerTimestamp;
	private Long issuerOrderId;
	private Timestamp issuerTimestamp;
	
	private String clientName;
	private String clientBankAccount; 
	
	public PccResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PccResponseDTO(String outcome, Long acquirerOrderId, Timestamp acquirerTimestamp, Long issuerOrderId,
			Timestamp issuerTimestamp) {
		super();
		// this.outcome = outcome;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
	}

//	public String getOutcome() {
//		return outcome;
//	}
//
//	public void setOutcome(String outcome) {
//		this.outcome = outcome;
//	}

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

	public Long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Timestamp getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Timestamp issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public TxStatus getStatus() {
		return status;
	}

	public void setStatus(TxStatus status) {
		this.status = status;
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
	
	

}
