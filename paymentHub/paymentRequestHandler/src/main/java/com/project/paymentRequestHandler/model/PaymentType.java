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
import javax.persistence.ManyToMany;

@Entity
public class PaymentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long paymentTypeId;
	
	@Column
	private String paymentTypeName;
	
	@Column
	private String paymentTypeHandlerName;
	
	@Column
	private String paymentTypeHandlerUrl;
	
	
	@ManyToMany(mappedBy="paymentTypes", fetch=FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
        })
	private Set<SellerInfo> sellerInfo = new HashSet<SellerInfo>();
	
	public PaymentType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PaymentType(String paymentTypeName, String paymentTypeHandlerName, Set<SellerInfo> sellerInfo) {
		super();
		this.paymentTypeName = paymentTypeName;
		this.paymentTypeHandlerName = paymentTypeHandlerName;
		this.sellerInfo = sellerInfo;
	}
	
	public PaymentType(String paymentTypeName, String paymentTypeHandlerName, String paymentTypeHandlerUrl) {
		super();
		this.paymentTypeName = paymentTypeName;
		this.paymentTypeHandlerName = paymentTypeHandlerName;
		this.paymentTypeHandlerUrl = paymentTypeHandlerUrl;
	}




	public Long getPaymentTypeId() {
		return paymentTypeId;
	}


	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}


	public String getPaymentTypeName() {
		return paymentTypeName;
	}


	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}


	public String getPaymentTypeHandlerUrl() {
		return paymentTypeHandlerUrl;
	}


	public void setPaymentTypeHandlerUrl(String paymentTypeHandlerUrl) {
		this.paymentTypeHandlerUrl = paymentTypeHandlerUrl;
	}


	public String getPaymentTypeHandlerName() {
		return paymentTypeHandlerName;
	}


	public void setPaymentTypeHandlerName(String paymentTypeHandlerName) {
		this.paymentTypeHandlerName = paymentTypeHandlerName;
	}
	
	


	public Set<SellerInfo> getSellerInfo() {
		return sellerInfo;
	}


	public void setSellerInfo(Set<SellerInfo> sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
	
	

}
