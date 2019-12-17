package com.project.scienceCenter.model;

import java.util.Date;
import java.util.Set;

public class UserTx {
	
	private Long userTxId;
	private User user;
	private Date date;
	
	private Set<UserTxItem> items;
	private Float totalAmount;

}
