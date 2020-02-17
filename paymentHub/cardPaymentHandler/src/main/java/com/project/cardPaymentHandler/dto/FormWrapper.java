package com.project.cardPaymentHandler.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class FormWrapper {

	@NotEmpty
	private String sellerBankAccountNumber;	
	
	@NotEmpty
	private String sellerClientName;
	
	@NotEmpty
	private String sellerUsername;
	
	@NotEmpty	
	@Length(min = 3, max = 7)
	private String sellerPassword;
	
	@NotEmpty
	private String txSuccessUrl;
	
	@NotEmpty
	private String txFailedUrl;
	
	@NotEmpty
	private String txErrorUrl;
	
	@NotEmpty
	private String sellerFk;
	    
		public FormWrapper() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		public String getSellerFk() {
			return sellerFk;
		}

		public void setSellerFk(String sellerFk) {
			this.sellerFk = sellerFk;
		}

		public String getTxSuccessUrl() {
			return txSuccessUrl;
		}

		public void setTxSuccessUrl(String txSuccessUrl) {
			this.txSuccessUrl = txSuccessUrl;
		}

		public String getTxFailedUrl() {
			return txFailedUrl;
		}

		public void setTxFailedUrl(String txFailedUrl) {
			this.txFailedUrl = txFailedUrl;
		}

		public String getTxErrorUrl() {
			return txErrorUrl;
		}

		public void setTxErrorUrl(String txErrorUrl) {
			this.txErrorUrl = txErrorUrl;
		}




		public String getSellerBankAccountNumber() {
			return sellerBankAccountNumber;
		}




		public void setSellerBankAccountNumber(String sellerBankAccountNumber) {
			this.sellerBankAccountNumber = sellerBankAccountNumber;
		}




		public String getSellerClientName() {
			return sellerClientName;
		}




		public void setSellerClientName(String sellerClientName) {
			this.sellerClientName = sellerClientName;
		}




		public String getSellerUsername() {
			return sellerUsername;
		}




		public void setSellerUsername(String sellerUsername) {
			this.sellerUsername = sellerUsername;
		}




		public String getSellerPassword() {
			return sellerPassword;
		}




		public void setSellerPassword(String sellerPassword) {
			this.sellerPassword = sellerPassword;
		}
		
	    
	    
}
