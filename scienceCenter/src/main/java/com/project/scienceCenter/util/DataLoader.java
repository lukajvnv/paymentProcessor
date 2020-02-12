package com.project.scienceCenter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.scienceCenter.model.Article;
import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.model.MagazineEdition;
import com.project.scienceCenter.model.UserA;
import com.project.scienceCenter.model.WayOfPayment;
import com.project.scienceCenter.repository.ArticleRepository;
import com.project.scienceCenter.repository.MagazineEditionRepository;
import com.project.scienceCenter.repository.MagazineRepository;
import com.project.scienceCenter.repository.UserRepository;



@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	MagazineRepository magRepo;
	
	@Autowired
	MagazineEditionRepository magEditionRepo;
	
	@Autowired
	ArticleRepository articleResository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		createMagazines();
		
		createArticles();
		
		createUser();
	}
	
	
	
	private void createMagazines() throws IOException {

		Magazine m1 = new Magazine(1l, "S1ksjdf3343", "Bravo" , WayOfPayment.OPEN_ACCESS, true, 1l,20.0);
		Magazine m2 = new Magazine(2l, "ghjghdf3343", "Zabavnik",WayOfPayment.PAID_ACCESS, true, 2l,30.0);
		Magazine m3 = new Magazine(3l, "797jdf33sad", "Blic zena", WayOfPayment.OPEN_ACCESS, true, 1l,40.0);
		
		
		Magazine peristedM1 = magRepo.save(m1);
		Magazine peristedM2 = magRepo.save(m2);
		magRepo.save(m3);
		
		
		MagazineEdition magazineEdition1 = new MagazineEdition(new Date(), 100f, peristedM1);
		MagazineEdition magazineEdition2 = new MagazineEdition(new Date(), 200f, peristedM1);

		
		magEditionRepo.save(magazineEdition1);
		magEditionRepo.save(magazineEdition2);

		
		MagazineEdition magazineEdition3 = new MagazineEdition(new Date(), 200f, peristedM2);
		magEditionRepo.save(magazineEdition3);



		
		
	}
	
	private void createArticles() throws IOException {
		MagazineEdition magEdition1 = magEditionRepo.getOne(1l);
		MagazineEdition magEdition2 = magEditionRepo.getOne(2l);
		MagazineEdition magEdition3 = magEditionRepo.getOne(3l);

		
		byte[] content1 = loadAndSaveFileInBytes("src/main/resources/files/o_kt1.txt");
		 byte[] content2 = loadAndSaveFileInBytes("src/main/resources/files/o_kt2.txt");
		 byte[] content3 = loadAndSaveFileInBytes("src/main/resources/files/o_kt3.txt");

		
		Article article1 = new Article("Article 1", "This article 1 is about ...", new Date(), content1, ".txt", "1232-155", magEdition1, 20f);
		Article article2 = new Article("Article 2", "This article 2 is about ...", new Date(), content2, ".txt", "1fd32-155", magEdition1, 40f);
		Article article3 = new Article("Article 3", "This article 3 is about ...", new Date(), content3, ".txt", "1232-222", magEdition1, 30f);
		
		Article article4 = new Article("Article 4", "This article 4 is about ...", new Date(), content1, ".txt", "3332-155", magEdition2, 20f);
		Article article5 = new Article("Article 5", "This article 5 is about ...", new Date(), content2, ".txt", "455-155", magEdition2, 30f);
		Article article6 = new Article("Article 6", "This article 6 is about ...", new Date(), content3, ".txt", "1232-666", magEdition3, 40f);
	
		articleResository.save(article1);
		articleResository.save(article2);
		articleResository.save(article3);
		articleResository.save(article4);
		articleResository.save(article5);
		articleResository.save(article6);

		//String[] fileParts = newArticleDto.getFile().split(",");
//		byte[] decodedByte = Base64Utility.decode(fileParts[1]);
//		String fileFormat = fileParts[0];
		
		 


	}
	
	private void createUser() {
		UserA user1 = new UserA("Ime", "Prezime", "Novi Sad", "Serbia", "email@gmail.com", true);
		
		userRepository.save(user1);
	}
	
	private byte[] loadAndSaveFileInBytes(String path) throws IOException {
		File file = new File(path);
		 //init array with file length
		 byte[] bytesArray = new byte[(int) file.length()]; 

		  FileInputStream fis = new FileInputStream(file);
		  fis.read(bytesArray); //read file into bytes[]
		  fis.close();
		  
		  return bytesArray;
	}
	
	
	
	
}
