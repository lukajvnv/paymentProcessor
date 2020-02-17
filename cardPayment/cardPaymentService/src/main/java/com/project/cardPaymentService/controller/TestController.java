package com.project.cardPaymentService.controller;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import com.project.cardPaymentService.FormWrapper;
import com.project.cardPaymentService.Proba;
import com.project.cardPaymentService.dto.PaymentCardRequestDTO;
import com.project.cardPaymentService.dto.PccRequestDTO;
import com.project.cardPaymentService.dto.PccResponseDTO;
import com.project.cardPaymentService.model.Tx;
import com.project.cardPaymentService.service.TransactionService;
import com.project.cardPaymentService.service.ValidationService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TransactionService txService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej cardService get radi"), HttpStatus.OK);
	}
	
	@RequestMapping(path= "/testAsync", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> testAsync() throws InterruptedException {
		txService.testAsync(new Tx());
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@RequestMapping(path= "/testSync", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> testSync() throws InterruptedException {
		txService.testSync(new Tx());

		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@RequestMapping(path= "/locationRedirect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> locationRedirect() {
		
		
		return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", "http://www.ftn.uns.ac.rs/691618389/fakultet-tehnickih-nauka").build();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testPost(@RequestBody Proba proba) {
		
		
		return new ResponseEntity<>(new String("Okej cardService post radi"), HttpStatus.OK);
	}
	
	// *********************************** Testiranje redirekcije i html generisanja **********************
	@RequestMapping(path="/redirect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> testPostR() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "https://www.google.com/");  
		return new ResponseEntity<>(new String("Okej cardService post radi"), headers, HttpStatus.TEMPORARY_REDIRECT);
	}
	
	 @GetMapping("/forwardWithForwardPrefix")
	    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
	       // model.addAttribute("attribute", "forwardWithForwardPrefix"); //atributi url
	        return new ModelAndView("redirect:/test/redirectRf", model);
	        						//forward redirect bez novih zahteva url se ne menja, redirect redirect novi zahtev...
	    }
	
	@RequestMapping(path="/redirectRf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testPostRf() {
		
	  
		//return "forward:/test/redirecttt";
		Map<String, String> params = new HashMap<String, String>();
		params.put("proba", "fajk");
		return ResponseEntity.ok().body(buildHtml("Proba", "https://localhost:8841/test/forwardWithForwardPrefix", params));
	}
	
	@RequestMapping(path="/forwardWithForwardPrefix", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = {"application/x-www-form-urlencoded;charset=UTF-8", "application/json"})
	public ModelAndView redirectWithUsingForwardPrefix(ModelMap model, @RequestBody Proba proba) {
	   return new ModelAndView("redirect:/test/redirectRf", model);
	        						//forward redirect bez novih zahteva url se ne menja, redirect redirect novi zahtev...       
	}
	 
	 public String buildHtml(String title, String actionUrl, Map<String, String> params) {
	        StringBuilder builder = new StringBuilder();
	        builder.append("<HTML>")
	                .append("<HEAD>");
	        if (title != null) {
	            builder.append("<TITLE>SAML HTTP Post Binding</TITLE>");
	        }
	        builder.append("</HEAD>")
	                .append("<BODY Onload=\"document.forms[0].submit()\">")

	                .append("<FORM METHOD=\"POST\" ACTION=\"").append(actionUrl).append("\">");
	        for (Map.Entry<String, String> param : params.entrySet()) {
	            builder.append("<INPUT TYPE=\"HIDDEN\" NAME=\"").append(param.getKey()).append("\"").append(" VALUE=\"").append(param.getValue()).append("\"/>");
	        }


	        builder.append("<NOSCRIPT>")
	                .append("<P>JavaScript is disabled. We strongly recommend to enable it. Click the button below to continue.</P>")
	                .append("<INPUT TYPE=\"SUBMIT\" VALUE=\"CONTINUE\" />")
	                .append("</NOSCRIPT>")

	                .append("</FORM></BODY></HTML>");

	        return builder.toString();
	    }
	 
	 @RequestMapping(path="/redirect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> testPostRPost(@RequestBody Proba proba) {
			
			RestTemplate template = new RestTemplate();
			ResponseEntity<String> r = template.getForEntity("https://localhost:8761", String.class);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "https://www.google.com/");  
			ResponseEntity<Object> e = new ResponseEntity<>(proba, headers, HttpStatus.TEMPORARY_REDIRECT);
			return e;
			//return new ResponseEntity<>(new String("Okej cardService post radi"), headers, HttpStatus.TEMPORARY_REDIRECT).se;
		}
	

	 
	 @RequestMapping(path="/testHelloRedirect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView testHelloRedirect(ModelMap model) {
	       // model.addAttribute("attribute", "forwardWithForwardPrefix");
	   return new ModelAndView("redirect:/test/testHello", model);
	        						//forward redirect bez novih zahteva url se ne menja, redirect redirect novi zahtev...       
	}

//	 @RequestMapping("/testHello")
//	 public ResponseEntity<?> test(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
//
//		 ctx.setVariable("name", name);
//
//		 // Rendered template in String, You can now return in a JSON property
//		 String htmlContent = this.htmlTemplateEngine.process("html/form.html", ctx);
//
//		 return ResponseEntity.ok().body(htmlContent);
//	 }
	 
	 @RequestMapping("/testHello")
	 public ModelAndView test(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

		 ctx.setVariable("name", name);

		 // Rendered template in String, You can now return in a JSON property
		 // String htmlContent = this.htmlTemplateEngine.process("html/form.html", ctx);

		 return new ModelAndView("html/form.html");
	 }
	 
//	 @RequestMapping("/testHelloT")
//	 public String testt(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
//
//		 ctx.setVariable("name", name);
//
//		 // Rendered template in String, You can now return in a JSON property
//		 String htmlContent = this.htmlTemplateEngine.process("html/form.html", ctx);
//
//		 return htmlContent;
//	 }
	 
	 @GetMapping(path= "/testHelloT", produces = MediaType.TEXT_HTML_VALUE)
	 public String testt() {
		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

		 ctx.setVariable("name", "Luka");

		 // Rendered template in String, You can now return in a JSON property
		 String htmlContent = this.htmlTemplateEngine.process("html/test.html", ctx);

		 return htmlContent;
	 }
	 
	 @GetMapping(path= "/testHelloTArr", produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<String> testtArr() {
		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

		 ctx.setVariable("name", "Luka");

		 // Rendered template in String, You can now return in a JSON property
		 String htmlContent = this.htmlTemplateEngine.process("html/test.html", ctx);
		 
		 List<String> ret = new ArrayList<String>();
		 ret.add(htmlContent);
		 ret.add(htmlContent);
		 ret.add(htmlContent);

		 return ret;
	 }
	
	 @RequestMapping(path="/testHelloSubmit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = {"application/x-www-form-urlencoded;charset=UTF-8", "application/json"})
	public ResponseEntity<Proba> testHelloSubmit(ModelMap model, @RequestBody Proba proba) {
		 HttpHeaders headers = new HttpHeaders();
		
		  // headers.add("Location", "https://www.google.com/"); //
//		  headers.add("Access-Control-Allow-Origin", "https://www.google.com/");
//		  headers.add("Location", "https://localhost:8841/test");
//		  headers.add("Access-Control-Allow-Origin", "https://www.google.com/"); 
//		  return new ResponseEntity<>(new String("Okej cardService post radi"), headers,
//		  HttpStatus.TEMPORARY_REDIRECT); 
		 			
//			final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
//			 String htmlContent = this.htmlTemplateEngine.process("html/hello.html", ctx);
//			 return ResponseEntity.ok(htmlContent);
		 ResponseEntity<Proba> res = new ResponseEntity<Proba>(new Proba(666, "fdjl"), HttpStatus.OK);
		 return res;
		 //return ResponseEntity.ok(new Proba(666, "fdjl"));

			 
	}
	 
	 @Autowired
	 TemplateEngine htmlTemplateEngine;
	 
	 @RequestMapping("/testHelloJsp")
	 public ModelAndView testJsp(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		 

		 return new ModelAndView("html/form.html");
	 }
	
	 @RequestMapping(path="/testHelloSubmitJsp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = {"application/x-www-form-urlencoded;charset=UTF-8", "application/json"})
	public ResponseEntity<?> testHelloSubmitJsp(ModelMap model, @RequestBody Proba proba) {
//	       // model.addAttribute("attribute", "forwardWithForwardPrefix");
//		 HttpHeaders headers = new HttpHeaders();
////			headers.add("Location", "https://www.google.com/");
////			headers.add("Access-Control-Allow-Origin", "https://www.google.com/");
//		    headers.add("Location", "https://localhost:8841/test");
//			headers.add("Access-Control-Allow-Origin", "https://www.google.com/");
//			return new ResponseEntity<>(new String("Okej cardService post radi"), headers, HttpStatus.TEMPORARY_REDIRECT);
	        						//forward redirect bez novih zahteva url se ne menja, redirect redirect novi zahtev... 
		 return ResponseEntity.ok("fd");
	}
	
	
	
	// *********************************************************************
	 
	 @Autowired
	 private ValidationService validationService;
	 
	 @GetMapping("/url")
	 public String get() {
		 return "fal";
	 }
	 
	 @GetMapping("/callPcc")
	 public ResponseEntity<String> testPcc(){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity("https://localhost:8951/test", String.class);
		} catch (RestClientException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		 return new ResponseEntity<String>("Radi pcc poziv, odg:" + response.getBody(), HttpStatus.OK);
	 }

	 
	 @PostMapping("/callPcc")
	 public ResponseEntity<String> testPccPost(@RequestBody Proba body){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Proba> response = null;
		try {
			response = restTemplate.postForEntity("https://localhost:8951/test", body, Proba.class);
		} catch (RestClientException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		 return new ResponseEntity<String>("Radi pcc post poziv, odg:" + response.getBody().getTekst(), HttpStatus.OK);
	 }
	 
	 @PostMapping("/callPccToCallOther")
	 public ResponseEntity<String> testPccOtherPost(@RequestBody Proba body){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Proba> response = null;
		try {
			response = restTemplate.postForEntity("https://localhost:8951/test/callOtherBank", body, Proba.class);
		} catch (RestClientException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		 return new ResponseEntity<String>("Radi pcc post poziv, odg:" + response.getBody().getTekst(), HttpStatus.OK);
	 }
	 
	 @PostMapping(path = "/callFromPcc")
		public ResponseEntity<Proba> testCallOtherBankProba(@RequestBody Proba request){
			return new ResponseEntity<Proba>(request, HttpStatus.OK);
		}
	 
	 @PostMapping("/payPcc")
	 public ResponseEntity<String> testPccPost(@RequestBody PccRequestDTO body){
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Proba> response = null;
		try {
			PaymentCardRequestDTO request = new PaymentCardRequestDTO("1234665465464", "4444", "fa", "2021-05-12", 12l);
			PccRequestDTO newRequest = new PccRequestDTO(1234567896l, new Timestamp(System.currentTimeMillis()), request);
			ResponseEntity<PccResponseDTO> r = restTemplate.postForEntity("https://localhost:8951/pay", newRequest, PccResponseDTO.class);
		} catch (RestClientException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		 return new ResponseEntity<String>("Radi pcc post poziv, odg:" + "", HttpStatus.OK);
	 }
	 
	 //*************************************************************************************
	 
	 @PostMapping("/formTest")
	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FormWrapper model) {
	        try {
//	            saveUploadedFile(model.getImage());
//	            formRepo.save(mode.getTitle(),model.getDescription()); //Save as you want as per requirement 
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	    }
	 
	 @GetMapping("/formTest")
	    public ResponseEntity<?> multiUploadFileModel() {
	        try {
//	            saveUploadedFile(model.getImage());
//	            formRepo.save(mode.getTitle(),model.getDescription()); //Save as you want as per requirement 
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	    }
	 
	 @GetMapping("/multiValue")
	 public ResponseEntity<String> multiValue(
	   @RequestHeader MultiValueMap<String, String> headers) {
	     headers.forEach((key, value) -> {
	         System.out.println(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
	           
	     });
	          
	     return new ResponseEntity<String>(
	       String.format("Listed %d headers", headers.size()), HttpStatus.OK);
	 }
	 
	 @GetMapping("/getBaseUrl")
	 public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
	     InetSocketAddress host = headers.getHost();
	     String url = "http://" + host.getHostName() + ":" + host.getPort();
	     return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
	 }
	
}
