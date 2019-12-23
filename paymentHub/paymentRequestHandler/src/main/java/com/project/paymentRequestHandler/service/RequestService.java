package com.project.paymentRequestHandler.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.paymentRequestHandler.dto.PaymentTypeDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeRequestDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;
import com.project.paymentRequestHandler.repository.SellerInfoRepository;

@Service
public class RequestService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private SellerInfoRepository sellerInfoRepository;
	
	public PaymentTypeResponseDTO getSupportedPaymentTypes(PaymentTypeRequestDTO request) {
		SellerInfo sellerInfo = sellerInfoRepository.findBySellerDBId(request.getSellerId());
		if(sellerInfo == null) {
			
		}
		ArrayList<PaymentTypeDTO> paymentTypesDTO = createPaymentTypeDTOList(sellerInfo);
		PaymentTypeResponseDTO response = new PaymentTypeResponseDTO(sellerInfo.getSellerDBId(), paymentTypesDTO);
		
		return response;
	}
	
	private ArrayList<PaymentTypeDTO> createPaymentTypeDTOList(SellerInfo sellerInfo) {
		ArrayList<PaymentTypeDTO> retVal = new ArrayList<PaymentTypeDTO>();
		
		Set<PaymentType> paymentTypes = sellerInfo.getPaymentTypes();
		for(PaymentType type: paymentTypes) {
			retVal.add(new PaymentTypeDTO(type.getPaymentTypeId(), type.getPaymentTypeName(), type.getPaymentTypeHandlerName(), type.getPaymentTypeHandlerUrl()));
		}
		
		return retVal;
	}
	
	
}