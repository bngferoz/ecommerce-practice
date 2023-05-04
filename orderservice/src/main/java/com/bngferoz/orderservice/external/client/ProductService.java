package com.bngferoz.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bngferoz.orderservice.exception.OrderServiceCustomException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {
	
	@PutMapping("/{id}")
    ResponseEntity<String> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);
	
	default void fallback(Exception e) {
		throw new OrderServiceCustomException("Product Service is not available", "UNAVAILABLE", 500);
	}
}
