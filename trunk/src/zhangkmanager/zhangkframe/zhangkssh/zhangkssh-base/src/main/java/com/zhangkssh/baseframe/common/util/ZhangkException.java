package com.zhangkssh.baseframe.common.util;


public class ZhangkException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ZhangkException(int errorCode, String errorText) {
		super();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	private int errorCode;

	private String errorText;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
