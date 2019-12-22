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
	
	@Column
	private String order_id; //order_id je order id porudzbine na coingate-u
	
	
	
	
	public SellerBitcoinInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public SellerBitcoinInfo(Long idMagazine, String bitcoinAddress) {
		super();
		this.idMagazine = idMagazine;
		this.bitcoinAddress = bitcoinAddress;
	}


	
	public SellerBitcoinInfo(Long idMagazine, String bitcoinAddress, String order_id) {
		super();
		this.idMagazine = idMagazine;
		this.bitcoinAddress = bitcoinAddress;
		this.order_id = order_id;
	}



	public String getOrder_id() {
		return order_id;
	}



	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
