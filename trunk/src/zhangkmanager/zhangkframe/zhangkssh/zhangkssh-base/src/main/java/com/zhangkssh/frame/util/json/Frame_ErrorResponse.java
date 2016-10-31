package com.zhangkssh.frame.util.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Frame_ErrorResponse extends Frame_BaseResponse {

	@Expose
	@SerializedName("errorcode")
	private Integer errorCode;

	@Expose
	@SerializedName("errortext")
	private String errorText;

	public Frame_ErrorResponse(Integer errorCode, String errorText) {
		super();
		this.errorCode = errorCode;
		this.errorText = errorText;
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
