package com.project.scienceCenter.dto;

import com.project.scienceCenter.model.BuyingType;

public class UserTxItemDto {
	
	private Long userTxItemId;
	
	private Float price;
	
	private BuyingType buyingType;
	
	private Long itemId;
	
	private Object content;

	public UserTxItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTxItemDto(Long userTxItemId, Float price, UserTxDto userTx, BuyingType buyingType, Long itemId) {
		super();
		this.userTxItemId = userTxItemId;
		this.price = price;
		this.buyingType = buyingType;
		this.itemId = itemId;
	}
	
	

	public UserTxItemDto(Float price, UserTxDto userTx, BuyingType buyingType, Long itemId) {
		super();
		this.price = price;
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

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
	
//	private Magazine purhasedMagazine;
//	private Article purchasedArticle;
//	private MagazineEdition purhasedMagazineEdition;
	
	

}
