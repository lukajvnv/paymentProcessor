package com.project.paymentRequestHandler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long paymentTypeId;
	
	@Column
	private String paymentTypeName;
	
	@Column
	private String paymentTypeHandlerName;

}
