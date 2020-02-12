package com.project.customPaymentHandler.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customPaymentHandler.dto.FormWrapper;
import com.project.customPaymentHandler.model.CustomSellerClientInfo;
import com.project.customPaymentHandler.model.FieldMetadata;
import com.project.customPaymentHandler.repository.CustomClientInfoRepository;
import com.project.customPaymentHandler.repository.FieldMetadataRepository;

@Service
public class FieldMetadataService {
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;
	
	@Autowired
	private CustomClientInfoRepository customClientRepo;
	
	public List<FieldMetadata> getFields(){
		return fieldMetadataRepository.findAll();
	}
	
	public void addNewSeller(FormWrapper form) throws Exception {
		Long sellerFk = Long.parseLong(form.getSellerFk());
		
		CustomSellerClientInfo newSeller;
		try {
			newSeller = new CustomSellerClientInfo(sellerFk, form.getTxSuccessUrl(), form.getTxFailedUrl(), form.getTxErrorUrl(), form.getImage().getBytes());
			customClientRepo.save(newSeller);
		} catch (IOException e) {
			throw new Exception();
		}
		
	}
	
	

}
