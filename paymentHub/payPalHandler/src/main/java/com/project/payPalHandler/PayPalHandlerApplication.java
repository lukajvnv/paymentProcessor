package com.project.payPalHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PayPalHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayPalHandlerApplication.class, args);
	}

}
