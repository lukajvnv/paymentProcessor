package com.project.cardPaymentHandler.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import com.project.cardPaymentHandler.util.Base64Utility;
import com.project.cardPaymentHandler.util.KeyStoreHelper;

@Configuration
public class SecurityConfig {
	
	private static final String KEYSTOREFILEPATH = "src/main/resources/kps_card_handler_keystore.jks";
	private static final String TRUSTSTOREFILEPATH = "src/main/resources/kps_card_handler_truststore.jks";
	
	private static final String CRYPTOFILEPATH = "src/main/resources/cardHandler.jks";
	private static final String PROPERTIESPATH = "src/main/resources/application.properties";
	
	@Bean 
	public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", KEYSTOREFILEPATH);
		System.setProperty("javax.net.ssl.keyStorePassword", "sepstore");
		System.setProperty("javax.net.ssl.trustStore", TRUSTSTOREFILEPATH);
		System.setProperty("javax.net.ssl.trustStorePassword", "sepstore");
		
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("cardPaymentHandler");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		
		return args;
	}
	
	@Bean
	public SecretKey key() throws IOException {
		KeyStoreHelper helper = new KeyStoreHelper();
		helper.loadKeyStore(CRYPTOFILEPATH, "sepstore".toCharArray());
		PrivateKey key = helper.readPrivateKey("sepstore", "cardhandler", "sepstore");
		
		Properties p = new Properties();
		p.load(new FileInputStream(PROPERTIESPATH));
		String encodedEncryptedKey = p.getProperty("symKey");
		byte[] decodedEncryptedKey = Base64Utility.decode(encodedEncryptedKey);
		
		byte[] decodedDectyptedKey = helper.decrypt(decodedEncryptedKey, key);
		
		return new SecretKeySpec(decodedDectyptedKey, 0, decodedDectyptedKey.length, "AES");
	}

}
