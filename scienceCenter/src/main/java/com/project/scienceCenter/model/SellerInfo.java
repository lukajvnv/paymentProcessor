package com.project.scienceCenter.model;

import java.util.Set;

public class SellerInfo {

	private Long sellerDBId;
	private Long sellerIdentifier;  //FK IZ NC
	private String sellerName;
	private String sellerPib;
	
	private Set<PaymentType> paymentTypes;
	
}
