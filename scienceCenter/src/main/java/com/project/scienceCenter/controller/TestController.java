package com.project.scienceCenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.scienceCenter.dto.MagazineDTO;
import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.repository.MagazineRepository;
import com.project.scienceCenter.service.TestService;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private MagazineRepository magRepo;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		System.out.println("Uso");
		System.out.println(testService.callPaymentHub());
		return new ResponseEntity<>(new String("Okej NC radi poziv KP kako treba"), HttpStatus.OK);
	}
	
	@RequestMapping(path="/service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testService() {
		System.out.println(testService.callPaymentHubServices());
		return new ResponseEntity<>(new String("Okej NC radi poziv KP kako treba"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/plain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testNC() {
		return new ResponseEntity<>(new String("Okej NC radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MagazineDTO>> getAllNC() {
		
		List<Magazine> lista = magRepo.findAll();
		
		List<MagazineDTO> listaDTO = new ArrayList<MagazineDTO>();
		
		for (Magazine magazine : lista) {
			
			listaDTO.add(new MagazineDTO(magazine));
			
		}
		
		
		return new ResponseEntity<List<MagazineDTO>>(listaDTO, HttpStatus.OK);
	}
	
	
	
}
