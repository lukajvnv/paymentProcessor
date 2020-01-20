package com.project.payPalService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Seller {
	
	@Id
	@Column
	private Long id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Column
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Seller(Long id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

}
