package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.model.ErrorResponse;

@ControllerAdvice
public class ProductAdvice {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handlAllException(Exception exception) {
		return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ProductNotFound.class)
	public ResponseEntity<ErrorResponse> handlProductNotFound(ProductNotFound ex) {
		ErrorResponse err = new ErrorResponse(ex.getErrorCode(),ex.getErrorMsg());
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
