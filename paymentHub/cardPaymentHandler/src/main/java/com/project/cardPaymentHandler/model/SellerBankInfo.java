package com.project.cardPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SellerBankInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long bankInfoId;
	
	@Column
	private Long sellerIdentifier;  //FK 
	
	//prepoznaje koja banka, kako drugacije
	@Column
	private String sellerBankAccountNumber;
	
	@Column
	private String sellerClientName;
	
	@Column
	private String sellerUsername;
	
	@Column
	private String sellerPassword;
	
	//hmhm
	@Column
	private String txSuccessUrl;
	
	@Column
	private String txFailedUrl;
	
	@Column
	private String txErrorUrl;
	
	
	public SellerBankInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerBankInfo(Long sellefIdentifier, String sellerBankAccountNumber, String sellerUsername,
			String sellerPassword, String txSuccessUrl, String txFailedUrl, String txErrorUrl, String clientName) {
		super();
		this.sellerIdentifier = sellefIdentifier;
		this.sellerBankAccountNumber = sellerBankAccountNumber;
		this.sellerUsername = sellerUsername;
		this.sellerPassword = sellerPassword;
		this.txSuccessUrl = txSuccessUrl;
		this.txFailedUrl = txFailedUrl;
		this.txErrorUrl = txErrorUrl;
		this.sellerClientName = clientName;
	}

	public Long getBankInfoId() {
		return bankInfoId;
	}

	public void setBankInfoId(Long bankInfoId) {
		this.bankInfoId = bankInfoId;
	}

	public Long getSellefIdentifier() {
		return sellerIdentifier;
	}

	public void setSellefIdentifier(Long sellefIdentifier) {
		this.sellerIdentifier = sellefIdentifier;
	}

	public String getSellerBankAccountNumber() {
		return sellerBankAccountNumber;
	}

	public void setSellerBankAccountNumber(String sellerBankAccountNumber) {
		this.sellerBankAccountNumber = sellerBankAccountNumber;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}

	public String getSellerPassword() {
		return sellerPassword;
	}

	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
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

	public String getSellerClientName() {
		return sellerClientName;
	}

	public void setSellerClientName(String sellerClientName) {
		this.sellerClientName = sellerClientName;
	}
	
	

}
