package com.product.exception;

public class ProductNotFound extends RuntimeException{

	private String errorCode;
	private String errorMsg;
	
	public ProductNotFound(String errorCode, String errorMsg) {
	
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
	
	
	
	
}
