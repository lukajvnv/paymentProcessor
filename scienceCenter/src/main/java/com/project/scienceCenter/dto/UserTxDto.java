package com.project.scienceCenter.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.project.scienceCenter.model.TxStatus;


public class UserTxDto {
	
	
	private Long userTxId;
	
	
	
	private Date created;
	
	
	private TxStatus status;
	
	private List<UserTxItemDto> items;
	
	
	private Float totalAmount;

	public UserTxDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserTxDto(Long userTxId, Date created, TxStatus status, List<UserTxItemDto> items, Float totalAmount) {
		super();
		this.userTxId = userTxId;
		this.created = created;
		this.status = status;
		this.items = items;
		this.totalAmount = totalAmount;
	}
	
	



	public UserTxDto(Long userTxId, Date created, TxStatus status, Float totalAmount) {
		super();
		this.userTxId = userTxId;
		this.created = created;
		this.status = status;
		this.totalAmount = totalAmount;
	}



	public UserTxDto(Date created, TxStatus status, Float totalAmount) {
		super();
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

	public List<UserTxItemDto> getItems() {
		return items;
	}

	public void setItems(List<UserTxItemDto> items) {
		this.items = items;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
