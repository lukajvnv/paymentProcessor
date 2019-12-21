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
	private boolean status;  //ovo mozda da bude enum {pending,confirm}...
	
	@Column
	private Float amountOfMoney;
	@Column
	private String txDescription; //mislim da nece trebati
	
	@Column
	private Long txNumber;  //mislim da nece trebati
	
	@Column
	private String senderAddress; //mozda neki senderID
	@Column
	private String recieverAddress; //ovo neka ostane adresa bitcoin servisa na koji je uplacen

}
