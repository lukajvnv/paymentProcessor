package com.project.cardPaymentHandler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardPaymentHandler.dto.PaymentTypeFormFieldDto;
import com.project.cardPaymentHandler.model.FieldMetadata;
import com.project.cardPaymentHandler.service.FieldMetadataService;

@RestController
@RequestMapping("/field")
public class FieldMetadataController {
	
	@Autowired
	private FieldMetadataService fieldMetadataService;

	@GetMapping(path = "/all")
	public ResponseEntity<List<FieldMetadata>> getAllFieldsMeta(){
		return new ResponseEntity<List<FieldMetadata>>(fieldMetadataService.getFields(), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/newClient/{sellerIdentifierFk}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> newClient(@RequestBody List<PaymentTypeFormFieldDto> fields, @PathVariable long sellerIdentifierFk){
		Map<String, PaymentTypeFormFieldDto> map = fromListToMap(fields);

		fieldMetadataService.addNewSeller(map, sellerIdentifierFk);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	private Map<String, PaymentTypeFormFieldDto> fromListToMap(List<PaymentTypeFormFieldDto> list){
		Map<String, PaymentTypeFormFieldDto> map = new HashMap<String, PaymentTypeFormFieldDto>();
		
		for(PaymentTypeFormFieldDto field: list) {
			map.put(field.getFieldName(), field);
		}
		
		return map;
	}
}
