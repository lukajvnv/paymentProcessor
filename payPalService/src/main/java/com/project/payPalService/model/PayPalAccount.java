package com.project.payPalService.model;

import java.util.Date;

public class PayPalAccount {

	private Long payPalAccountId;
	
	//odredjuje nalog
	private String country;
	private Long accountNationalId;
	
	private String firstName;
	private String lastName;
	private String businessName;
	
	private String email;
	
	private String password;
	private String salt;
	
	private String address;
	private String city;    //postal code
	private Date dateOfBirth;
	private String telephoneNumber;
	
	private CardAccount cardAccount;
	
	private Float balance;
}
