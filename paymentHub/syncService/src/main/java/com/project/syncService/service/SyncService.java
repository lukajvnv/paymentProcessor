package com.project.syncService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.syncService.model.WebshopClient;
import com.project.syncService.repository.WebshopClientRepository;

@Service
public class SyncService {

	@Autowired
	private WebshopClientRepository webShopClientRepository;
	
	public boolean webShopClientExist(String host) {
		return webShopClientRepository.findByClientHost(host) != null ? true : false;
	}
	
	public WebshopClient save(WebshopClient webshopClient) {
		return webShopClientRepository.save(webshopClient);
	}
}
