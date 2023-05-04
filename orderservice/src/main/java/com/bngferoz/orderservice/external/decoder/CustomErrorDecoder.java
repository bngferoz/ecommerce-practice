package com.bngferoz.orderservice.external.decoder;

import java.io.IOException;

import com.bngferoz.orderservice.exception.OrderServiceCustomException;
import com.bngferoz.orderservice.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("::{}", response.request().url());
		log.info("::{}", response.request().headers());
		
		try {
			ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new OrderServiceCustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());
		} catch (IOException e) {
			log.info(e.getLocalizedMessage());
			throw new OrderServiceCustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500);
		}
	}

}
