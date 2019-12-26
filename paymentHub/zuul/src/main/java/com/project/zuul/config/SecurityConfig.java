package com.project.zuul.config;

import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@Configuration
public class SecurityConfig {
	
	private static final String KEYSTOREFILEPATH = "src/main/resources/kps_zuul_keystore.jks";
	private static final String TRUSTSTOREFILEPATH = "src/main/resources/kps_zuul_truststore.jks";
	
	@Bean 
	public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", KEYSTOREFILEPATH);
		System.setProperty("javax.net.ssl.keyStorePassword", "sepstore");
		System.setProperty("javax.net.ssl.trustStore", TRUSTSTOREFILEPATH);
		System.setProperty("javax.net.ssl.trustStorePassword", "sepstore");
		
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("zuul");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		
		return args;
	}
	
	@Bean
	public CorsFilter CorsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
	
	@Bean
	public ZuulPreFilter preFilter() {
		return new ZuulPreFilter();
	}
	
//	@Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
//       String allPassword = "some-pasword";
//
//        SSLContext sslContext = SSLContextBuilder
//                .create()
//                .loadKeyMaterial(ResourceUtils.getFile("classpath:kp_zuul_keystore.jks"), allPassword.toCharArray(), allPassword.toCharArray())
//                .loadTrustMaterial(ResourceUtils.getFile("classpath:kp_zuul_truststore.jks"), allPassword.toCharArray())
//                .build();
//
//        HttpClient client = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .build();
//
//        return builder.requestFactory(new HttpComponentsClientHttpRequestFactory(client))
//                .build();
//    }

}
