package com.project.paymentRequestHandler.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.paymentRequestHandler.dto.NewClientDto;
import com.project.paymentRequestHandler.dto.NewMagazineConfirmationDto;
import com.project.paymentRequestHandler.dto.PaymentTypeDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeFormDto;
import com.project.paymentRequestHandler.dto.SellerInfoDto;
import com.project.paymentRequestHandler.model.NewClientRequest;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.service.ClientService;
import com.project.paymentRequestHandler.service.RequestService;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class NewKpClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping(path = "/newClient/{externalIdentifier}")
	public ResponseEntity<String> initRegister(@PathVariable("externalIdentifier") long externalIdentifier, @RequestHeader MultiValueMap<String, String> headers){
		//prosao authentifikaciju
		String hostSc = headers.get("hostsc").get(0);
		
		
		NewClientRequest newClientRequest = new NewClientRequest(externalIdentifier, hostSc);
		NewClientRequest persistedNewClientRequest = requestService.saveNewRequest(newClientRequest);
		
		String responseRedirectUrl = "https://localhost:4666/new-client/" + persistedNewClientRequest.getNewClientId(); 
		
		return new ResponseEntity<String>(responseRedirectUrl, HttpStatus.OK);
	}
	
//	@PostMapping(path = "/newClient")
//	public ResponseEntity<?> newClientRegistration(@RequestBody SellerInfoDto sellerInfoDto){
//		
//		NewClientRequest request = requestService.getNewClientRequest(sellerInfoDto.getNewClientRequestId());
//		
//		if(request == null) {
//			
//		}
//		
//		SellerInfo persistedSellerInfo = clientService.saveSellerInfo(new SellerInfo(request.getSellerIdentifier(), sellerInfoDto.getSellerName(), sellerInfoDto.getSellerPib()));
//		
//		NewMagazineConfirmationDto newMagazineConfirmationDtoRequest = new NewMagazineConfirmationDto(request.getSellerIdentifier(),  persistedSellerInfo.getSellerIdentifier());
//		//callback to Nc
//		RestTemplate restTemplate = new RestTemplate();
//		
//		String url = "https://HOST/pay/registerConfirmationBasic";
//		//String host = "localhost:8836";
//		String host = request.getClientApplication();
//		
//		ResponseEntity<?> response = restTemplate.postForEntity(url.replace("HOST", host), newMagazineConfirmationDtoRequest, Object.class);
//		
//		//offer payment options
//		Map<Long, PaymentTypeFormDto> forms = clientService.prepareFields();
//		NewClientDto newClientRequest = new NewClientDto(persistedSellerInfo.getSellerDBId(), forms);
//
//		
//		return new ResponseEntity<>(newClientRequest, HttpStatus.OK);
//	}
//	
//	@PostMapping(path = "/newClientPaymentData")
//	public ResponseEntity<?> newClientRegistration(@RequestBody NewClientDto request){
//		
//		clientService.submitPaymentData(request.getForms(), request.getNewClientId());
//		
//
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	@PostMapping(path = "/newClient")
	public ResponseEntity<?> newClientRegistration(@RequestBody SellerInfoDto sellerInfoDto){
		
		NewClientRequest request = requestService.getNewClientRequest(sellerInfoDto.getNewClientRequestId());
		
		if(request == null) {
			
		}
		
		SellerInfo persistedSellerInfo = clientService.saveSellerInfo(new SellerInfo(request.getSellerIdentifier(), sellerInfoDto.getSellerName(), sellerInfoDto.getSellerPib()));
		
		NewMagazineConfirmationDto newMagazineConfirmationDtoRequest = new NewMagazineConfirmationDto(request.getSellerIdentifier(),  persistedSellerInfo.getSellerIdentifier());
		//callback to Nc
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://HOST/pay/registerConfirmationBasic";
		//String host = "localhost:8836";
		String host = request.getClientApplication();
		
		ResponseEntity<?> response = restTemplate.postForEntity(url.replace("HOST", host), newMagazineConfirmationDtoRequest, Object.class);
		
		//offer payment options
		List<PaymentTypeDTO> types = clientService.prepareTypes();
		NewClientDto newClientRequest = new NewClientDto(persistedSellerInfo.getSellerDBId(), types);

		
		return new ResponseEntity<>(newClientRequest, HttpStatus.OK);
	}
	
	@PostMapping(path = "/newClientPaymentTypesSubmit")
	public ResponseEntity<?> newClientPaymentTypesSubmit(@RequestBody NewClientDto request){
		
		clientService.submitNewClientPaymentTypes(request.getPaymentTypes(), request.getNewClientId());
		
//		List<String> htmlContent = clientService.prepareFields(request.getPaymentTypes(), request.getNewClientId());
		
		Map<Long, PaymentTypeFormDto> forms = clientService.prepareFields(request.getPaymentTypes(), request.getNewClientId());
		
		NewClientDto newClientRequest = new NewClientDto(request.getNewClientId(), forms);
		newClientRequest.setPaymentTypes(request.getPaymentTypes());

		return new ResponseEntity<>(newClientRequest, HttpStatus.OK);
	}
	

}
