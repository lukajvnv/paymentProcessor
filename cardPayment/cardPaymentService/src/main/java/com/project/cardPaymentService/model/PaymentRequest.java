package com.project.cardPaymentService.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.cardPaymentService.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentService.util.DateConverter;

@Entity
public class PaymentRequest {
	
	//privremeno sesija
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long paymentRequestId;
	
//	@Column
//	private BankAccount sellerAccount;
	
	
	@Column
	private String merchantUsername;
	
	@Column
	private Float amount;
	
	@Column
	private Long merchantOrderId;
	
	@Column
	private Timestamp merchantTimestamp;
	
	@Column
	private Timestamp created;

	@Column
	private String successUrl;
	
	@Column
	private String failedUrl;
	
	@Column
	private String errorUrl;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TxStatus requestStatus;
	
	
	@Column
	private Long paymentId;
	
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentRequest(Long paymentRequestId, String merchantUsername, Float amount, Long merchantOrderId,
			Timestamp merchantTimestamp, String successUrl, String failedUrl, String errorUrl, TxStatus status) {
		super();
		this.paymentRequestId = paymentRequestId;
		this.merchantUsername = merchantUsername;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		
		this.requestStatus = status;
		
		this.created = new Timestamp(System.currentTimeMillis());
	}
	
	public PaymentRequest(PaymentValidationRequestDTO requestDto) {
		super();
		
		this.merchantUsername = requestDto.getMerchantId();
		this.amount = requestDto.getAmount();
		this.merchantOrderId = requestDto.getMerchantOrderId();
		this.merchantTimestamp = DateConverter.decodeT(requestDto.getMerchantTimeStamp());
		this.successUrl = requestDto.getSuccessUrl();
		this.failedUrl = requestDto.getFailedUrl();
		this.errorUrl = requestDto.getErrorUrl();
		
		this.created = new Timestamp(System.currentTimeMillis());
	}
	
	public PaymentRequest(PaymentValidationRequestDTO requestDto, Long paymentId, TxStatus status) {
		this(requestDto);
		
		this.paymentId = paymentId;
		this.requestStatus = status;
	}

	public Long getPaymentRequestId() {
		return paymentRequestId;
	}

	public void setPaymentRequestId(Long paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}

	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Timestamp getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Timestamp merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public TxStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(TxStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	
}
