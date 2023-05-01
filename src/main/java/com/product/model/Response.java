package com.product.model;

import com.product.exception.ErrorResponse;

public class Response {

	private Product product;
	private ErrorResponse errorResponse;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	@Override
	public String toString() {
		return "Response [product=" + product + ", errorResponse=" + errorResponse + "]";
	}
	
	
	
	
	
	

}
