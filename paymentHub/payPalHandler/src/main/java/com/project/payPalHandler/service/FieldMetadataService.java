package com.project.payPalHandler.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.payPalHandler.dto.PaymentTypeFormFieldDto;
import com.project.payPalHandler.model.FieldMetadata;
import com.project.payPalHandler.model.Seller;
import com.project.payPalHandler.repository.IFieldMetadataRepository;
import com.project.payPalHandler.repository.ISellerRepository;

@Service
public class FieldMetadataService {
	
	@Autowired
	private IFieldMetadataRepository fieldMetadataRepository;
	
	@Autowired
	private ISellerRepository sellerRepository;
	
	public List<FieldMetadata> getFields(){
		return fieldMetadataRepository.findAll();
	}
	
	public void addNewSeller(Map<String, PaymentTypeFormFieldDto> map, Long sellerIdentifierFk) {
		PaymentTypeFormFieldDto email = map.get("email");
	
		//!!!org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save(): com.project.payPalHandler.model.Seller
		Seller seller = new Seller();
		seller.setSellerId(3l);
		seller.setEmail((String)email.getFieldValue());
		seller.setSellerIdentifier(sellerIdentifierFk);

		
		sellerRepository.save(seller);
	}
	
	

}
