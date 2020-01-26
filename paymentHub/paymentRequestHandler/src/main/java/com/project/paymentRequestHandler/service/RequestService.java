package com.project.paymentRequestHandler.service;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.paymentRequestHandler.util.Generator;
import com.project.paymentRequestHandler.repository.IdGeneratorRepository;
import com.project.paymentRequestHandler.dto.PaymentTypeDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeRequestDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;
import com.project.paymentRequestHandler.dto.ShoppingCartDTO;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.model.ShoppingCart;
import com.project.paymentRequestHandler.model.NewClientRequest;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.repository.NewClientRequestRepository;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;
import com.project.paymentRequestHandler.repository.SellerInfoRepository;
import com.project.paymentRequestHandler.repository.ShoppingCartRepository;

@Service
public class RequestService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private SellerInfoRepository sellerInfoRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepo;
	
	@Autowired
	private ShoppingCartRepository cartRepo;
	@Autowired
	private NewClientRequestRepository newClientRequestRepository;
	
	@Autowired
	private IdGeneratorRepository idGeneratorRepository;
	
	public PaymentTypeResponseDTO getSupportedPaymentTypes(PaymentTypeRequestDTO request) {
		
		SellerInfo sellerInfo = sellerInfoRepository.findBySellerDBId(request.getSellerId());
		if(sellerInfo == null) {
			
		}
		
		//shoppingCartRepo.findByOrderId(request.getOrderId());
		
		ArrayList<PaymentTypeDTO> paymentTypesDTO = createPaymentTypeDTOList(sellerInfo);
		PaymentTypeResponseDTO response = new PaymentTypeResponseDTO(sellerInfo.getSellerDBId(), 
				paymentTypesDTO, "https://localhost:4666/pay/"+ request.getSellerId() + "/" + request.getOrderId());
		
		return response;
	}
	
	public Long saveShoppingCartTemp(ShoppingCart request) {
		
		//ovde idemo random generator za orderId
		Long orderId = generateOrderId();
		request.setOrderId(orderId);
		
		cartRepo.save(request);
		
		//ici ce return taj orderId
		return orderId;
		
	}
	
	private long generateOrderId() {
//		Random random = new Random(System.nanoTime());
//		long number = random.nextLong();
		Generator g = idGeneratorRepository.save(new Generator());
		long number = g.getGeneratedId();
		return number;
	}
	
	public ShoppingCart getPrice(Long orderId) {
		ShoppingCart sc = cartRepo.findByOrderId(orderId);
		//ShoppingCart sc = cartRepo.findBySellerId(sellerId);
		
		return sc;
	}
	
	private ArrayList<PaymentTypeDTO> createPaymentTypeDTOList(SellerInfo sellerInfo) {
		ArrayList<PaymentTypeDTO> retVal = new ArrayList<PaymentTypeDTO>();
		
		Set<PaymentType> paymentTypes = sellerInfo.getPaymentTypes();
		for(PaymentType type: paymentTypes) {
			retVal.add(new PaymentTypeDTO(type.getPaymentTypeId(), type.getPaymentTypeName(), type.getPaymentTypeHandlerName(), type.getPaymentTypeHandlerUrl(), type.getPaymentTypeHandlerUrlRoot()));
		}
		
		return retVal;
	}
	
	public NewClientRequest saveNewRequest(NewClientRequest newClientRequest) {
		return newClientRequestRepository.save(newClientRequest);
	}
	
	public NewClientRequest getNewClientRequest(long requestId) {
		return newClientRequestRepository.getOne(requestId);
	}
	
	
	
	
}
