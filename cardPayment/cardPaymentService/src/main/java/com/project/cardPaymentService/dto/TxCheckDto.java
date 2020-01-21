package com.project.cardPaymentService.dto;

public class TxCheckDto {
	
	private long paymentId;
	private long merchantOrderId;
	
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public long getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	
	public TxCheckDto(long paymentId, long merchantOrderId) {
		super();
		this.paymentId = paymentId;
		this.merchantOrderId = merchantOrderId;
	}
	
	

}
