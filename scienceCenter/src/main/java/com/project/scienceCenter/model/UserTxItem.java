package com.project.scienceCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserTxItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long userTxItemId;
	
	@Column
	private Float price;
	
	
	
	@ManyToOne
	@JoinColumn(name = "userTx_id")
	private UserTx userTx;
	//private Magazine seller; //ili neki seller id
	
	@Column
	@Enumerated(EnumType.STRING)
	private BuyingType buyingType;
	
	@Column
	private Long itemId;

	public UserTxItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTxItem(Long userTxItemId, Float price, UserTx userTx, BuyingType buyingType, Long itemId) {
		super();
		this.userTxItemId = userTxItemId;
		this.price = price;
		this.userTx = userTx;
		this.buyingType = buyingType;
		this.itemId = itemId;
	}
	
	

	public UserTxItem(Float price, UserTx userTx, BuyingType buyingType, Long itemId) {
		super();
		this.price = price;
		this.userTx = userTx;
		this.buyingType = buyingType;
		this.itemId = itemId;
	}

	public Long getUserTxItemId() {
		return userTxItemId;
	}

	public void setUserTxItemId(Long userTxItemId) {
		this.userTxItemId = userTxItemId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public UserTx getUserTx() {
		return userTx;
	}

	public void setUserTx(UserTx userTx) {
		this.userTx = userTx;
	}

	public BuyingType getBuyingType() {
		return buyingType;
	}

	public void setBuyingType(BuyingType buyingType) {
		this.buyingType = buyingType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
	
//	private Magazine purhasedMagazine;
//	private Article purchasedArticle;
//	private MagazineEdition purhasedMagazineEdition;
	
	

}
