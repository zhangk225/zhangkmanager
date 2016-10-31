package com.zhangkssh.externalservice.commonobject;

import com.zhangkssh.baseframe.common.util.ZhangkException;

public class BaseException extends ZhangkException {

	private static final long serialVersionUID = 1L;

	public static final int ERRORCODE_EXCEPTIONCATCHERROR1 = 0;// 异常抓取错误

	public static final int ERRORCODE_PARSEEXCEPTION1 = 1;// ParseException(解析异常)

	public static final int ERRORCODE_JAXBEXCEPTION2 = 2;// JAXBException(异常)

	public static final int ERRORCODE_IOEXCEPTION3 = 3;// IOException(异常)

	public static final int ERRORCODE_SOCKETEXCEPTION4 = 4;// SocketException(异常)

	public static final int ERRORCODE_UNKNOWNHOSTEXCEPTION5 = 5;// UnknownHostException

	public static final int ERRORCODE_ROWSEXCEEDEDEXCEPTION6 = 6;// RowsExceededException

	public static final int ERRORCODE_WRITEEXCEPTION7 = 7;// WriteException
	
	public static final int ERRORCODE_UNSUPPORTEDENCODINGEXCEPTION8 = 8;// UnsupportedEncodingException
	
	public static final int ERRORCODE_JSONEXCEPTION9 = 9;// JSONException
	
	public static final int ERRORCODE_FILENOTFOUNDEXCEPTION10 = 10;// FileNotFoundException

	/**
	 * 基本构造函数，传入错误码和错误信息
	 * 
	 * @param errorCode
	 * @param errorText
	 */
	public BaseException(int errorCode, String errorText) {
		super(errorCode, errorText);
	}
}
