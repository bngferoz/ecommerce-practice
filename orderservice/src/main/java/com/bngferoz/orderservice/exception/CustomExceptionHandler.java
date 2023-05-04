package com.bngferoz.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bngferoz.orderservice.external.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(OrderServiceCustomException.class)
	public ResponseEntity<ErrorResponse> customHandler(OrderServiceCustomException exception){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse().builder()
				.errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode())
				.build(),HttpStatus.valueOf(exception.getStatus()));
	}
}
