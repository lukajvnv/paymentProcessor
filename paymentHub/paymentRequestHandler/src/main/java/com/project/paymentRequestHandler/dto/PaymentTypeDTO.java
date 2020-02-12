package com.project.paymentRequestHandler.dto;

import javax.validation.constraints.NotEmpty;

public class PaymentTypeDTO {
	
	private Long paymentTypeId;
	
	@NotEmpty
	private String paymentTypeName;
	
	@NotEmpty
	private String paymentTypeHandlerName;
	
	@NotEmpty
	private String paymentTypeHandlerUrl;

	@NotEmpty
	private String paymentTypeHandlerUrlRoot;
	
//	public PaymentTypeDTO(Long paymentTypeId, String paymentTypeName, String paymentTypeHandlerName,
//			String paymentTypeHandlerUrl) {
//		super();
//		this.paymentTypeId = paymentTypeId;
//		this.paymentTypeName = paymentTypeName;
//		this.paymentTypeHandlerName = paymentTypeHandlerName;
//		this.paymentTypeHandlerUrl = paymentTypeHandlerUrl;
//	}
	
	

	public PaymentTypeDTO(Long paymentTypeId, String paymentTypeName, String paymentTypeHandlerName,
			String paymentTypeHandlerUrl, String paymentTypeHandlerUrlRoot) {
		super();
		this.paymentTypeId = paymentTypeId;
		this.paymentTypeName = paymentTypeName;
		this.paymentTypeHandlerName = paymentTypeHandlerName;
		this.paymentTypeHandlerUrl = paymentTypeHandlerUrl;
		this.paymentTypeHandlerUrlRoot = paymentTypeHandlerUrlRoot;
	}



	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getPaymentTypeHandlerName() {
		return paymentTypeHandlerName;
	}

	public void setPaymentTypeHandlerName(String paymentTypeHandlerName) {
		this.paymentTypeHandlerName = paymentTypeHandlerName;
	}

	public String getPaymentTypeHandlerUrl() {
		return paymentTypeHandlerUrl;
	}

	public void setPaymentTypeHandlerUrl(String paymentTypeHandlerUrl) {
		this.paymentTypeHandlerUrl = paymentTypeHandlerUrl;
	}
	
	public PaymentTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getPaymentTypeHandlerUrlRoot() {
		return paymentTypeHandlerUrlRoot;
	}



	public void setPaymentTypeHandlerUrlRoot(String paymentTypeHandlerUrlRoot) {
		this.paymentTypeHandlerUrlRoot = paymentTypeHandlerUrlRoot;
	}
	
	

}
