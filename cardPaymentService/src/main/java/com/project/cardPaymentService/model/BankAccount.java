package com.project.cardPaymentService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long bankAccountId;
	
	@Column
	private String bankAccountNumber;
	
	@Column
	private String bankAccountName;
	
	@Column
	private String bankAccountUsername;
	
	@Column
	private String bankAccountPassword;
	
	@Column
	private String salt;
	
	@Column
	private Float balance;
	
	@Column
	private Float reservedAmount;
	
	
	
	
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public BankAccount(String bankAccountNumber, String bankAccountName, String bankAccountUsername,
			String bankAccountPassword, String salt, Float balance) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.bankAccountName = bankAccountName;
		this.bankAccountUsername = bankAccountUsername;
		this.bankAccountPassword = bankAccountPassword;
		this.salt = salt;
		this.balance = balance;
	}



	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountUsername() {
		return bankAccountUsername;
	}

	public void setBankAccountUsername(String bankAccountUsername) {
		this.bankAccountUsername = bankAccountUsername;
	}

	public String getBankAccountPassword() {
		return bankAccountPassword;
	}

	public void setBankAccountPassword(String bankAccountPassword) {
		this.bankAccountPassword = bankAccountPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getReservedAmount() {
		return reservedAmount;
	}

	public void setReservedAmount(Float reservedAmount) {
		this.reservedAmount = reservedAmount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}



	

}
