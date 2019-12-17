package com.project.scienceCenter.model;

public class SellerBankInfo {
	
	private Long bankInfoId;
	private Long sellefIdentifier;  //FK
	
	//prepoznaje koja banka, kako drugacije
	private String sellerBankAccountNumber;
	
	private String sellerUsername;
	private String sellerPassword;
	
	//hmhm
	private String txSuccessUrl;
	private String txFailedUrl;
	private String txErrorUrl;

}
