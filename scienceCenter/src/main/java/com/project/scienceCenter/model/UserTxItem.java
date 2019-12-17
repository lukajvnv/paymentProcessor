package com.project.scienceCenter.model;

public class UserTxItem {
	
	private Long userTxItemId;
	private Float price;
	private boolean paid;
	
	private UserTx userTx;
	private Magazine seller; //ili neki seller id
	
	private BuyingType buyingType;
	private Magazine purhasedMagazine;
	private Article purchasedArticle;
	private MagazineEdition purhasedMagazineEdition;

}
