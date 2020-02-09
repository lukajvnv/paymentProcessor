package com.project.syncService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class WebshopClient {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	@GenericGenerator(name = "idGenerator",
	    strategy = "com.project.syncService.util.IdGenerator",
	    parameters = {
	        @Parameter(name = "sequence", value = "generator_generator_id_sequence")
	    })
	private Long webShopClientId;
	
	@Column(unique = true, updatable = false, nullable = false)
	private String clientHost;

	public WebshopClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public WebshopClient(Long webShopClientId, String clientHost) {
		super();
		this.webShopClientId = webShopClientId;
		this.clientHost = clientHost;
	}
	
	
	public WebshopClient(String clientHost) {
		this.clientHost = clientHost;
	}

	public Long getWebShopClientId() {
		return webShopClientId;
	}

	public void setWebShopClientId(Long webShopClientId) {
		this.webShopClientId = webShopClientId;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}
	
	

}
