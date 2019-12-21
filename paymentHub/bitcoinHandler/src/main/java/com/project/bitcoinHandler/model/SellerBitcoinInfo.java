package com.project.bitcoinHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SellerBitcoinInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long sellerBitcoinInfoId;
	
	@Column
	private Long idMagazine;
	//trebalo bi da je adresa bitcoin-a dovoljna
	@Column
	private String bitcoinAddress; //bice token od servisa bitcoina
	
	
	public SellerBitcoinInfo(Long idMagazine, String bitcoinAddress) {
		super();
		this.idMagazine = idMagazine;
		this.bitcoinAddress = bitcoinAddress;
	}


	public Long getSellerBitcoinInfoId() {
		return sellerBitcoinInfoId;
	}


	public void setSellerBitcoinInfoId(Long sellerBitcoinInfoId) {
		this.sellerBitcoinInfoId = sellerBitcoinInfoId;
	}


	public Long getIdMagazine() {
		return idMagazine;
	}


	public void setIdMagazine(Long idMagazine) {
		this.idMagazine = idMagazine;
	}


	public String getBitcoinAddress() {
		return bitcoinAddress;
	}


	public void setBitcoinAddress(String bitcoinAddress) {
		this.bitcoinAddress = bitcoinAddress;
	}
	

}
