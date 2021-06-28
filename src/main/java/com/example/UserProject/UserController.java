package com.example.UserProject;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
public class UserController {

	private RestTemplate restTemplate;
	@Value("${user.external.serviceUrl}")
	private String externalServiceUrl;
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@GetMapping(value = "/user")
	public UserResponse getUser() throws RestClientException, IOException{
		log.info("Fetching User...");
		UserResponse user = new UserResponse();
		user.setId(1);
		user.setName("abc");
		user.setAddress(getAddressByExternalService());
		log.info("Fetching User complete");
		return user;
	}
	
	private String getAddressByExternalService() throws RestClientException, IOException {
		log.info("User info microservice url:" + externalServiceUrl);
		ResponseEntity<String> response = restTemplate.exchange(externalServiceUrl, HttpMethod.GET, getHeaders(),String.class);
		return response.getBody();
	}
	
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
