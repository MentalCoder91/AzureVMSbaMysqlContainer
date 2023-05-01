package com.product.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	
}
