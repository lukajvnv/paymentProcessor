package com.project.customPaymentHandler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.project.customPaymentHandler.dto.FormWrapper;
import com.project.customPaymentHandler.model.FieldMetadata;
import com.project.customPaymentHandler.service.FieldMetadataService;

@RestController
@RequestMapping("/field")
public class FieldMetadataController {
	
	@Autowired
	TemplateEngine htmlTemplateEngine;
	
	@Autowired
	private FieldMetadataService fieldMetadataService;
	

	@GetMapping(path = "/all")
	public ResponseEntity<List<FieldMetadata>> getAllFieldsMeta(){
		return new ResponseEntity<List<FieldMetadata>>(fieldMetadataService.getFields(), HttpStatus.OK);
	}
										//dovoljno pametan da i bez ovoga radi...
	@GetMapping(path= "/getHtml/{sellerIdentifierFk}", produces = MediaType.TEXT_HTML_VALUE)
	 public String getHtml(@PathVariable long sellerIdentifierFk) {
		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

		 ctx.setVariable("sellerFk", sellerIdentifierFk);
		 ctx.setVariable("name", "CUSTOM");


		 // Rendered template in String, You can now return in a JSON property
		 String htmlContent = this.htmlTemplateEngine.process("html/form.html", ctx);

		 return htmlContent;
	 }
	
	    @PostMapping("/submit")
	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FormWrapper model) {
	        try {
	        	fieldMetadataService.addNewSeller(model);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
	
//	@PostMapping(path = "/newClient/{sellerIdentifierFk}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> newClient(@RequestBody List<PaymentTypeFormFieldDto> fields, @PathVariable long sellerIdentifierFk){
//		Map<String, PaymentTypeFormFieldDto> map = fromListToMap(fields);
//
//		fieldMetadataService.addNewSeller(map, sellerIdentifierFk);
//		
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
	
//	private Map<String, PaymentTypeFormFieldDto> fromListToMap(List<PaymentTypeFormFieldDto> list){
//		Map<String, PaymentTypeFormFieldDto> map = new HashMap<String, PaymentTypeFormFieldDto>();
//		
//		for(PaymentTypeFormFieldDto field: list) {
//			map.put(field.getFieldName(), field);
//		}
//		
//		return map;
//	}
}
