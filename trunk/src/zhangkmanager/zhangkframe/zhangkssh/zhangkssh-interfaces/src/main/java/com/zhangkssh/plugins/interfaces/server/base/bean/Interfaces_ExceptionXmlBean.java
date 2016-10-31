package com.zhangkssh.plugins.interfaces.server.base.bean;

public class Interfaces_ExceptionXmlBean {

	public Interfaces_ExceptionXmlBean() {
		super();
	}

	public Interfaces_ExceptionXmlBean(Integer errorCode, String errorText) {
		super();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	private Integer errorCode;

	private String errorText;

	private Interfaces_ExceptionXmlBean exception;

	public Interfaces_ExceptionXmlBean getException() {
		return exception;
	}

	public void setException(Interfaces_ExceptionXmlBean exception) {
		this.exception = exception;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
