package com.project.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		configSystemParams();
		SpringApplication.run(EurekaApplication.class, args);
	}
	
	private static void configSystemParams() {
		// System.setProperty("KEYSTOREPATH", "src/main/resources/kp_sep_keystore");
	}

}
