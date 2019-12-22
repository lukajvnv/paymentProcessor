package com.project.scienceCenter.dto;

import java.util.ArrayList;

public class PaymentTypeResponseDTO {

	private Long sellerInfoDbId;
	
	private ArrayList<PaymentTypeDTO> paymentTypes = new ArrayList<PaymentTypeDTO>();
	
	

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
	
	
}
