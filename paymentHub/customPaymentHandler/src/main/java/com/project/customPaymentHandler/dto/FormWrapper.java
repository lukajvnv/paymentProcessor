package com.project.customPaymentHandler.dto;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {

		private MultipartFile image;
	    
		private String txSuccessUrl;
	    private String txFailedUrl;
	    private String txErrorUrl;

	    
	    private String sellerFk;
	    
		public FormWrapper() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public MultipartFile getImage() {
			return image;
		}
		public void setImage(MultipartFile image) {
			this.image = image;
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
		
	    
	    
}
