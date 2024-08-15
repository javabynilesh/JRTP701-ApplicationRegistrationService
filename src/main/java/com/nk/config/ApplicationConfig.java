package com.nk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

	@Bean(name="template")
	public RestTemplate createTemplate() {
		System.out.println("RestTemplate bean created");
		return new RestTemplate();
	}
	
	@Bean(name="webclient")
	public WebClient createWebClient() {
		System.out.println("WebClient bean created:"+WebClient.create());
		return WebClient.create();
	}
}
