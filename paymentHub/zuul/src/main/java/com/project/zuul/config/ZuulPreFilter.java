package com.project.zuul.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.project.zuul.dto.AuthorizeRequest;

public class ZuulPreFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(ZuulFilter.class);

	private static final String SYNC_URL = "https://localhost:8769";
	
	@Value("${server.port}")
	private String port;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("zuul app at port {}", port);
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		String external = request.getHeader("external");
		String hostSc = request.getHeader("hostsc");
		
		if(external == null || hostSc == null) {
			logger.error("System cannot recognize client");
			throw new ZuulException("System cannot recognize client", HttpStatus.UNAUTHORIZED.value(), "Required params are missing");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		AuthorizeRequest authRequest = new AuthorizeRequest(hostSc);
		
		try {
			ResponseEntity<AuthorizeRequest> response = restTemplate.postForEntity(SYNC_URL, authRequest, AuthorizeRequest.class);
			System.out.println(response.getBody().getHost());
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("System cannot recognize client");
			throw new ZuulException("System cannot recognize client", HttpStatus.UNAUTHORIZED.value(), "Webshop client does not exist");
		}

	
		System.out.println("usao");
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
