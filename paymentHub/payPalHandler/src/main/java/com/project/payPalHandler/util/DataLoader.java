package com.project.payPalHandler.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.payPalHandler.model.Seller;
import com.project.payPalHandler.repository.ISellerRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private ISellerRepository sellerRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		seedSeller();
	}

	public void seedSeller() {
		Seller seller = new Seller(1L, "srdjan.popovic@gmail.com");
		sellerRepository.save(seller);
	}
	
}

