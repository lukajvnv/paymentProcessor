package com.project.cardPaymentService.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tx {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long txId;
	
	@Column
	private Timestamp timestamp;
	
	@Column
	private TxStatus status;
	
	@Column
	private Float amountOfMoney;
	
	@Column
	private String txDescription;
	
	/*
	 * @Column private Long txNumber;
	 */
	
	@Column
	private String senderName;
	
	@Column
	private String senderAccountNum;
	
	@Column
	private String recieverName;
	
	@Column
	private String recieverAccountNum;
	
	

	public Tx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Tx(Timestamp timestamp, TxStatus status, Float amountOfMoney, String txDescription,
			String senderName, String senderAccountNum, String recieverName, String recieverAccountNum) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.amountOfMoney = amountOfMoney;
		this.txDescription = txDescription;
		this.senderName = senderName;
		this.senderAccountNum = senderAccountNum;
		this.recieverName = recieverName;
		this.recieverAccountNum = recieverAccountNum;
	}

    	

	public Long getTxId() {
		return txId;
	}

	public void setTxId(Long txId) {
		this.txId = txId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TxStatus getStatus() {
		return status;
	}

	public void setStatus(TxStatus status) {
		this.status = status;
	}

	public Float getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(Float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public String getTxDescription() {
		return txDescription;
	}

	public void setTxDescription(String txDescription) {
		this.txDescription = txDescription;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccountNum() {
		return senderAccountNum;
	}

	public void setSenderAccountNum(String senderAccountNum) {
		this.senderAccountNum = senderAccountNum;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getRecieverAccountNum() {
		return recieverAccountNum;
	}

	public void setRecieverAccountNum(String recieverAccountNum) {
		this.recieverAccountNum = recieverAccountNum;
	}
}
