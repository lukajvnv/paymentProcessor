package com.project.cardPaymentService.model;

public class BankAccount {
	
	private Long bankAccountId;
	
	private String bankAccountNumber;
	
	private String bankAccountUsername;
	private String bankAccountPassword;
	private String salt;
	
	private Float balance;
	private Float reservedAmount;
	
	private CardAccount cardAccount;

}
