package com.project.bitcoinHandler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bitcoinHandler.dto.FormWrapper;
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
	
	public void addNewSeller(FormWrapper form) throws Exception {
		Long sellerFk = Long.parseLong(form.getSellerFk());
		
		SellerBitcoinInfo newBitcoinSeller = new SellerBitcoinInfo();
		newBitcoinSeller.setBitcoinAddress(form.getBitcoinAddress());
		newBitcoinSeller.setSellerIdentifier(sellerFk);
		
		sellerBitcoinRepository.save(newBitcoinSeller);
		
	}
	
	

}
