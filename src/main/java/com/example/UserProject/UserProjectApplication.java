package com.example.UserProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserProjectApplication {

	private static Logger log = LoggerFactory.getLogger(UserProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UserProjectApplication.class, args);
		log.info("Inside UserProject");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
