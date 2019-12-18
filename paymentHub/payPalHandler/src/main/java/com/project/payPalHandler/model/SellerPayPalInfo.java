package com.project.payPalHandler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SellerPayPalInfo {
	
	@Id
	@Column
	private Long sellerId;
	
	@Column
	private String country;
	@Column
	private Long accountNationalId;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String businessName;
	
	@Column
	private String email;
	@Column
	private String password;
	
	

}
