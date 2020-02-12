package com.project.customPaymentHandler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.customPaymentHandler.model.CustomSellerClientInfo;
import com.project.customPaymentHandler.model.FieldMetadata;
import com.project.customPaymentHandler.repository.CustomClientInfoRepository;
import com.project.customPaymentHandler.repository.FieldMetadataRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CustomClientInfoRepository customClientInfoRepo;
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;

	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

		
		
		createCustomSellerInfo();
		
		createFieldMetadata();
	}
	
      private void createFieldMetadata() {
		
		FieldMetadata field1 = new FieldMetadata("txSuccessUrl", "String", "text");
		FieldMetadata field2 = new FieldMetadata("txFailedUrl", "String", "text");
		FieldMetadata field3 = new FieldMetadata("txErrorUrl", "String", "text");
		FieldMetadata field4 = new FieldMetadata("image", "byte[]", "file");
		
		
		fieldMetadataRepository.save(field1);
		fieldMetadataRepository.save(field2);
		fieldMetadataRepository.save(field3);
		fieldMetadataRepository.save(field4);
		
	}
      
    private void createCustomSellerInfo() {
    	CustomSellerClientInfo customSeller1 = new CustomSellerClientInfo(1l, "https://localhost:4200/success", "https://localhost:4200/failed", "https://localhost:4200/error");
    	
		customClientInfoRepo.save(customSeller1);
    }

}
