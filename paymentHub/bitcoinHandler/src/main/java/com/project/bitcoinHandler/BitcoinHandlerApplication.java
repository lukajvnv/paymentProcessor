package com.project.bitcoinHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BitcoinHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinHandlerApplication.class, args);
	}

}
