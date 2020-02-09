package com.project.syncService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.syncService.dto.AuthorizeRequest;
import com.project.syncService.model.WebshopClient;
import com.project.syncService.repository.WebshopClientRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private WebshopClientRepository webshopClientRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		initWebshopClient();
	}
	
	private void initWebshopClient() {
		WebshopClient webshopClient1Demo = new WebshopClient();
		webshopClient1Demo.setClientHost("localhost:8836");
		
		WebshopClient webshopClient2Luka = new WebshopClient();
		webshopClient2Luka.setClientHost("localhost:8085");
		
		WebshopClient webshopClient3Viktor = new WebshopClient();
		webshopClient3Viktor.setClientHost("localhost:8088");
		
		WebshopClient webshopClient4Srdjan = new WebshopClient();
		webshopClient4Srdjan.setClientHost("localhost:8836");
		
		webshopClientRepository.save(webshopClient1Demo);
		webshopClientRepository.save(webshopClient2Luka);
		webshopClientRepository.save(webshopClient3Viktor);
//		webshopClientRepository.save(webshopClient4Srdjan);

	}

}
