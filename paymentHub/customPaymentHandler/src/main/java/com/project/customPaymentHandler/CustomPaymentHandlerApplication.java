package com.project.customPaymentHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomPaymentHandlerApplication {

	public static void main(String[] args) {
		initAttributes();
		SpringApplication.run(CustomPaymentHandlerApplication.class, args);
	}
	
	private static void initAttributes() {
		System.setProperty("KEY_ALIAS", "cardpaymenthandler");
		System.setProperty("KEY_STORE_PATH", "src/main/resources/kps_card_handler_keystore.jks");
		System.setProperty("KEY_STORE_PASSWWORD", "sepstore");
		System.setProperty("TRUST_STORE_PATH", "src/main/resources/kps_card_handler_truststore.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "sepstore");
		
		System.setProperty("DB_USERNAME", "postgres");
		System.setProperty("DB_PASSWORD", "postgres");
		
		System.setProperty("INSTANCE_HOSTNAME", "localhost");
		System.setProperty("SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		
		System.setProperty("ADDRESS", "127.0.0.1");
		
		System.setProperty("PORT", "8766");

	}

}
