package com.project.scienceCenter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.scienceCenter.dto.ArticleDto;
import com.project.scienceCenter.dto.MagazineDTO;
import com.project.scienceCenter.dto.MagazineEditionArticleDto;
import com.project.scienceCenter.dto.MagazineEditionDto;
import com.project.scienceCenter.dto.NewCartItemRequest;
import com.project.scienceCenter.dto.ShoppingCartDto;
import com.project.scienceCenter.dto.UserTxDto;
import com.project.scienceCenter.dto.UserTxItemDto;
import com.project.scienceCenter.model.Article;
import com.project.scienceCenter.model.BuyingType;
import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.model.MagazineEdition;
import com.project.scienceCenter.model.TxStatus;
import com.project.scienceCenter.model.UserA;
import com.project.scienceCenter.model.UserTx;
import com.project.scienceCenter.model.UserTxItem;
import com.project.scienceCenter.repository.ArticleRepository;
import com.project.scienceCenter.repository.MagazineEditionRepository;
import com.project.scienceCenter.repository.MagazineRepository;
import com.project.scienceCenter.repository.UserRepository;
import com.project.scienceCenter.repository.UserTxItemRepository;
import com.project.scienceCenter.repository.UserTxRepository;
import com.project.scienceCenter.service.TestService;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private MagazineRepository magRepo;
	
	@Autowired
	private MagazineEditionRepository magEditionRepo;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTxRepository userTxRepository;
	
	@Autowired
	private UserTxItemRepository userTxItemRepository;
	
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
	
	@RequestMapping(path = "/getOne/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MagazineDTO> getById(@PathVariable Long id) {
		
		Magazine mag = magRepo.getOne(id);
		
		
		return new ResponseEntity<MagazineDTO>(new MagazineDTO(mag), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getOneEdition/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MagazineEditionDto> getEditionById(@PathVariable Long id) {
		
		MagazineEdition edition = magEditionRepo.getOne(id);
		
		
		return new ResponseEntity<MagazineEditionDto>(new MagazineEditionDto(edition.getMagazineEditionId(), edition.getPublishingDate(), edition.getMagazineEditionPrice()), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getEditions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MagazineEditionDto>> getEditions(@PathVariable Long id) {
		
		Magazine mag = magRepo.getOne(id);
		Set<MagazineEdition> editions = mag.getMagazineEditions();
		List<MagazineEditionDto> editionsDto = new ArrayList<MagazineEditionDto>();
		
		for(MagazineEdition mE: editions) {
			editionsDto.add(new MagazineEditionDto(mE.getMagazineEditionId(), mE.getPublishingDate(), mE.getMagazineEditionPrice()));
		}
		
		return new ResponseEntity<List<MagazineEditionDto>>(editionsDto, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getArticles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MagazineEditionArticleDto>> getArticles(@PathVariable Long id) {
		
		Magazine mag = magRepo.getOne(id);
		MagazineEdition edition = magEditionRepo.getOne(id);
		Set<Article> articles = edition.getArticles();
		List<MagazineEditionArticleDto> articlesDto = new ArrayList<MagazineEditionArticleDto>();
		
		for(Article a : articles) {
			articlesDto.add(new MagazineEditionArticleDto(a.getArticleId(), a.getArticleTitle(), a.getArticleAbstract(), a.getPublishingDate(), a.getDoi(), a.getArticlePrice(), ""));
		}
		
		return new ResponseEntity<List<MagazineEditionArticleDto>>(articlesDto, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/addItemToCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MagazineEditionDto> addItemToCart(@RequestBody NewCartItemRequest request) {
		
		UserTx cartOrTx= userTxRepository.getOne(request.getCartId());
		Article article = articleRepo.getOne(request.getArticleId());
		
		cartOrTx.setTotalAmount(cartOrTx.getTotalAmount() + article.getArticlePrice());
		UserTx cartOrTxUpdated = userTxRepository.save(cartOrTx);
		
		UserTxItem newUserTxItem = new UserTxItem(article.getArticlePrice(), cartOrTxUpdated, BuyingType.ARTICLE, article.getArticleId());
		
		
		
		
		userTxItemRepository.save(newUserTxItem);
		
		
		return new ResponseEntity<MagazineEditionDto>(new MagazineEditionDto(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/newCart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCartDto> newCart() {
		
		//privremeno 
		UserA user = userRepository.getOne(1l);
		UserTx newTx = new UserTx(user, new Date(), TxStatus.UNKNOWN, 0f);
		
		UserTx persistedNewTx = userTxRepository.save(newTx);
		
		
		
		return new ResponseEntity<ShoppingCartDto>(new ShoppingCartDto(persistedNewTx.getUserTxId()), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getCart/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTxDto> getCart(@PathVariable Long id) {
		
		 
		UserTx cart = userTxRepository.getOne(id);
		
		Set<UserTxItem> cartItems = cart.getItems();
		
		UserTxDto cartDto = new UserTxDto(cart.getUserTxId(), cart.getCreated(), cart.getStatus(), cart.getTotalAmount());  
		List<UserTxItemDto> cartItemsDto = new ArrayList<UserTxItemDto>();
		
		for(UserTxItem item : cartItems) {
			cartItemsDto.add(new UserTxItemDto(item.getUserTxItemId(), item.getPrice(), cartDto, item.getBuyingType(), item.getItemId()));
		}
		
		cartDto.setItems(cartItemsDto);
		
		return new ResponseEntity<UserTxDto>(cartDto, HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/getUserTxs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserTxDto>> getUserTxs() {
		
		//privremeno 
		UserA user = userRepository.getOne(1l);
		List<UserTx> userTxs = user.getUserTxs().stream().filter(tx -> tx.getStatus() == TxStatus.SUCCESS).collect(Collectors.toList());
		List<UserTxDto> userTxDtos = new ArrayList<UserTxDto>();
		
		for(UserTx tx: userTxs) {
			
			Set<UserTxItem> cartItems = tx.getItems();
			
			UserTxDto cartDto = new UserTxDto(tx.getUserTxId(), tx.getCreated(), tx.getStatus(), tx.getTotalAmount());  
			List<UserTxItemDto> cartItemsDto = new ArrayList<UserTxItemDto>();
			
			for(UserTxItem item : cartItems) {
				Article a = articleRepo.getOne(item.getItemId());
				
				ArticleDto aDto = new ArticleDto(a.getArticleId(), a.getArticleTitle(), a.getArticleAbstract(), a.getPublishingDate(), a.getArticlePrice(), "", a.getDoi());
				UserTxItemDto d = new UserTxItemDto(item.getUserTxItemId(), item.getPrice(), cartDto, item.getBuyingType(), item.getItemId());
				d.setContent(aDto);
				
				cartItemsDto.add(d);
			}
			
			cartDto.setItems(cartItemsDto);
			
			userTxDtos.add(cartDto);

		}
		
				
		return new ResponseEntity<List<UserTxDto>>(userTxDtos, HttpStatus.OK);
	}
}
