package com.project.paymentRequestHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class NewClientRequest {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientIdGenerator")
	@GenericGenerator(name = "clientIdGenerator",
	    strategy = "com.project.paymentRequestHandler.util.ClientIdGenerator",
	    parameters = {
	        @Parameter(name = "sequence", value = "generator_generator_client_id_sequence")
	    })
	private Long newClientId;
		
	@Column
	private Long sellerIdentifier;  //FK IZ NC
	
	@Column
	private String clientApplication;
	
	
	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}

	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}

	public String getClientApplication() {
		return clientApplication;
	}

	public void setClientApplication(String clientApplication) {
		this.clientApplication = clientApplication;
	}

	public NewClientRequest() {
		super();
		// TODO Auto-generated constructor stub
	}



	public NewClientRequest(Long newClientId, Long sellerIdentifier, String clientApplication) {
		super();
		this.newClientId = newClientId;
		this.sellerIdentifier = sellerIdentifier;
		this.clientApplication = clientApplication;
	}

	public NewClientRequest(Long sellerIdentifier, String clientApplication) {
		super();
		this.sellerIdentifier = sellerIdentifier;
		this.clientApplication = clientApplication;
	}

	public Long getNewClientId() {
		return newClientId;
	}

	public void setNewClientId(Long newClientId) {
		this.newClientId = newClientId;
	}
	
	

}
