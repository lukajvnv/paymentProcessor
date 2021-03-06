package com.project.cardPaymentHandler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cardPaymentHandler.dto.FormWrapper;
import com.project.cardPaymentHandler.model.FieldMetadata;
import com.project.cardPaymentHandler.model.SellerBankInfo;
import com.project.cardPaymentHandler.repository.FieldMetadataRepository;
import com.project.cardPaymentHandler.repository.SellerBankInfoRepository;

@Service
public class FieldMetadataService {
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;
	
	@Autowired
	private SellerBankInfoRepository sellerBankInfoRepository;
	
	public List<FieldMetadata> getFields(){
		return fieldMetadataRepository.findAll();
	}
	
	public void addNewSeller(FormWrapper form) {
		Long sellerFk = Long.parseLong(form.getSellerFk());
		
		SellerBankInfo newSellerBankInfo = new SellerBankInfo(sellerFk, 
				form.getSellerBankAccountNumber(), 
				form.getSellerUsername(), 
				form.getSellerPassword(), 
				form.getTxErrorUrl(), 
				form.getTxFailedUrl(), 
				form.getTxErrorUrl(), 
				form.getSellerClientName());
		
		sellerBankInfoRepository.save(newSellerBankInfo);
	}
	
//	public void addNewSeller(Map<String, PaymentTypeFormFieldDto> map, Long sellerIdentifierFk) {
//		PaymentTypeFormFieldDto sellerBankAccountNumber = map.get("sellerBankAccountNumber");
//		PaymentTypeFormFieldDto sellerUsername = map.get("sellerUsername");
//		PaymentTypeFormFieldDto sellerPassword = map.get("sellerPassword");
//		PaymentTypeFormFieldDto txSuccessUrl = map.get("txSuccessUrl");
//		PaymentTypeFormFieldDto txFailedUrl = map.get("txFailedUrl");
//		PaymentTypeFormFieldDto txErrorUrl = map.get("txErrorUrl");
//		PaymentTypeFormFieldDto clientName = map.get("sellerClientName");
//
//		
//		SellerBankInfo newSellerBankInfo = new SellerBankInfo(sellerIdentifierFk, 
//				(String)sellerBankAccountNumber.getFieldValue(), (String)sellerUsername.getFieldValue(), (String)sellerPassword.getFieldValue(), 
//				(String)txSuccessUrl.getFieldValue(), (String)txFailedUrl.getFieldValue(), (String)txErrorUrl.getFieldValue(), (String)clientName.getFieldValue());
//	
//		sellerBankInfoRepository.save(newSellerBankInfo);
//		
//	}
	
	

}
