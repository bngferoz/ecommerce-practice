package com.bngferoz.orderservice.external.intercept;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor{
	

	private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

	@Autowired
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager clientManager) {
		this.oAuth2AuthorizedClientManager = clientManager;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().add("Authorization", "Bearer "
				+oAuth2AuthorizedClientManager
				.authorize(OAuth2AuthorizeRequest
						.withClientRegistrationId("internal-client")
						.principal("internal")
						.build())
				.getAccessToken().getTokenValue());
		return execution.execute(request, body);
	}

}
