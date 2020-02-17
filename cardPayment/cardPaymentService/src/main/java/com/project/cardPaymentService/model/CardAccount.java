package com.project.cardPaymentService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CardAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long cardAccountId;
	
	@Column
	private String pan;
	
	@Column
	private String salt;
	
	@Column
	private String securityCode;
	
	@Column
	private String cardHolderName;
	
	@Column
	private Date validUntil;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private BankAccount bankAccount;

	public CardAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public CardAccount(String pan, String securityCode, String cardHolderName, Date validUntil,
			BankAccount bankAccount) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.validUntil = validUntil;
		this.bankAccount = bankAccount;
	}
	
	public CardAccount(String pan, String salt, String securityCode, String cardHolderName, Date validUntil,
			BankAccount bankAccount) {
		super();
		this.pan = pan;
		this.salt = salt;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.validUntil = validUntil;
		this.bankAccount = bankAccount;
	}

	public Long getCardAccountId() {
		return cardAccountId;
	}

	public void setCardAccountId(Long cardAccountId) {
		this.cardAccountId = cardAccountId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}



	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
