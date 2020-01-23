package com.project.bitcoinHandler.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bitcoinHandler.dto.PaymentTypeFormFieldDto;
import com.project.bitcoinHandler.model.FieldMetadata;
import com.project.bitcoinHandler.model.SellerBitcoinInfo;
import com.project.bitcoinHandler.repository.BitcoinRepository;
import com.project.bitcoinHandler.repository.FieldMetadataRepository;

@Service
public class FieldMetadataService {
	
	@Autowired
	private FieldMetadataRepository fieldMetadataRepository;
	
	@Autowired
	private BitcoinRepository sellerBitcoinRepository;
	
	public List<FieldMetadata> getFields(){
		return fieldMetadataRepository.findAll();
	}
	
	public void addNewSeller(Map<String, PaymentTypeFormFieldDto> map, Long sellerIdentifierFk) {
		PaymentTypeFormFieldDto bitcoinAddress = map.get("bitcoinAddress");
		

		SellerBitcoinInfo newBitcoinSeller = new SellerBitcoinInfo();
		newBitcoinSeller.setBitcoinAddress((String)bitcoinAddress.getFieldValue());
		newBitcoinSeller.setSellerIdentifier(sellerIdentifierFk);
		
		sellerBitcoinRepository.save(newBitcoinSeller);
		
	}
	
	

}
