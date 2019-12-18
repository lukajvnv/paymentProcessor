package com.project.bitcoinService.model;

import java.util.Set;

public class BitcoinAccount {

	private Long accountId;
	
	private String username;
	private String passwored;
	
	private String email;
	private String backupEmail;
	
	
	private String bitcoinAddress;
	
	private Float balance;
	
	private Set<Wallet> wallets;
}
