package com.project.payPalService.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
	
	@Id
	@Column
	private Long id;

	@Column
	private String clientId;
	
	@Column
	private String clientSecret;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Client(Long id, String clientId, String clientSecret) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

}
