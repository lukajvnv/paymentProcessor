package com.project.bitcoinHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bitcoinHandler.dto.BitCoinResponseDTO;
import com.project.bitcoinHandler.dto.GetOrderResponseDTO;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableScheduling
public class BitcoinHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinHandlerApplication.class, args);
	}
	
}
