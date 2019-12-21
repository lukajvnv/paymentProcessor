package com.project.bitcoinHandler.dto;

public class BitCoinRequestDTO {
	
	private String order_id;
	

	private double price_amount;
	
	private String price_currency;
	
	private String receive_currency;
	
	private String title;
	
	private String descripton;
	
	private String callback_url;
	
	private String cancel_url;
	
	private String success_url;

	private String token;
	
	public BitCoinRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public BitCoinRequestDTO(double price_amount, String price_currency, String receive_currency) {
		super();
		this.price_amount = price_amount;
		this.price_currency = price_currency;
		this.receive_currency = receive_currency;
	}


	public BitCoinRequestDTO(double price_amount, String price_currency, String receive_currency, String token) {
		super();
		this.price_amount = price_amount;
		this.price_currency = price_currency;
		this.receive_currency = receive_currency;
		this.token = token;
	}

	

	public BitCoinRequestDTO(String order_id, double price_amount, String price_currency, String receive_currency,
			String title, String descripton, String callback_url, String cancel_url, String success_url, String token) {
		super();
		this.order_id = order_id;
		this.price_amount = price_amount;
		this.price_currency = price_currency;
		this.receive_currency = receive_currency;
		this.title = title;
		this.descripton = descripton;
		this.callback_url = callback_url;
		this.cancel_url = cancel_url;
		this.success_url = success_url;
		this.token = token;
	}


	@Override
	public String toString() {
		return "BitCoinRequestDTO [price_amount=" + price_amount + ", price_currency=" + price_currency
				+ ", receive_currency=" + receive_currency + ", token=" + token + "]";
	}

	
	

	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescripton() {
		return descripton;
	}


	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}


	public String getCallback_url() {
		return callback_url;
	}


	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}


	public String getCancel_url() {
		return cancel_url;
	}


	public void setCancel_url(String cancel_url) {
		this.cancel_url = cancel_url;
	}


	public String getSuccess_url() {
		return success_url;
	}


	public void setSuccess_url(String success_url) {
		this.success_url = success_url;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public double getPrice_amount() {
		return price_amount;
	}

	public void setPrice_amount(double price_amount) {
		this.price_amount = price_amount;
	}

	public String getPrice_currency() {
		return price_currency;
	}

	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}

	public String getReceive_currency() {
		return receive_currency;
	}

	public void setReceive_currency(String receive_currency) {
		this.receive_currency = receive_currency;
	}
	
	
}
