package com.project.bitcoinHandler.dto;

import com.project.bitcoinHandler.util.TxStatus;
import com.project.bitcoinHandler.util.TxStatusReqHandler;

public class TxInfoDto {

	
	private Long txInfoId;
	
	
	private Long orderId;
	
	
	private Integer paymentId;
	
	private TxStatusReqHandler status;
	
	private String serviceWhoHandlePayment;

	public TxInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TxInfoDto(Long orderId, Integer paymentId, String serviceWhoHanlePayment) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.serviceWhoHandlePayment = serviceWhoHanlePayment;
	}
	
	

	public TxInfoDto(Integer paymentId, TxStatusReqHandler status, String serviceWhoHandlePayment) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.serviceWhoHandlePayment = serviceWhoHandlePayment;
	}

	public TxInfoDto(Long orderId, Integer paymentId, TxStatusReqHandler status, String serviceWhoHandlePayment) {
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

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getServiceWhoHandlePayment() {
		return serviceWhoHandlePayment;
	}

	public void setServiceWhoHandlePayment(String serviceWhoHandlePayment) {
		this.serviceWhoHandlePayment = serviceWhoHandlePayment;
	}

	public TxStatusReqHandler getStatus() {
		return status;
	}

	public void setStatus(TxStatusReqHandler status) {
		this.status = status;
	}
	
	
}
