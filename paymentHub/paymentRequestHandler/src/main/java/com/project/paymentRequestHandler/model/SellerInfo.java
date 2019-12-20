package com.project.paymentRequestHandler.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SellerInfo {

	@Id
	@Column
	private Long sellerDBId;
	@Column
	private Long sellerIdentifier;  //FK IZ NC
	@Column
	private String sellerName;
	@Column
	private String sellerPib;
	
	//ovo bitrebalo nekako da se mapira, ne znam sta je zamisljeno
	/*
	@Column
	private Set<PaymentType> paymentTypes = new HashSet<PaymentType>();*/
	
}
