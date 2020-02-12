package com.project.customPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomSellerClientInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private Long sellerIdentifier;  //FK 

	@Column
	private String txSuccessUrl;
	
	@Column
	private String txFailedUrl;
	
	@Column
	private String txErrorUrl;
	
	@Column
	private byte[] image;

	public CustomSellerClientInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomSellerClientInfo(String txSuccessUrl, String txFailedUrl, String txErrorUrl) {
		super();
		this.txSuccessUrl = txSuccessUrl;
		this.txFailedUrl = txFailedUrl;
		this.txErrorUrl = txErrorUrl;
	}
	
	

	public CustomSellerClientInfo(Long sellerIdentifier, String txSuccessUrl, String txFailedUrl, String txErrorUrl) {
		super();
		this.sellerIdentifier = sellerIdentifier;
		this.txSuccessUrl = txSuccessUrl;
		this.txFailedUrl = txFailedUrl;
		this.txErrorUrl = txErrorUrl;
	}
	
	

	public CustomSellerClientInfo(Long sellerIdentifier, String txSuccessUrl, String txFailedUrl, String txErrorUrl,
			byte[] image) {
		super();
		this.sellerIdentifier = sellerIdentifier;
		this.txSuccessUrl = txSuccessUrl;
		this.txFailedUrl = txFailedUrl;
		this.txErrorUrl = txErrorUrl;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxSuccessUrl() {
		return txSuccessUrl;
	}

	public void setTxSuccessUrl(String txSuccessUrl) {
		this.txSuccessUrl = txSuccessUrl;
	}

	public String getTxFailedUrl() {
		return txFailedUrl;
	}

	public void setTxFailedUrl(String txFailedUrl) {
		this.txFailedUrl = txFailedUrl;
	}

	public String getTxErrorUrl() {
		return txErrorUrl;
	}

	public void setTxErrorUrl(String txErrorUrl) {
		this.txErrorUrl = txErrorUrl;
	}

	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}

	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}

	
	
	
}
