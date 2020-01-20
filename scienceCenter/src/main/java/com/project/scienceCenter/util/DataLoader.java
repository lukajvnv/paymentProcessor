package com.project.scienceCenter.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.model.WayOfPayment;
import com.project.scienceCenter.repository.MagazineRepository;



@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	MagazineRepository magRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		createMagazines();
		
		
	}
	
	
	
	private void createMagazines() throws IOException {

		Magazine m1 = new Magazine(1l, "S1ksjdf3343", "Bravo" , WayOfPayment.OPEN_ACCESS, true, 1l,20.0);
		Magazine m2 = new Magazine(2l, "ghjghdf3343", "Zabavnik",WayOfPayment.PAID_ACCESS, true, 1l,30.0);
		Magazine m3 = new Magazine(3l, "797jdf33sad", "Blic zena", WayOfPayment.OPEN_ACCESS, true, 1l,40.0);
		
		
		magRepo.save(m1);
		magRepo.save(m2);
		magRepo.save(m3);
		
		
	}
	
	
}
