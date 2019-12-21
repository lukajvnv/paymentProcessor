package com.project.cardPaymentService.dto;

import com.project.cardPaymentService.model.TxStatus;

public class PaymentValidationResponseDTO {
	
	private String paymentUrl;
	private long paymentId;
	
	private TxStatus txStatus;
	
	public PaymentValidationResponseDTO(String paymentUrl, int paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}
	
	public PaymentValidationResponseDTO(String paymentUrl, int paymentId, TxStatus status) {
		this(paymentUrl, paymentId);
		this.txStatus = status;
	}
	
	public PaymentValidationResponseDTO(TxStatus status) {
		this.txStatus = status;
	}
	
	public PaymentValidationResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public TxStatus getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(TxStatus txStatus) {
		this.txStatus = txStatus;
	}
	
	
}
