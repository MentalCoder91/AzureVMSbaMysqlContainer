package com.product.exception;

public class ErrorResponse extends RuntimeException{

	private String errorCode;
	private String errorMsg;
	
	public ErrorResponse(String errorCode, String errorMsg) {
	
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
