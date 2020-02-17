package com.project.pcc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.pcc.dto.PccRequestDTO;
import com.project.pcc.dto.PccResponseDTO;
import com.project.pcc.model.BankInfo;
import com.project.pcc.model.TxStatus;
import com.project.pcc.repository.BankInfoRepository;

@Service
public class PccPayService {
	
	@Autowired
	private BankInfoRepository bankInfoRepository;
	
	private Logger logger = LoggerFactory.getLogger(PccPayService.class);

	
	public PccResponseDTO handleRequest(PccRequestDTO request, PccResponseDTO response) {
		try {
			if(!validatePccRequest(request)) {
				logger.error("invalid pcc request");
				throw new Exception();
			}
			logger.info("validation of pcc request ended successfully");

			
			BankInfo bankInfo = findBank(request);
			if(bankInfo == null) {
				logger.error("cannot find issuer bank");
				throw new Exception();
			}
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PccResponseDTO> pccResponse = null;
			
			logger.info("invokation of issuer bank [IIB] {} with bankPan: {} has started", bankInfo.getBankName(), bankInfo.getBankPanNumber());
			logger.info("[IIB] with request acquiererOrderId {}, timestamp {} amount {}", request.getAcquirerOrderId(), request.getAcquirerTimestamp(), request.getAmount());

			
			pccResponse = restTemplate.postForEntity(bankInfo.getBankUrl(), request, PccResponseDTO.class);
			response = pccResponse.getBody();

			logger.info("invokation of issuer bank has finished");
			logger.info("Tx [IIB] bank with request acquiererOrderId {}, timestamp {} amount {}, OUTCOME: {}", request.getAcquirerOrderId(), request.getAcquirerTimestamp(), request.getAmount(), TxStatus.SUCCESS);


			return response;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			logger.error("error occured while invoking issuer's bank");
			logger.error("Tx [IIB] with request acquiererOrderId {}, timestamp {} amount {}, OUTCOME: {}", request.getAcquirerOrderId(), request.getAcquirerTimestamp(), request.getAmount(), TxStatus.ERROR);
			response.setStatus(TxStatus.ERROR);
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("unexpected error occured");
			logger.error("Tx [IIB] with request acquiererOrderId {}, timestamp {} amount {}, OUTCOME: {}", request.getAcquirerOrderId(), request.getAcquirerTimestamp(), request.getAmount(), TxStatus.ERROR);
			response.setStatus(TxStatus.ERROR);
			return response;
		}
	}
	
	private boolean validatePccRequest(PccRequestDTO request) {
		logger.info("validation of pcc request has started");

		return true;
	}
	
	private BankInfo findBank(PccRequestDTO request) {
		logger.info("The operation of finding bank has started");
		
		String issuerBankPan = request.getPaymentCardRequest().getPan().substring(1, 7);
		
		BankInfo bankInfo = bankInfoRepository.findByBankPanNumber(issuerBankPan);
		return bankInfo;
	}

}
