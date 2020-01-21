package com.project.pcc.util;

import java.sql.Timestamp;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.pcc.PccApplication;
import com.project.pcc.model.BankInfo;
import com.project.pcc.repository.BankInfoRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private BankInfoRepository bankInfoRepository;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DataLoader.class);


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("fa {}", DataLoader.class.getName());
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		initBankRoutes();
	}
	
	private void initBankRoutes() {
		BankInfo bankInfo1 = new BankInfo(1l, "Banca Intesa Beograd d.o.o", "512365", "https://localhost:8841/card/payViaPcc");
		BankInfo bankInfo2 = new BankInfo(2l, "RaifeissenBank Beograd d.o.o", "864236", "https://localhost:8851/card/payViaPcc");
		BankInfo bankInfo3 = new BankInfo(3l, "Unicredit bank Beograd d.o.o", "335478", "https://localhost:8861/card/payViaPcc");
		
		bankInfoRepository.save(bankInfo1);
		bankInfoRepository.save(bankInfo2);
		bankInfoRepository.save(bankInfo3);
	}

}
