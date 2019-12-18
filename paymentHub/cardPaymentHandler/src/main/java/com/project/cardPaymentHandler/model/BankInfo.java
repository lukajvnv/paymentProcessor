package com.project.cardPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankInfo {
	@Id
	@Column
	private Long id;
	@Column
	private String bankName;
	@Column
	private Long bankNumInNationalBank;
	//private String bankAccountInNationalBank;
	@Column
	private String serviceUrl;
}
