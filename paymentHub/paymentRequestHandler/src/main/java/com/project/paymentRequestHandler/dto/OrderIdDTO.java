package com.project.paymentRequestHandler.dto;

public class OrderIdDTO {

	private Long orderId;
	
	private String kpUrl;

	public OrderIdDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderIdDTO(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getKpUrl() {
		return kpUrl;
	}

	public void setKpUrl(String kpUrl) {
		this.kpUrl = kpUrl;
	}
	
	
}
