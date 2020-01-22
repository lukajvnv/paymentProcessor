package com.project.cardPaymentService;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.cardPaymentService.service.AuthorizationService;
import com.project.cardPaymentService.service.ValidationService;
import com.project.cardPaymentService.util.Base64Utility;

@SpringBootApplication
public class CardPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPaymentServiceApplication.class, args);
		
		/*
		 * AuthorizationService service = new AuthorizationService();
		 * service.generateSensitiveData();
		 * 
		 * boolean ret1 = service.authenticate("casopisA",
		 * "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=", "8bflvLaZDvLPwjLjf9Ns2g==");
		 * boolean ret2 = service.authenticate("casapisA",
		 * "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=", "8bflvLaZDvLPwjLjf9Ns2g==");
		 * System.out.println(ret1 + ", " + ret2);
		 */
		
//		ValidationService s = new ValidationService();
//		s.getUrl();
//		boolean v1 = s.validateAcccount("casopisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=");
//		boolean v2 = s.validateAcccount("casapisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=");


	}

}
