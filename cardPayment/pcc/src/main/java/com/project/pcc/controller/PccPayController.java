package com.project.pcc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pcc.dto.PccRequestDTO;
import com.project.pcc.dto.PccResponseDTO;
import com.project.pcc.dto.Proba;
import com.project.pcc.service.PccPayService;

@RestController
@RequestMapping("/pay")
public class PccPayController {
	
	@Autowired
	private PccPayService pccPayService;
	
	private Logger logger = LoggerFactory.getLogger(PccPayController.class);

	@PostMapping
	public ResponseEntity<PccResponseDTO> pay(@RequestBody PccRequestDTO request){
		PccResponseDTO response = new PccResponseDTO();
		response.setAcquirerOrderId(request.getAcquirerOrderId());
		response.setAcquirerTimestamp(request.getAcquirerTimestamp());
		
		logger.info("pay started");
		
		response = pccPayService.handleRequest(request, response);
		
		logger.info("pay finished sucessfully");

		
		return new ResponseEntity<PccResponseDTO>(response, HttpStatus.OK);
	}
}
