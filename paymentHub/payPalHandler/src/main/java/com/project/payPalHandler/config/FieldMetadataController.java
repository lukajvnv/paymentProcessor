package com.project.payPalHandler.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.payPalHandler.dto.FormWrapper;
import com.project.payPalHandler.model.FieldMetadata;
import com.project.payPalHandler.service.FieldMetadataService;

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
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FormWrapper model) {
        try {
        	fieldMetadataService.addNewSeller(model);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
