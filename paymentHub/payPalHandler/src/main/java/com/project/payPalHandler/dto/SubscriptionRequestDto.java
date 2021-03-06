package com.project.payPalHandler.dto;

public class SubscriptionRequestDto {

	private Long sellerId;

    private Float amount;

    private String redirectUrl;

//    private String subject;

    private String frequency;

    private String interval;

    private Integer cycles;
    
    private Long orderId;



	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

//	public String getSubject() {
//		return subject;
//	}
//
//	public void setSubject(String subject) {
//		this.subject = subject;
//	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Integer getCycles() {
		return cycles;
	}

	public void setCycles(Integer cycles) {
		this.cycles = cycles;
	}

	public SubscriptionRequestDto(Long sellerId, Float amount, String redirectUrl, String frequency,
			String interval, Integer cycles) {
		super();
		this.sellerId = sellerId;
		this.amount = amount;
		this.redirectUrl = redirectUrl;
//		this.subject = subject;
		this.frequency = frequency;
		this.interval = interval;
		this.cycles = cycles;
	}

	public SubscriptionRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
    
    
}
