package com.project.bitcoinHandler.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.bitcoinHandler.model.SellerBitcoinInfo;
import com.project.bitcoinHandler.repository.BitcoinRepository;


@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private BitcoinRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		// createCardAccount();
		createSellerBitcoinInfo();
	}
	

	private void createSellerBitcoinInfo() {
		SellerBitcoinInfo sbi = new SellerBitcoinInfo(4l,"ab7fzPdN49-xBVoY_LdSifCZiVrqCbdcfjWdweJS","223783");
		repo.save(sbi);
	}

}
