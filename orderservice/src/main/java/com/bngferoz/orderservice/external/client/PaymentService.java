package com.bngferoz.orderservice.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bngferoz.orderservice.exception.OrderServiceCustomException;
import com.bngferoz.orderservice.external.request.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
	@PostMapping
	ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
	
	default void fallback(Exception e) {
		throw new OrderServiceCustomException("Payment Service is not available", "UNAVAILABLE", 500);
	}
}
