package com.project.scienceCenter.model;

import java.util.Set;

public class User {
	//korisnik generalne info
	private Long userId;
	private String firstName;
	private String lastName;
	private String city;
	private String country;
	private String email;
	
	private boolean canReviewe;
	//neki userRole
	
	//urednik/recezent
	private String vocation;
	private Set<ScienceArea> scienceAreas;
	
	private boolean activatedAccount;
	
	private Set<Magazine> memberships;
	private Set<UserTx> userTxs;

}
