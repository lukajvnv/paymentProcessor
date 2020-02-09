package com.project.syncService.dto;

import javax.validation.constraints.NotEmpty;

public class AuthorizeRequest {

	@NotEmpty
	private String host;

	public AuthorizeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthorizeRequest(String host) {
		super();
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	
}
