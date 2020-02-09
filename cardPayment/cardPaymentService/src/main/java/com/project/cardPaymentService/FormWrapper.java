package com.project.cardPaymentService;

import org.springframework.web.multipart.MultipartFile;

public class FormWrapper {

		private MultipartFile image;
	    private String title;
	    private String description;
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	    
	    
}
