package com.project.pcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PccApplication {
	
	public static void main(String[] args) {
		initAttributes();
		SpringApplication.run(PccApplication.class, args);
		
	}
	
	private static void initAttributes() {
		System.setProperty("KEY_ALIAS", "pcc");
		System.setProperty("KEY_STORE_PATH", "src/main/resources/kps_pcc_service_keystore.jks");
		System.setProperty("KEY_STORE_PASSWWORD", "sepstore");
		System.setProperty("TRUST_STORE_PATH", "src/main/resources/kps_pcc_service_truststore.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "sepstore");
		System.setProperty("DB_USERNAME", "postgres");
		System.setProperty("DB_PASSWORD", "postgres");
		System.setProperty("ADDRESS", "127.0.0.1");

		System.setProperty("PORT", "8951");

	}

}
