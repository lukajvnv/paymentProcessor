package com.project.paymentRequestHandler.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.paymentRequestHandler.dto.FieldMetadata;
import com.project.paymentRequestHandler.dto.PaymentTypeDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeFormDto;
import com.project.paymentRequestHandler.dto.PaymentTypeFormFieldDto;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;
import com.project.paymentRequestHandler.repository.SellerInfoRepository;

@Service
public class ClientService {

	@Autowired
	private SellerInfoRepository sellerInfoRepository;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	public SellerInfo saveSellerInfo(SellerInfo newClient) {
		return sellerInfoRepository.save(newClient);
	}
	
//	public Map<Long, PaymentTypeFormDto> prepareFields() {
//		Map<Long, PaymentTypeFormDto> forms = new HashMap<Long, PaymentTypeFormDto>();
//
//		
//		List<PaymentType> allPaymentTypes = paymentTypeRepository.findAll();
//		for(PaymentType paymentType : allPaymentTypes) {
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<FieldMetadata[]> fieldsMeta = restTemplate.getForEntity(paymentType.getPaymentTypeHandlerUrlRoot() + "field/all", FieldMetadata[].class);
//			List<FieldMetadata> metaFieldData = Arrays.asList(fieldsMeta.getBody());
//			List<PaymentTypeFormFieldDto> fields = new ArrayList<PaymentTypeFormFieldDto>();
//			PaymentTypeDTO paymentTypeDto = new PaymentTypeDTO(paymentType.getPaymentTypeId(), paymentType.getPaymentTypeName(), paymentType.getPaymentTypeHandlerName(), paymentType.getPaymentTypeHandlerUrl(), paymentType.getPaymentTypeHandlerUrlRoot());
//			
//			for (FieldMetadata fM : metaFieldData) {
//				fields.add(new PaymentTypeFormFieldDto(fM.getFieldId(), fM.getFieldName(), fM.getFieldTypeBack(), fM.getFieldTypeFront()));
//			}
//			
//			PaymentTypeFormDto form = new PaymentTypeFormDto(paymentTypeDto, fields);
//			forms.put(paymentTypeDto.getPaymentTypeId(), form);
//		}
//		
//		return forms;
//	}
//	
//	public void submitPaymentData(Map<Long, PaymentTypeFormDto> forms, Long newClientId) {
//		
//		//payment types added to seller
//		SellerInfo sellerInfo = sellerInfoRepository.getOne(newClientId);
//		List<Long> paymentTypesId = forms.keySet().stream().collect(Collectors.toList());
//		Set<PaymentType> selectedPaymentTypes = paymentTypeRepository.findAllById(paymentTypesId).stream().collect(Collectors.toSet());
//		sellerInfo.setPaymentTypes(selectedPaymentTypes);
//		sellerInfoRepository.save(sellerInfo);
//		
//		RestTemplate restTemplate = new RestTemplate();
//		for(PaymentTypeFormDto form : forms.values()) {
//			PaymentTypeDTO paymentType = form.getPaymentType();
//			String prepareUrlSuffix = "field/newClient/" + sellerInfo.getSellerDBId();
//			ResponseEntity<Object> response = restTemplate.postForEntity(paymentType.getPaymentTypeHandlerUrlRoot() + prepareUrlSuffix, form.getFormFields(), Object.class);
//		}
//	}
	
	public List<PaymentTypeDTO> prepareTypes() {
		List<PaymentTypeDTO> list = new ArrayList<PaymentTypeDTO>();

		List<PaymentType> allPaymentTypes = paymentTypeRepository.findAll();
		for(PaymentType paymentType : allPaymentTypes) {
			PaymentTypeDTO paymentTypeDto = new PaymentTypeDTO(paymentType.getPaymentTypeId(), paymentType.getPaymentTypeName(), paymentType.getPaymentTypeHandlerName(), paymentType.getPaymentTypeHandlerUrl(), paymentType.getPaymentTypeHandlerUrlRoot());
			
			list.add(paymentTypeDto);
		}
		
		return list;
	}
	
	public void submitNewClientPaymentTypes(List<PaymentTypeDTO> selectedTypes, Long newClientId) {
		
		//payment types added to seller
		SellerInfo sellerInfo = sellerInfoRepository.getOne(newClientId);
		List<Long> paymentTypesId = selectedTypes.stream().map(PaymentTypeDTO::getPaymentTypeId).collect(Collectors.toList());
		Set<PaymentType> selectedPaymentTypes = paymentTypeRepository.findAllById(paymentTypesId).stream().collect(Collectors.toSet());
		sellerInfo.setPaymentTypes(selectedPaymentTypes);
		sellerInfoRepository.save(sellerInfo);
		
	}
	
//	public List<String> prepareFields(List<PaymentTypeDTO> selectedTypes, Long newClientId) {
//		
//		//payment types added to seller
//		List<String> retVal = new ArrayList<String>();
//		
//		RestTemplate restTemplate = new RestTemplate();
//		for(PaymentTypeDTO type : selectedTypes) {
//			String prepareUrlSuffix = "field/getHtml/" + newClientId;
//			ResponseEntity<String> response = restTemplate.getForEntity(type.getPaymentTypeHandlerUrlRoot() + prepareUrlSuffix, String.class);
//		
//			retVal.add(response.getBody());
//		}
//		
//		return retVal;
//	}
	
	public Map<Long, PaymentTypeFormDto> prepareFields(List<PaymentTypeDTO> selectedTypes, Long newClientId) {
		
		Map<Long, PaymentTypeFormDto> forms = new HashMap<Long, PaymentTypeFormDto>();

		
		RestTemplate restTemplate = new RestTemplate();
		for(PaymentTypeDTO paymentType : selectedTypes) {
			
			ResponseEntity<FieldMetadata[]> fieldsMeta = restTemplate.getForEntity(paymentType.getPaymentTypeHandlerUrlRoot() + "field/all", FieldMetadata[].class);
			List<FieldMetadata> metaFieldData = Arrays.asList(fieldsMeta.getBody());
			List<PaymentTypeFormFieldDto> fields = new ArrayList<PaymentTypeFormFieldDto>();
			
			for (FieldMetadata fM : metaFieldData) {
				fields.add(new PaymentTypeFormFieldDto(fM.getFieldId(), fM.getFieldName(), fM.getFieldTypeBack(), fM.getFieldTypeFront()));
			}
			
			PaymentTypeFormDto form = new PaymentTypeFormDto(paymentType, fields);
			forms.put(paymentType.getPaymentTypeId(), form);
		}
		
		return forms;
	}
}
