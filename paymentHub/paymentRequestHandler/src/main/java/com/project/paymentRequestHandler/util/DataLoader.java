package com.project.paymentRequestHandler.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.paymentRequestHandler.model.NewClientRequest;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.model.TxInfo;
import com.project.paymentRequestHandler.repository.NewClientRequestRepository;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;
import com.project.paymentRequestHandler.repository.SellerInfoRepository;
import com.project.paymentRequestHandler.repository.TxInfoRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private SellerInfoRepository sellerInfoRepository;
	
	@Autowired
	private NewClientRequestRepository newClientRequestRepository;
	
	@Autowired
	private TxInfoRepository txInfoRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		//ako zelimo da ostanu isti pod
//		if(true) {
//			return;
//		}
		
		createPaymentTypes();
		createSellerInfoAccount();
		
		createNewClientRequest();
		
		createTxInfoForTesting();
	}
	
	
	
	private void createSellerInfoAccount() throws IOException {
		List<PaymentType> paymentTypes = paymentTypeRepository.findAll();
		Set<PaymentType> paymentTypesSet = new HashSet<PaymentType>(paymentTypes);
		
		// VAZNO: requestPaymentHandler.SellerInfo.sellerIdentifier -> id iz NC neki
		SellerInfo sellerInfo1 = new SellerInfo(1L, 1L, "casopisA", "4563152131", paymentTypesSet);
		SellerInfo sellerInfo2 = new SellerInfo(2L, 2L, "casopisB", "4323255433", paymentTypesSet);
		SellerInfo sellerInfo3 = new SellerInfo(3L, 3L, "casopisC", "3553253253", paymentTypesSet);
		
		sellerInfoRepository.save(sellerInfo1);
		sellerInfoRepository.save(sellerInfo2);
		sellerInfoRepository.save(sellerInfo3);

	}
	
	private void createNewClientRequest() throws IOException {
		NewClientRequest newClientRequest = new NewClientRequest(1l, "");
		
		newClientRequestRepository.save(newClientRequest);
	}
	
	
	private void createTxInfoForTesting() {
		TxInfo txInfo = new TxInfo(1l, 1l, "https://localhost:8766/custom", "");
		
		txInfoRepo.save(txInfo);
	}

	
	private void createPaymentTypes() {

		PaymentType paymentType1 = new PaymentType("CARD", "cardPaymentHandler", "https://localhost:8763/card/pay", "https://localhost:8763/");
		PaymentType paymentType2 = new PaymentType("BITCOIN", "bitcoinHandler", "https://localhost:8764/bitCoin/createOrder", "https://localhost:8764/");
		PaymentType paymentType3 = new PaymentType("PAYPAL", "payPalHandler", "https://localhost:8765/payPal", "https://localhost:8765/");
		PaymentType paymentType4 = new PaymentType("CUSTOM", "customPaymentHandler", "https://localhost:8766/custom/pay", "https://localhost:8766/");


		paymentTypeRepository.save(paymentType1);
		paymentTypeRepository.save(paymentType2);
		paymentTypeRepository.save(paymentType3);
		//paymentTypeRepository.save(paymentType4);

	}
	
}
