package com.project.pcc.model;

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
	private Long bankInfoId;
	
	@Column
	private String bankName;
	
	@Column
	private String bankPanNumber;
	
	@Column
	private String bankUrl;

	public BankInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankInfo(Long bankInfoId, String bankName, String bankPanNumber, String bankUrl) {
		super();
		this.bankInfoId = bankInfoId;
		this.bankName = bankName;
		this.bankPanNumber = bankPanNumber;
		this.bankUrl = bankUrl;
	}

	public Long getBankInfoId() {
		return bankInfoId;
	}

	public void setBankInfoId(Long bankInfoId) {
		this.bankInfoId = bankInfoId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankPanNumber() {
		return bankPanNumber;
	}

	public void setBankPanNumber(String bankPanNumber) {
		this.bankPanNumber = bankPanNumber;
	}

	public String getBankUrl() {
		return bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}
	
	

}
