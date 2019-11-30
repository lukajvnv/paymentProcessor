package com.project.scienceCenter.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BasicConfig {

    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedHeaders("*")
					.allowedMethods("*")
					.allowedOrigins("*");
			}			
		};
    }
    
    @LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
    
   
}
