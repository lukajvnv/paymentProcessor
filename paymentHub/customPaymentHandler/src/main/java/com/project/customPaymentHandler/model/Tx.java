package com.project.customPaymentHandler.model;

import java.sql.Timestamp;

public class Tx {

	
	private Long txId;
	
	
	private Timestamp timestamp;
	
	
	private TxStatus status;
	
	
	private Float amountOfMoney;
	
	
	private String txDescription;
	
	
	 
	private Long paymentId;
	 
	
	
	private String senderName;
	
	
	private String senderAccountNum;
	
	
	private String recieverName;
	
	
	private String recieverAccountNum;
	
	
	private Timestamp merchantTimestamp;
	
	
	private Long merchantOrderId;
	
	
	private Timestamp acquirerTimestamp;
	
	
	private Long acquirerOrderId;
	
	//casopis?

	public Tx() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Tx(Timestamp timestamp, TxStatus status, Float amountOfMoney, String txDescription, long paymentId,
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
		
		this.paymentId = paymentId;
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


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	public Timestamp getMerchantTimestamp() {
		return merchantTimestamp;
	}


	public void setMerchantTimestamp(Timestamp merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}


	public Long getMerchantOrderId() {
		return merchantOrderId;
	}


	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}


	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}


	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}


	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}


	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}


	public Tx(Timestamp timestamp, TxStatus status, Float amountOfMoney, String txDescription,
			long paymentId, String senderName, String senderAccountNum, String recieverName, 
			String recieverAccountNum, Timestamp merchantTimestamp, long merchantOrderId, 
			Timestamp acquirerTimestamp, long acquirerOrderId) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.amountOfMoney = amountOfMoney;
		this.txDescription = txDescription;
		this.paymentId = paymentId;
		this.senderName = senderName;
		this.senderAccountNum = senderAccountNum;
		this.recieverName = recieverName;
		this.recieverAccountNum = recieverAccountNum;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantOrderId = merchantOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerOrderId = acquirerOrderId;
	}
}
