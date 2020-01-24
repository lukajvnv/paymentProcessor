package com.project.bitcoinHandler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.bitcoinHandler.model.FieldMetadata;
import com.project.bitcoinHandler.model.SellerBitcoinInfo;
import com.project.bitcoinHandler.repository.BitcoinRepository;
import com.project.bitcoinHandler.repository.FieldMetadataRepository;


@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private BitcoinRepository repo;
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		// createCardAccount();
		createSellerBitcoinInfo();
		createFieldMetadata();
	}
	

	private void createSellerBitcoinInfo() {
		SellerBitcoinInfo sbi = new SellerBitcoinInfo(1l,"ab7fzPdN49-xBVoY_LdSifCZiVrqCbdcfjWdweJS","223783");
		repo.save(sbi);
	}
	
	private void createFieldMetadata() {
		
		FieldMetadata field1 = new FieldMetadata("bitcoinAddress", "String", "text");
		
		
		fieldMetadataRepository.save(field1);
		


	}

}
