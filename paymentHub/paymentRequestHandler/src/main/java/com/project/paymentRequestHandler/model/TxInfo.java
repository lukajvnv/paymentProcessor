package com.project.paymentRequestHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TxInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long txInfoId;
	
	@Column
	private Long orderId;
	
	@Column
	private Long paymentId;
	
	@Column
	private String serviceWhoHandlePayment;

	public TxInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TxInfo(Long orderId, Long paymentId, String serviceWhoHanlePayment) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.serviceWhoHandlePayment = serviceWhoHanlePayment;
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
	
	
}
