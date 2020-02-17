package com.project.payPalHandler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.project.payPalHandler.util.PaymentStatus;

@Entity
public class Subscription {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String planId;

    @ManyToOne
    private Seller seller;

    private String token;
    
    private Float amount;

//    private String subject;

    private String frequency;

    private String frequencyInterval;

    private Integer cycles;

    private String redirectUrl;
    
    private PaymentStatus paymentStatus;
    
    private Long payId;

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Subscription(Long id, String planId, Seller seller, String token, Float amount, String frequency,
			String frequencyInterval, Integer cycles, String redirectUrl, PaymentStatus paymentStatus) {
		super();
		this.id = id;
		this.planId = planId;
		this.seller = seller;
		this.token = token;
		this.amount = amount;
		this.frequency = frequency;
		this.frequencyInterval = frequencyInterval;
		this.cycles = cycles;
		this.redirectUrl = redirectUrl;
		this.paymentStatus = paymentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
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

	public String getFrequencyInterval() {
		return frequencyInterval;
	}

	public void setFrequencyInterval(String frequencyInterval) {
		this.frequencyInterval = frequencyInterval;
	}

	public Integer getCycles() {
		return cycles;
	}

	public void setCycles(Integer cycles) {
		this.cycles = cycles;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Subscription(Long id, String planId, Seller seller, Float amount, 
			String frequency, String frequencyInterval, Integer cycles, String redirectUrl, String token) {
		super();
		this.id = id;
		this.planId = planId;
		this.seller = seller;
		this.amount = amount;
//		this.subject = subject;
		this.frequency = frequency;
		this.frequencyInterval = frequencyInterval;
		this.cycles = cycles;
		this.redirectUrl = redirectUrl;
		this.token = token;
	}

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}
    
    

}
