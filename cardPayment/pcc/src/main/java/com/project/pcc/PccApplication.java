package com.project.pcc;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PccApplication {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PccApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PccApplication.class, args);
		
		logger.info("start info test");
		logger.warn("start info warn");
		logger.error("start info error");

		
	}

}
