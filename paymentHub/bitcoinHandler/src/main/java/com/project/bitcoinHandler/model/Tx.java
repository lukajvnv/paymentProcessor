package com.project.bitcoinHandler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.project.bitcoinHandler.util.TxStatus;

@Entity
public class Tx {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long txId;
	
	@Column
	private Date date;
	@Column
	private TxStatus status;  //ovo mozda da bude enum {pending,confirm}...
	
	@Column
	private Double amountOfMoney;
	@Column
	private String txDescription; //mislim da nece trebati
	

	@NotNull
	@Column(name = "username")
	private Integer username;  //mislim da nece trebati
	
	@Column
	private String senderAddress; //mozda neki senderID
	@Column
	private String recieverAddress; //ovo neka ostane adresa bitcoin servisa na koji je uplacen
	
	@ManyToOne
	private SellerBitcoinInfo sbi; //informacija o seller-u kako bismo znali btw
	

	public Tx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Tx(Long txId, Date date, TxStatus status, Double amountOfMoney, String txDescription, Integer order_id,
			String senderAddress, String recieverAddress, SellerBitcoinInfo sbi) {
		super();
		this.txId = txId;
		this.date = date;
		this.status = status;
		this.amountOfMoney = amountOfMoney;
		this.txDescription = txDescription;
		this.username = order_id;
		this.senderAddress = senderAddress;
		this.recieverAddress = recieverAddress;
		this.sbi = sbi;
	}



	public Tx(Long txId, Date date, TxStatus status, Double amountOfMoney, String txDescription, Integer order_id,
			String senderAddress, String recieverAddress) {
		super();
		this.txId = txId;
		this.date = date;
		this.status = status;
		this.amountOfMoney = amountOfMoney;
		this.txDescription = txDescription;
		this.username = order_id;
		this.senderAddress = senderAddress;
		this.recieverAddress = recieverAddress;
	}
	
	public SellerBitcoinInfo getSbi() {
		return sbi;
	}



	public void setSbi(SellerBitcoinInfo sbi) {
		this.sbi = sbi;
	}



	public TxStatus getStatus() {
		return status;
	}
	public Long getTxId() {
		return txId;
	}
	public void setTxId(Long txId) {
		this.txId = txId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TxStatus isStatus() {
		return status;
	}
	public void setStatus(TxStatus status) {
		this.status = status;
	}
	public Double getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public String getTxDescription() {
		return txDescription;
	}
	public void setTxDescription(String txDescription) {
		this.txDescription = txDescription;
	}
	public Integer getorder_id() {
		return username;
	}
	public void setorder_id(Integer order_id) {
		this.username = order_id;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getRecieverAddress() {
		return recieverAddress;
	}
	public void setRecieverAddress(String recieverAddress) {
		this.recieverAddress = recieverAddress;
	}
	public void setDate(int date2) {
		// TODO Auto-generated method stub
		
	}

	
	
}
