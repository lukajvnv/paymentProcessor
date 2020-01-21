package com.project.scienceCenter.dto;

public class PaymentResponseDTO {

	private String paymentUrl;
	private long paymentId;
	
	private String url;//url kp-ovog fronta
	private String paypalId;

	
	public PaymentResponseDTO(String paymentUrl, long paymentId, String url) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
		this.url = url;
	}





	public PaymentResponseDTO(String paymentUrl, int paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}
	
	
	
	
	
	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public PaymentResponseDTO() {
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





	public String getPaypalId() {
		return paypalId;
	}





	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	
}
