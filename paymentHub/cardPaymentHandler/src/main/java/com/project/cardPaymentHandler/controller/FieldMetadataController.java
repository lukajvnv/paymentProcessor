package com.project.cardPaymentHandler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cardPaymentHandler.dto.FormWrapper;
import com.project.cardPaymentHandler.model.FieldMetadata;
import com.project.cardPaymentHandler.service.FieldMetadataService;

@RestController
@RequestMapping("/field")
public class FieldMetadataController {
	
	@Autowired
	private FieldMetadataService fieldMetadataService;

	@GetMapping(path = "/all")
	public ResponseEntity<List<FieldMetadata>> getAllFieldsMeta(){
		return new ResponseEntity<List<FieldMetadata>>(fieldMetadataService.getFields(), HttpStatus.OK);
	}
	
	
	@PostMapping("/submit")
    public ResponseEntity<?> multiUploadFileModel(@Valid @ModelAttribute FormWrapper model, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
				
        try {
        	fieldMetadataService.addNewSeller(model);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
	

	
//	private boolean sensitiveDataValid(String input) {
//		if(input.contains("[a-zA-Z0-9]+")) {
//			return true;
//		}
//		
//		return true;
//	}
}
