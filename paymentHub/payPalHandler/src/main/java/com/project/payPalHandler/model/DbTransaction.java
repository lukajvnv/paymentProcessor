package com.project.payPalHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DbTransaction {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private Long id;

	@Column
    private String paymentId;

    @ManyToOne
    private Seller seller;

    @Column
    private String amount;

    @Column
    private String redirectUrl;

    @Column
    private Boolean successful;
    
    @Column
    private String currency;

	public DbTransaction(Long id, String paymentId, Seller seller, String amount, String redirectUrl,
			Boolean successful, String currency) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.seller = seller;
		this.amount = amount;
		this.redirectUrl = redirectUrl;
		this.successful = successful;
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public DbTransaction() {

	}

	public DbTransaction(Long id, String paymentId, Seller seller, String amount, String redirectUrl,
			Boolean successful) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.seller = seller;
		this.amount = amount;
		this.redirectUrl = redirectUrl;
		this.successful = successful;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}
	
}
