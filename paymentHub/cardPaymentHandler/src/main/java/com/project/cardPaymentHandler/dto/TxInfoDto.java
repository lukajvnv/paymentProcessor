package com.project.cardPaymentHandler.dto;

import com.project.cardPaymentHandler.model.TxStatus;


public class TxInfoDto {

	
	private Long txInfoId;
	
	
	private Long orderId;
	
	
	private Long paymentId;
	
	private TxStatus status;
	
	private String serviceWhoHandlePayment;

	public TxInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TxInfoDto(Long orderId, Long paymentId, String serviceWhoHanlePayment) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.serviceWhoHandlePayment = serviceWhoHanlePayment;
	}
	
	

	public TxInfoDto(Long paymentId, TxStatus status, String serviceWhoHandlePayment) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.serviceWhoHandlePayment = serviceWhoHandlePayment;
	}

	public TxInfoDto(Long orderId, Long paymentId, TxStatus status, String serviceWhoHandlePayment) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.status = status;
		this.serviceWhoHandlePayment = serviceWhoHandlePayment;
	}

	public Long getTxInfoId() {
		return txInfoId;
	}

	public void setTxInfoId(Long txInfoId) {
		this.txInfoId = txInfoId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getServiceWhoHandlePayment() {
		return serviceWhoHandlePayment;
	}

	public void setServiceWhoHandlePayment(String serviceWhoHandlePayment) {
		this.serviceWhoHandlePayment = serviceWhoHandlePayment;
	}

	public TxStatus getStatus() {
		return status;
	}

	public void setStatus(TxStatus status) {
		this.status = status;
	}
	
	
}
