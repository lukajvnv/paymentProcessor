package com.project.syncService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SyncServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncServiceApplication.class, args);
	}

}
