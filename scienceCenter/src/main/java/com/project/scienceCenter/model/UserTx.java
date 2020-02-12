package com.project.scienceCenter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserTx {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long userTxId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserA user;
	
	@Column
	private Date created;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TxStatus status;
	
	@OneToMany(mappedBy = "userTx", fetch = FetchType.EAGER)
	private Set<UserTxItem> items;
	
	@Column
	private Float totalAmount;
	
	@Column
	private Long kPClientIdentifier;
	
	@Column
	private Long orderId;

	public UserTx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTx(UserA user, Date created, TxStatus status, Float totalAmount) {
		super();
		this.user = user;
		this.created = created;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public Long getUserTxId() {
		return userTxId;
	}

	public void setUserTxId(Long userTxId) {
		this.userTxId = userTxId;
	}

	public UserA getUser() {
		return user;
	}

	public void setUser(UserA user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public TxStatus getStatus() {
		return status;
	}

	public void setStatus(TxStatus status) {
		this.status = status;
	}

	public Set<UserTxItem> getItems() {
		return items;
	}

	public void setItems(Set<UserTxItem> items) {
		this.items = items;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getkPIdentifier() {
		return kPClientIdentifier;
	}

	public void setkPIdentifier(Long kPIdentifier) {
		this.kPClientIdentifier = kPIdentifier;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	

}
