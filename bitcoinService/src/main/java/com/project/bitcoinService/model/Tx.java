package com.project.bitcoinService.model;

import java.util.Date;

public class Tx {

	private Long txId;
	
	private Date date;
	private boolean status;
	private Float amountOfMoney;
	private String txDescription;
	
	private Long txNumber;
	private String senderName;
	private String senderAccountNum;
	private String recieverName;
	private String recieverAccountNum;
}
