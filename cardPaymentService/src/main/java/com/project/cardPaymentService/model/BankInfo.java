package com.project.cardPaymentService.model;

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
	private Long bankNumInNationalBank;
	
	//private String bankAccountInNationalBank;
	@Column
	private String serviceUrl;
}
