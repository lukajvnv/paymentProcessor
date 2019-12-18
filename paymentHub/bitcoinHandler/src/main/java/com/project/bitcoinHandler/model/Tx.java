package com.project.bitcoinHandler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tx {

	@Id
	@Column
	private Long txId;
	
	@Column
	private Date date;
	@Column
	private boolean status;
	
	@Column
	private Float amountOfMoney;
	@Column
	private String txDescription;
	
	@Column
	private Long txNumber;
	@Column
	private String senderName;
	@Column
	private String senderAccountNum;
	@Column
	private String recieverName;
	@Column
	private String recieverAccountNum;
}
