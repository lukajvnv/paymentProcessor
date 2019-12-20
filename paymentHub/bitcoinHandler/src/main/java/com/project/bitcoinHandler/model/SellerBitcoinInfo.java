package com.project.bitcoinHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SellerBitcoinInfo {
	@Id
	@Column
	private Long sellerBitcoinInfoId;
	
	@Column
	private String username;
	@Column
	private String passwored;
	
	@Column
	private String email;
	@Column
	private String backupEmail;
	
	@Column
	private String bitcoinAddress;
	

}
