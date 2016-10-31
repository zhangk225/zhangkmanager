package com.zhangkssh.frame.util;

import com.zhangkssh.externalservice.commonobject.BaseException;

public class Frame_Exception extends BaseException {

	private static final long serialVersionUID = 1L;

	public static final String EXCEPTION_PROPERTIES_KEY = "exception.key";

	public static final String EXCEPTION_PROPERTIES_NAME = "exception";

	public static final int ERRORCODE_ARGSCHECKERROR = 50;// 参数校验错误

	public static final int ERRORCODE_REGISTERPROJECTERROR = 51;// 项目注册错误

	public Frame_Exception(int errorCode, String errorText) {
		super(errorCode, errorText);
	}

}
