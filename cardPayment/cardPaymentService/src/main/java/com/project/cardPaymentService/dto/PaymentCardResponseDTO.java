package com.project.cardPaymentService.dto;

import java.sql.Timestamp;

import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;

public class PaymentCardResponseDTO {
	
	private PccResponseDTO pccResponse;
	
	private Long paymentId;
	private Long merchantOrderId;
	private Long acquirerOrderId;
	private Timestamp acquirerTimestamp;
	
	private TxStatus outcome;
	
	private String redirectUrl;
	
	private Tx tx;

	public PaymentCardResponseDTO(PccResponseDTO pccResponse, Long paymentId, Long merchantOrderId,
			Long acquirerOrderId, Timestamp acquirerTimestamp, TxStatus outcome, String redirectUrl) {
		super();
		this.pccResponse = pccResponse;
		this.paymentId = paymentId;
		this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.outcome = outcome;
		this.redirectUrl = redirectUrl;
	}
	
	

	public PaymentCardResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PaymentCardResponseDTO(Long merchantOrderId, Long paymentId) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.paymentId = paymentId;
	}



	public PccResponseDTO getPccResponse() {
		return pccResponse;
	}

	public void setPccResponse(PccResponseDTO pccResponse) {
		this.pccResponse = pccResponse;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Timestamp getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public TxStatus getOutcome() {
		return outcome;
	}

	public void setOutcome(TxStatus outcome) {
		this.outcome = outcome;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}



	public Tx getTx() {
		return tx;
	}



	public void setTx(Tx tx) {
		this.tx = tx;
	}

}
