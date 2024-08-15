package com.nk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

	@Bean(name="template")
	public RestTemplate createTemplate() {
		System.out.println("RestTemplate bean created");
		return new RestTemplate();
	}
	
}
