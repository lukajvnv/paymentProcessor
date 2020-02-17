package com.project.cardPaymentService;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.project.cardPaymentService.service.AuthorizationService;
import com.project.cardPaymentService.service.ValidationService;
import com.project.cardPaymentService.util.Base64Utility;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class CardPaymentServiceApplication {

	public static void main(String[] args) {
		initAttributes();
		SpringApplication.run(CardPaymentServiceApplication.class, args);
		
		
//		  AuthorizationService service = new AuthorizationService();
//		  service.generateSensitiveData();
//		  
//		  boolean ret1 = service.authenticate("5512365555555",
//		  "2G0N7xdURclaU7v48QlryWyaFCImlYENYtejYeZxBuU=", "Nse2fiAnu2j7heNHF2IKEA==");
//		  
//		  boolean ret2 = service.authenticate("5512365444555",
//		  "SSvXF+7xd42mUKzCFnP6DfgEe1Nuq7JfI1dlkJF30I0=", "SjypG09D96JxurUvF6Bn3Q==");
//		  System.out.println(ret1 + ", " + ret2);
//		  
//		  boolean ret3 = service.authenticate("4512365653214568",
//				  "8yK+EsfQKDnmw6y8dm02gu0uSmgz6CfwwTo706WTwdM=", "WCPGGE/2O3/LjjCpbyy/cA==");
//				  
//		  boolean ret4 = service.authenticate("4864236653214568",
//				  "XNq8w8Fl0AZyMguEhjTOi6IXG3R21ZRa7K6CwfKFGcE=", "TIsBkdQiDD6OWNxP9Y4Ngg==");
//				  System.out.println(ret1 + ", " + ret2);
//				  
//		  boolean ret5 = service.authenticate("5864236444555",
//						  "e3q++Xd/8XvYgV4EChl8aEXN4kuwE3wUZKqIf7IQCbI=", "WWNVKghwc0keEcE8R+TD6g==");
//						  
//		  boolean ret6 = service.authenticate("5864236555555",
//						  "lMEynNzNVHtZTu2VJUzShveIJ5OWFY/25AcNYCSn3Vc=", "AyuhtZvk+gpzk+DAiYYvZA==");
//						  System.out.println(ret1 + ", " + ret2);				  
//		 
//		 System.out.println("**************************");
//		 System.out.println(ret1);
//		 System.out.println(ret2);
//		 System.out.println(ret3);
//		 System.out.println(ret4);
//		 System.out.println(ret5);
//		 System.out.println(ret6);

		
//		ValidationService s = new ValidationService();
//		s.getUrl();
//		boolean v1 = s.validateAcccount("casopisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=");
//		boolean v2 = s.validateAcccount("casapisA", "ktP9zj/U1zRo2x+1+s/0ggjHZLl45XnLy8xIINHTpck=");


	}
	
	private static void initAttributes() {
		System.setProperty("KEY_ALIAS", "cardpaymentservice");
		System.setProperty("KEY_STORE_PATH", "src/main/resources/kps_card_service_keystore.jks");
		System.setProperty("KEY_STORE_PASSWWORD", "sepstore");
		System.setProperty("TRUST_STORE_PATH", "src/main/resources/kps_card_service_truststore.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "sepstore");
		System.setProperty("DB_USERNAME", "postgres");
		
		System.setProperty("DB_PASSWORD", "postgres");
		System.setProperty("INSTANCE_HOSTNAME", "localhost");
		System.setProperty("SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("ADDRESS", "127.0.0.1");
		
		System.setProperty("SERVICE_URL_FRONT", "https://localhost:4300/pay");
		
		//promenljivo
		System.setProperty("BANK_PAN_NUM", "512365");
		System.setProperty("DB_NAME", "cardPaymentService");
		System.setProperty("PORT", "8841");


	}

}
