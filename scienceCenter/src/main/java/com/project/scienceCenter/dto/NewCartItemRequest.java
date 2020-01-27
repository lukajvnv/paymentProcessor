package com.project.scienceCenter.dto;

public class NewCartItemRequest {
	
	private long cartId;
	private long articleId;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public NewCartItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewCartItemRequest(long cartId, long articleId) {
		super();
		this.cartId = cartId;
		this.articleId = articleId;
	}
	
	

}
