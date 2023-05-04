package com.bngferoz.orderservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OrderServiceCustomException extends RuntimeException{

	private String errorCode;
	private int status;
	
	public OrderServiceCustomException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
}
