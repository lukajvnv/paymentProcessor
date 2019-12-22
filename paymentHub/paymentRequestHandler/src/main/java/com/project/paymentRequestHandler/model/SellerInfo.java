package com.project.paymentRequestHandler.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SellerInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long sellerDBId;
	
	@Column
	private Long sellerIdentifier;  //FK IZ NC
	
	@Column
	private String sellerName;
	
	@Column
	private String sellerPib;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        })
	@JoinTable(name="seller_supported_payment_types", joinColumns = @JoinColumn(name = "seller_db_id", referencedColumnName = "sellerDBId"), inverseJoinColumns = @JoinColumn(name="payment_type_id", referencedColumnName = "paymentTypeId"))
	private Set<PaymentType> paymentTypes = new HashSet<PaymentType>();

	public SellerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerInfo(Long sellerDBId, Long sellerIdentifier, String sellerName, String sellerPib,
			Set<PaymentType> paymentTypes) {
		super();
		this.sellerDBId = sellerDBId;
		this.sellerIdentifier = sellerIdentifier;
		this.sellerName = sellerName;
		this.sellerPib = sellerPib;
		this.paymentTypes = paymentTypes;
	}

	public Long getSellerDBId() {
		return sellerDBId;
	}

	public void setSellerDBId(Long sellerDBId) {
		this.sellerDBId = sellerDBId;
	}

	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}

	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerPib() {
		return sellerPib;
	}

	public void setSellerPib(String sellerPib) {
		this.sellerPib = sellerPib;
	}

	public Set<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(Set<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
	
	
}
