package com.product.model;

public class ErrorResponse {
	
	private String errCode;
	private String errMsg;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public ErrorResponse(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "ErrorResponse [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}
	
	
	
	
	
	
	

}
