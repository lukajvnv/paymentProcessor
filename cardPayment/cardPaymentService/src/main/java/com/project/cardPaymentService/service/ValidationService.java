package com.project.cardPaymentService.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationRequestDTO;
import com.project.cardPaymentService.dto.PaymentValidationResponseDTO;
import com.project.cardPaymentService.exception.AuthentificationException;
import com.project.cardPaymentService.exception.PaymentRequestException;
import com.project.cardPaymentService.model.BankAccount;
import com.project.cardPaymentService.model.PaymentRequest;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.model.TxStatus;
import com.project.cardPaymentService.repository.UnityOfWork;
import com.project.cardPaymentService.util.Generator;

@Service
public class ValidationService {
	
	@Autowired
	private UnityOfWork unityOfWork;
	
	@Autowired
	private AuthorizationService authService;
	
	@Value("${serviceUrl}")
	private String url;
	
	
	private final Logger logger = LoggerFactory.getLogger(ValidationService.class);
	
	private final long TIME_OFFSET_IN_MILLISECONDS = 300000l;
	
	public BankAccount getAccount(String username) {
		return unityOfWork.getBankAccountRepository().findByBankAccountUsername(username);
	}
	 
	
	public boolean validateAcccount(String username, String password) {
		BankAccount account = unityOfWork.getBankAccountRepository().findByBankAccountUsername(username);
		if(account == null) {
			return false;
		}
		return authService.authenticate(password, account.getBankAccountPassword(), account.getSalt());
	}
	
	public boolean validatePaymentRequest(PaymentValidationRequestDTO request) {
		return true;
	}
	
	public PaymentValidationResponseDTO returnValidationResponseAnswer() {
		long paymentId = generatePaymentId();
		return new PaymentValidationResponseDTO(url + '/' + paymentId, paymentId, TxStatus.SUCCESS);
	}
	
	private long generatePaymentId() {
//		Random random = new Random(System.nanoTime());
//		int number = random.nextInt(1000000000);
		Generator g = unityOfWork.getIdGeneratorRepository().save(new Generator());
		long number = g.getGeneratedId();
		return number;
	}
	
	public PaymentValidationResponseDTO validateRequest(PaymentValidationRequestDTO request) throws ValidationException, AuthentificationException{
		boolean validRequest = validatePaymentRequest(request);
		if(!validRequest) {
			throw new ValidationException("Request validation exception");
		}
		boolean validAuthentification = validateAcccount(request.getMerchantId(), request.getMerchantPassword());
		if(!validAuthentification) {
			throw new AuthentificationException();
		}
		
		PaymentValidationResponseDTO response = returnValidationResponseAnswer();
		savePaymentRequest(new PaymentRequest(request, response.getPaymentId(), TxStatus.UNKNOWN));
		
		return response;
	}
	
	public void savePaymentRequest(PaymentRequest request) {
		unityOfWork.getPaymentRequestRepository().save(request);
	}
	
//	public PaymentRequest getPaymentRequest(PaymentCardRequestDTO request) throws PaymentRequestException {
//		PaymentRequest req = unityOfWork.getPaymentRequestRepository().findByPaymentIdAndMerchantUsername(request.getPaymentId(), request.getMerchantUsername());
//		if(req == null) {
//			throw new PaymentRequestException();
//		}
//		return req;
//	}
	
	public PaymentRequest getPaymentRequest(PaymentCardRequestDTO request) throws PaymentRequestException {
		PaymentRequest req = unityOfWork.getPaymentRequestRepository().findByPaymentId(request.getPaymentId());
		if(req == null) {
			throw new PaymentRequestException();
		}
		return req;
	}
	
	public Object enoughMoney() {
		return null;
	}
	
	public Object validateCardRequest() {
		return null;
	}
	

	public void checkPaymentRequest() {
//		List<PaymentRequest> nonPaidList = retrieveNonPaidRequest();
//		for (PaymentRequest request: nonPaidList) {
//			request.setRequestStatus(TxStatus.ERROR);
//			unityOfWork.getPaymentRequestRepository().save(request);
//			Tx sellerTx = saveTxLog(TxStatus.SUCCESS, req.getAmount(), "Money added to the seler account", pccResponseBody.getClientName(),pccResponseBody.getClientBankAccount(), sellerAccount.getBankAccountName(), sellerAccount.getBankAccountNumber());
//			logger.info("Money added to the seler account");
//			sendTxToKp(sellerTx);
//		}
	}
	
	public List<PaymentRequest> retrieveNonPaidRequest(){
		long now = System.currentTimeMillis();
		List<PaymentRequest> list = unityOfWork.getPaymentRequestRepository().findByRequestStatus(TxStatus.UNKNOWN).stream().filter(r -> now - r.getCreated().getTime() > TIME_OFFSET_IN_MILLISECONDS).collect(Collectors.toList());

		return list;
	}
	
	public Tx getTx(Long paymentId, Long merchantOrderId) {
		return unityOfWork.getTxRepository().findByMerchantOrderIdAndPaymentId(merchantOrderId, paymentId);
	}
	
	
	
	

}
