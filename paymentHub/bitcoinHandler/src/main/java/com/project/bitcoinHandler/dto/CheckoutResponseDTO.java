package com.project.bitcoinHandler.dto;

public class CheckoutResponseDTO {

	private int id;
	private String status;
	private String price_currency;
	private String pay_currency;
	private double price_amount;
	private double pay_amount;
	private String receive_currency;
	private String receive_amount;
	private String created_at;
	private String expire_at;
	private String payment_address;
	private String order_id;
	private String underpaid_amount;
	private String overpaid_amount;
	private boolean is_refundable;
	private String payment_url;
	private boolean do_not_convert;
	private boolean lightning_network;
	
	
	
	public CheckoutResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CheckoutResponseDTO(int id, String status, String price_currency, String pay_currency, double price_amount,
			double pay_amount, String receive_currency, String receive_amount, String created_at, String expire_at,
			String payment_address, String order_id, String underpaid_amount, String overpaid_amount,
			boolean is_refundable, String payment_url, boolean do_not_convert, boolean lightning_network) {
		super();
		this.id = id;
		this.status = status;
		this.price_currency = price_currency;
		this.pay_currency = pay_currency;
		this.price_amount = price_amount;
		this.pay_amount = pay_amount;
		this.receive_currency = receive_currency;
		this.receive_amount = receive_amount;
		this.created_at = created_at;
		this.expire_at = expire_at;
		this.payment_address = payment_address;
		this.order_id = order_id;
		this.underpaid_amount = underpaid_amount;
		this.overpaid_amount = overpaid_amount;
		this.is_refundable = is_refundable;
		this.payment_url = payment_url;
		this.do_not_convert = do_not_convert;
		this.lightning_network = lightning_network;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getPrice_currency() {
		return price_currency;
	}



	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}



	public String getPay_currency() {
		return pay_currency;
	}



	public void setPay_currency(String pay_currency) {
		this.pay_currency = pay_currency;
	}



	public double getPrice_amount() {
		return price_amount;
	}



	public void setPrice_amount(double price_amount) {
		this.price_amount = price_amount;
	}



	public double getPay_amount() {
		return pay_amount;
	}



	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}



	public String getReceive_currency() {
		return receive_currency;
	}



	public void setReceive_currency(String receive_currency) {
		this.receive_currency = receive_currency;
	}



	public String getReceive_amount() {
		return receive_amount;
	}



	public void setReceive_amount(String receive_amount) {
		this.receive_amount = receive_amount;
	}



	public String getCreated_at() {
		return created_at;
	}



	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}



	public String getExpire_at() {
		return expire_at;
	}



	public void setExpire_at(String expire_at) {
		this.expire_at = expire_at;
	}



	public String getPayment_address() {
		return payment_address;
	}



	public void setPayment_address(String payment_address) {
		this.payment_address = payment_address;
	}



	public String getOrder_id() {
		return order_id;
	}



	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}



	public String getUnderpaid_amount() {
		return underpaid_amount;
	}



	public void setUnderpaid_amount(String underpaid_amount) {
		this.underpaid_amount = underpaid_amount;
	}



	public String getOverpaid_amount() {
		return overpaid_amount;
	}



	public void setOverpaid_amount(String overpaid_amount) {
		this.overpaid_amount = overpaid_amount;
	}



	public boolean isIs_refundable() {
		return is_refundable;
	}



	public void setIs_refundable(boolean is_refundable) {
		this.is_refundable = is_refundable;
	}



	public String getPayment_url() {
		return payment_url;
	}



	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}



	public boolean isDo_not_convert() {
		return do_not_convert;
	}



	public void setDo_not_convert(boolean do_not_convert) {
		this.do_not_convert = do_not_convert;
	}



	public boolean isLightning_network() {
		return lightning_network;
	}



	public void setLightning_network(boolean lightning_network) {
		this.lightning_network = lightning_network;
	}
	
	
	
	
}
