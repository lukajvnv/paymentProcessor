package com.project.paymentRequestHandler.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.paymentRequestHandler.dto.NewClientDto;
import com.project.paymentRequestHandler.dto.NewMagazineConfirmationDto;
import com.project.paymentRequestHandler.dto.OrderIdDTO;
import com.project.paymentRequestHandler.dto.PaymentOrderDataDto;
import com.project.paymentRequestHandler.dto.PaymentTypeDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeFormDto;
import com.project.paymentRequestHandler.dto.PaymentTypeRequestDTO;
import com.project.paymentRequestHandler.dto.PaymentTypeResponseDTO;
import com.project.paymentRequestHandler.dto.SellerInfoDto;
import com.project.paymentRequestHandler.dto.ShoppingCartDTO;
import com.project.paymentRequestHandler.dto.TxInfoDto;
import com.project.paymentRequestHandler.model.NewClientRequest;
import com.project.paymentRequestHandler.model.PaymentType;
import com.project.paymentRequestHandler.model.SellerInfo;
import com.project.paymentRequestHandler.model.ShoppingCart;
import com.project.paymentRequestHandler.model.TxInfo;
import com.project.paymentRequestHandler.service.ClientService;
import com.project.paymentRequestHandler.service.PaymentTypeService;
import com.project.paymentRequestHandler.service.RequestService;

@RestController
@RequestMapping("/request")
@CrossOrigin
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
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
	
//	@RequestMapping(path = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<OrderIdDTO> savePost(@RequestBody ShoppingCart request) {		
//		Long orderId = requestService.saveShoppingCartTemp(request);
//		
//		OrderIdDTO dto = new OrderIdDTO(orderId);
//		
//		dto.setKpUrl("https://localhost:4666/pay/" + orderId);
//		
//		//vracamo orderId - redirekt url na kp
//		return new ResponseEntity<OrderIdDTO>(dto, HttpStatus.OK);
//	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderIdDTO> savePost(@RequestBody ShoppingCartDTO request, @RequestHeader MultiValueMap<String, String> headers) {	
		//prosao authentifikaciju
		String hostSc = headers.get("hostsc").get(0);
		
		ShoppingCart s = new ShoppingCart(request.getTotalAmount().doubleValue(), request.getkPClientIdentifier());
		
		Long orderId = requestService.saveShoppingCartTemp(s);
		
		OrderIdDTO dto = new OrderIdDTO(orderId);
		
		dto.setKpUrl("https://localhost:4666/pay/" + orderId);
		
		//cuvanje inicijalnog tx-a
		TxInfo txInfo = new TxInfo(orderId, -1l, hostSc);
		requestService.saveNewTxInfo(txInfo);
		
		//vracamo orderId i redirekt url na kp
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
	
	@RequestMapping(path = "/getPaymentAndOrderData/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentOrderDataDto> cardHandlerPost(@PathVariable long id) {
		System.out.println("Usao, nece debug da radi nesto");
		
		if(id == 0) {
			return new ResponseEntity<PaymentOrderDataDto>(HttpStatus.OK);
		}
		
		ShoppingCart sc = requestService.getPrice(id);
		
		PaymentTypeResponseDTO types = requestService.getSupportedPaymentTypes(sc.getSellerId());
		
		PaymentOrderDataDto response = new PaymentOrderDataDto(sc, types);
		
		return new ResponseEntity<PaymentOrderDataDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/updateTxAfterPaymentInit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TxInfoDto> updateTxInitial(@RequestBody TxInfoDto request) {		
		
		TxInfo txInfo = requestService.getTxInfoByOrderId(request.getOrderId());
		txInfo.setServiceWhoHandlePayment(request.getServiceWhoHandlePayment());
		txInfo.setPaymentId(request.getPaymentId());
		
		requestService.saveNewTxInfo(txInfo);
		
		
		return new ResponseEntity<TxInfoDto>(request, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/updateTxAfterPaymentIsFinished", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TxInfoDto> updateTxInTheEnd(@RequestBody TxInfoDto request) {		
		
		TxInfo txInfo = requestService.getTxInfoByPaymentIdAndServiceWhoHandle(request.getPaymentId(), request.getServiceWhoHandlePayment());
		request.setOrderId(txInfo.getOrderId());
			
		//callback to Nc
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://HOST/pay/updateTxAfterPaymentIsFinished";
		//String host = "localhost:8836";
		String host = txInfo.getClientApplication();
		
		System.out.println("ordId: " + request.getOrderId());
		System.out.println("paymentId: " + request.getPaymentId());
		System.out.println("paymentId: " + request.getStatus());

		//privremeno zeza callback...
		ResponseEntity<?> response = restTemplate.postForEntity(url.replace("HOST", host), request, TxInfoDto.class);
		
		
		return new ResponseEntity<TxInfoDto>(request, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/checkTx", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TxInfoDto> checkTx(@RequestBody TxInfoDto request) {		
		
		TxInfo txInfo = requestService.getTxInfoByOrderId(request.getOrderId());
		String service = txInfo.getServiceWhoHandlePayment();
		// txInfo.getPaymentId();
		
		RestTemplate restTemplate = new RestTemplate();
				
		try {
			ResponseEntity<TxInfoDto> response = restTemplate.postForEntity(service + "/checkTx", txInfo, TxInfoDto.class);
			return new ResponseEntity<TxInfoDto>(response.getBody(), HttpStatus.OK);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return new ResponseEntity<TxInfoDto>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(path = "/newPaymentType")
	public ResponseEntity<?> newPaymentType(@Valid @RequestBody PaymentTypeDTO request, BindingResult result){
		
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		PaymentType newPaymentType = new PaymentType(request.getPaymentTypeName(), request.getPaymentTypeHandlerName(), request.getPaymentTypeHandlerUrl(), request.getPaymentTypeHandlerUrlRoot());
		
		paymentTypeService.saveNewPaymentType(newPaymentType);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@GetMapping(path = "/test")
//	public ResponseEntity<Map<Long, PaymentTypeFormDto>> test() {
//		Map<Long, PaymentTypeFormDto> forms = clientService.prepareFields();
//		return new ResponseEntity<Map<Long,PaymentTypeFormDto>>(forms, HttpStatus.OK);
//	}

}
