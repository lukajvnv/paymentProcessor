package com.project.scienceCenter.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserA {
	//korisnik generalne info
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long userId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	@Column
	private String email;
	
	
	//neki userRole
	
	//urednik/recezent
	
	@Column
	private boolean activatedAccount;


	public UserA() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	//private Set<Magazine> memberships;
	
	public UserA(String firstName, String lastName, String city, String country, String email,
			boolean activatedAccount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.country = country;
		this.email = email;
		this.activatedAccount = activatedAccount;
	}



	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<UserTx> userTxs;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivatedAccount() {
		return activatedAccount;
	}

	public void setActivatedAccount(boolean activatedAccount) {
		this.activatedAccount = activatedAccount;
	}

	public Set<UserTx> getUserTxs() {
		return userTxs;
	}

	public void setUserTxs(Set<UserTx> userTxs) {
		this.userTxs = userTxs;
	}
	
	

}
