package com.models;

public class MyError {
	
	private int errorCode;
	private String errorTitle;
	private String errorMessage;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorTitle() {
		return errorTitle;
	}
	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public MyError(int errorCode, String errorTitle, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorTitle = errorTitle;
		this.errorMessage = errorMessage;
	}

}
