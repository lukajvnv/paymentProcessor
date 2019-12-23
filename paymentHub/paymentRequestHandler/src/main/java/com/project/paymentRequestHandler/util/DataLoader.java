package com.project.paymentRequestHandler.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.repository.PaymentTypeRepository;
import com.project.paymentRequestHandler.repository.SellerInfoRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private SellerInfoRepository sellerInfoRepository;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		createPaymentTypes();
		createSellerInfoAccount();
		
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
	
	
	

	
	private void createPaymentTypes() {
//		PaymentType paymentType1 = new PaymentType("CARD", "cardPaymentHandler", "https://localhost:8763");
//		PaymentType paymentType2 = new PaymentType("BITCOIN", "bitcoinHandler", "https://localhost:8764");
//		PaymentType paymentType3 = new PaymentType("PAYPAL", "payPalHandler", "https://localhost:8765");

		PaymentType paymentType1 = new PaymentType("CARD", "cardPaymentHandler", "https://localhost:8762/cardPaymentHandler/card/pay");
		PaymentType paymentType2 = new PaymentType("BITCOIN", "bitcoinHandler", "https://localhost:8764/bitCoin/createOrder");
		PaymentType paymentType3 = new PaymentType("PAYPAL", "payPalHandler", "https://localhost:8762/payPalHandler");
		
		paymentTypeRepository.save(paymentType1);
		paymentTypeRepository.save(paymentType2);
		paymentTypeRepository.save(paymentType3);
	}

}
