package com.project.cardPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String bankName;
	
	@Column
	private String bankAccountInNationalBank;
//	private Long bankNumInNationalBank;
	
	@Column
	private String serviceUrl;

	public BankInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankInfo(String bankName, String bankAccountInNationalBank, String serviceUrl) {
		super();
		this.bankName = bankName;
		this.bankAccountInNationalBank = bankAccountInNationalBank;
		this.serviceUrl = serviceUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountInNationalBank() {
		return bankAccountInNationalBank;
	}

	public void setBankAccountInNationalBank(String bankAccountInNationalBank) {
		this.bankAccountInNationalBank = bankAccountInNationalBank;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	
}
