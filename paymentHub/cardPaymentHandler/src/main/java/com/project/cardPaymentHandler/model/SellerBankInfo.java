package com.project.cardPaymentHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SellerBankInfo {
	@Id
	@Column
	private Long bankInfoId;
	@Column
	private Long sellefIdentifier;  //FK
	
	//prepoznaje koja banka, kako drugacije
	@Column
	private String sellerBankAccountNumber;
	
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

}
