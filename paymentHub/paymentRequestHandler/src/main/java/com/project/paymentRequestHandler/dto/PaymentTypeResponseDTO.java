package com.project.paymentRequestHandler.dto;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

public class PaymentTypeResponseDTO {

	private Long sellerInfoDbId;
	
	private ArrayList<PaymentTypeDTO> paymentTypes = new ArrayList<PaymentTypeDTO>();
	
	@NotBlank
	private String url; //kp-front
	
	

	public PaymentTypeResponseDTO(Long sellerInfoDbId, ArrayList<PaymentTypeDTO> paymentTypes, @NotBlank String url) {
		super();
		this.sellerInfoDbId = sellerInfoDbId;
		this.paymentTypes = paymentTypes;
		this.url = url;
	}

	public PaymentTypeResponseDTO(Long sellerInfoDbId, ArrayList<PaymentTypeDTO> paymentTypes) {
		super();
		this.sellerInfoDbId = sellerInfoDbId;
		this.paymentTypes = paymentTypes;
	}

	public Long getSellerInfoDbId() {
		return sellerInfoDbId;
	}

	public void setSellerInfoDbId(Long sellerInfoDbId) {
		this.sellerInfoDbId = sellerInfoDbId;
	}

	public ArrayList<PaymentTypeDTO> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(ArrayList<PaymentTypeDTO> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public PaymentTypeResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
