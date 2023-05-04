package com.bngferoz.productService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceCustomException extends RuntimeException{
	
	private String errorCode;
	
	public ProductServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
