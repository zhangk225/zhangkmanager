package com.zhangkssh.frame.service.tools;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.baseframe.common.util.http.HttpResponse;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_HttpTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_ExcellTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 通过SSL发送https请求，并获得返回
	 * 
	 * @param urlStr
	 *            - url字符串
	 * @param type
	 *            - 编码类型
	 * @param requestMethod
	 *            - http请求类型]
	 * @param json
	 *            - json信息
	 * @param cookie
	 *            - cookie字符串
	 * @return HttpResponse，包含response
	 *         code，内容和cookie信息，注意如果未能得到信息，code将返回-1，内容和cookie将返回空字符串
	 */
	public HttpResponse sendSSLWithJson(String urlStr, String type,
			String requestMethod, String json, String cookie) {
		return CommonUtil.http_SendSSLWithJson(urlStr, type, requestMethod,
				json, cookie);
	}

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @return
	 * @throws BaseException
	 */
	public String getContent(String url) throws Frame_Exception {
		try {
			return CommonUtil.http_GetContent(url);
		} catch (UnsupportedEncodingException e) {
			s_logger.error(
					"error send http request object(UnsupportedEncodingException) in method Frame_HttpTools.getContent("
							+ url + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_JAXBEXCEPTION2);
		}
	}
}
