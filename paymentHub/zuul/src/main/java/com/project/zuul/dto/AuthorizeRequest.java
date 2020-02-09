package com.project.zuul.dto;

public class AuthorizeRequest {

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
