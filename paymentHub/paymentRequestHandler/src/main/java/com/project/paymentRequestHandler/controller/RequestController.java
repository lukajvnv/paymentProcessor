package com.project.paymentRequestHandler.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.paymentRequestHandler.dto.NewClientDto;
import com.project.paymentRequestHandler.dto.NewMagazineConfirmationDto;
import com.project.paymentRequestHandler.dto.OrderIdDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeFormDto;
import com.project.paymentRequestHandler.dto.PaymentTypeRequestDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;
import com.project.paymentRequestHandler.dto.ShoppingCartDTO;
import com.project.paymentRequestHandler.model.ShoppingCart;
import com.project.paymentRequestHandler.dto.SellerInfoDto;
import com.project.paymentRequestHandler.model.NewClientRequest;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.service.ClientService;
import com.project.paymentRequestHandler.service.RequestService;

@RestController
@RequestMapping("/request")
@CrossOrigin
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(path = "/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cardHandler() {
		
		
		return new ResponseEntity<>(new String("Okej paymentRequest poziv card handler radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/paymentTypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentTypeResponseDTO> cardHandlerPost(@RequestBody PaymentTypeRequestDTO request) {
		System.out.println("Usao, nece debug da radi nesto");
		PaymentTypeResponseDTO response = requestService.getSupportedPaymentTypes(request);
		
		return new ResponseEntity<PaymentTypeResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderIdDTO> savePost(@RequestBody ShoppingCart request) {
		System.out.println("Usao u save");
		
		Long orderId = requestService.saveShoppingCartTemp(request);
		
		OrderIdDTO dto = new OrderIdDTO(orderId);
		
		
		//vracamo orderId
		return new ResponseEntity<OrderIdDTO>(dto, HttpStatus.OK);
	}
	/*
	 * {id} --> orderId
	 * */
	@RequestMapping(path = "/getPrice/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShoppingCart> getPrice(@PathVariable Long id) {
		System.out.println("Usao u getPrice");
		
		ShoppingCart sc = requestService.getPrice(id);
		
		return new ResponseEntity<ShoppingCart>(sc, HttpStatus.OK);
	}
	
	@GetMapping(path = "/newClient/{externalIdentifier}")
	public ResponseEntity<String> initRegister(@PathVariable("externalIdentifier") long externalIdentifier){
		NewClientRequest newClientRequest = new NewClientRequest(externalIdentifier, "");
		NewClientRequest persistedNewClientRequest = requestService.saveNewRequest(newClientRequest);
		
		String responseRedirectUrl = "https://localhost:4666/new-client/" + persistedNewClientRequest.getNewClientId(); 
		
		return new ResponseEntity<String>(responseRedirectUrl, HttpStatus.OK);
	}
	
	@PostMapping(path = "/newClient")
	public ResponseEntity<?> newClientRegistration(@RequestBody SellerInfoDto sellerInfoDto){
		
		NewClientRequest request = requestService.getNewClientRequest(sellerInfoDto.getNewClientRequestId());
		
		if(request == null) {
			
		}
		
		SellerInfo persistedSellerInfo = clientService.saveSellerInfo(new SellerInfo(request.getSellerIdentifier(), sellerInfoDto.getSellerName(), sellerInfoDto.getSellerPib()));
		
		NewMagazineConfirmationDto newMagazineConfirmationDtoRequest = new NewMagazineConfirmationDto(request.getSellerIdentifier(),  persistedSellerInfo.getSellerIdentifier());
		//callback to Nc
		RestTemplate restTemplate = new RestTemplate();
		//ResponseEntity<?> response = restTemplate.postForEntity("https://localhost:8836/pay/registerConfirmationBasic", newMagazineConfirmationDtoRequest, Object.class);
		
		//offer payment options
		Map<Long, PaymentTypeFormDto> forms = clientService.prepareFields();
		NewClientDto newClientRequest = new NewClientDto(persistedSellerInfo.getSellerDBId(), forms);

		
		return new ResponseEntity<>(newClientRequest, HttpStatus.OK);
	}
	
	@PostMapping(path = "/newClientPaymentData")
	public ResponseEntity<?> newClientRegistration(@RequestBody NewClientDto request){
		
		clientService.submitPaymentData(request.getForms(), request.getNewClientId());
		

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/test")
	public ResponseEntity<Map<Long, PaymentTypeFormDto>> test() {
		Map<Long, PaymentTypeFormDto> forms = clientService.prepareFields();
		return new ResponseEntity<Map<Long,PaymentTypeFormDto>>(forms, HttpStatus.OK);
	}

}
