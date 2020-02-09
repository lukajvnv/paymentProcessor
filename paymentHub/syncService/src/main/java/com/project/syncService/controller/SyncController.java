package com.project.syncService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.syncService.dto.AuthorizeRequest;
import com.project.syncService.model.WebshopClient;
import com.project.syncService.service.SyncService;

@RestController
public class SyncController {
	
	@Autowired
	private SyncService syncService;

	@PostMapping
	private ResponseEntity<?> verify(@Valid @RequestBody AuthorizeRequest request, BindingResult result) {
		
		try {
			if (result.hasErrors()) {
				throw new Exception();
			}
			if (syncService.webShopClientExist(request.getHost())) {
				return new ResponseEntity<>(request, HttpStatus.OK);
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/newWebshopClient")
	private ResponseEntity<?> newWebshopClient(@Valid @RequestBody AuthorizeRequest request, BindingResult result) {
		
		try {
			if (result.hasErrors()) {
				throw new Exception();
			}
			syncService.save(new WebshopClient(request.getHost()));
			return new ResponseEntity<>(request, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}		
	}
}
