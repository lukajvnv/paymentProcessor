package com.project.payPalService.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.project.payPalService.model.Client;
import com.project.payPalService.repository.IClientRepository;

public class DataLoader implements ApplicationRunner {

	@Autowired
	private IClientRepository clientRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		seedClient();
	}

	public void seedClient() {
		Client client = new Client(1L,
				"AcI4uBHVrHz3OFAt5OTzoBcz68-gU6rR4x0UM6GfgFLEufIwsfYBvjeEMDL2tz6eT0BiXSiO--BTcf_Z",
				"EOR-cyCwpmFHd2Ut53JKCGFE6s7eehXdja-fEQuH1qsbtqBI5YdSsJyQHResoghimdINQbKOlzXFI3JZ");
		clientRepository.save(client);
	}
	
}
