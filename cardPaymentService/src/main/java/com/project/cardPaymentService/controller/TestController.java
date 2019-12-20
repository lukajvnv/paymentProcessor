package com.project.cardPaymentService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import com.project.cardPaymentService.Proba;
import com.project.cardPaymentService.service.ValidationService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> test() {
		
		
		return new ResponseEntity<>(new String("Okej cardService get radi"), HttpStatus.OK);
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
	 
	 @RequestMapping("/testHelloT")
	 public String testt(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		 final org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

		 ctx.setVariable("name", name);

		 // Rendered template in String, You can now return in a JSON property
		 String htmlContent = this.htmlTemplateEngine.process("html/form.html", ctx);

		 return htmlContent;
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
	
}
