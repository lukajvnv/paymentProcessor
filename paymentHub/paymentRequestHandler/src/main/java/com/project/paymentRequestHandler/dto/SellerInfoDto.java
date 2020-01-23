package com.project.paymentRequestHandler.dto;

public class SellerInfoDto {
	

	private Long sellerDBId;
	
	
	private Long sellerIdentifier;  //FK IZ NC
	
	
	private String sellerName;
	
	
	private String sellerPib;
	
	private long newClientRequestId;


	public SellerInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SellerInfoDto(Long sellerDBId, Long sellerIdentifier, String sellerName, String sellerPib) {
		super();
		this.sellerDBId = sellerDBId;
		this.sellerIdentifier = sellerIdentifier;
		this.sellerName = sellerName;
		this.sellerPib = sellerPib;
	}
	
	


	public SellerInfoDto(Long sellerDBId, Long sellerIdentifier, String sellerName, String sellerPib,
			long newClientRequestId) {
		super();
		this.sellerDBId = sellerDBId;
		this.sellerIdentifier = sellerIdentifier;
		this.sellerName = sellerName;
		this.sellerPib = sellerPib;
		this.newClientRequestId = newClientRequestId;
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


	public long getNewClientRequestId() {
		return newClientRequestId;
	}


	public void setNewClientRequestId(long newClientRequestId) {
		this.newClientRequestId = newClientRequestId;
	}
	
	

}
