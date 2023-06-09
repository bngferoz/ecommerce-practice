package com.bngferoz.orderservice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.bngferoz.orderservice.external.intercept.RestTemplateInterceptor;

@SpringBootApplication
@EnableFeignClients
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}
	
	
	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate obj = new RestTemplate();
		obj.setInterceptors(Arrays.asList(new RestTemplateInterceptor(
				clientManager(clientRegistrationRepository,oAuth2AuthorizedClientRepository))));
		return obj;
	}
	
	@Bean
	public OAuth2AuthorizedClientManager clientManager(ClientRegistrationRepository clientRegistrationRepository, 
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
		OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider
		= OAuth2AuthorizedClientProviderBuilder
		.builder()
		.clientCredentials()
		.build();
		
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager 
		= new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,oAuth2AuthorizedClientRepository);
		
		
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(oAuth2AuthorizedClientProvider);
		return defaultOAuth2AuthorizedClientManager;
		
	}

}