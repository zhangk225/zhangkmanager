package com.zhangkssh.frame.service.tools;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_EncryptTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_EncryptTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 转码
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encode(String text) throws Frame_Exception {
		try {
			return CommonUtil.other_Encode(text);
		} catch (UnsupportedEncodingException e) {
			s_logger.error(
					"encode String error (UnsupportedEncodingException) in method Frame_EncryptTools.encode()",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_UNSUPPORTEDENCODINGEXCEPTION8);
		}
	}

	/**
	 * 解码
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decode(String text) throws BaseException {
		try {
			return CommonUtil.other_Decode(text);
		} catch (UnsupportedEncodingException e) {
			s_logger.error(
					"decode String error (UnsupportedEncodingException) in method Frame_EncryptTools.decode()",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_UNSUPPORTEDENCODINGEXCEPTION8);
		}
	}

	/**
	 * 按基础算法加密
	 * 
	 * @param strSrc
	 *            要加密的字符串
	 * @param encName
	 *            算法类型 MD5 SHA-1 SHA-256
	 * @return
	 */
	public String encryptByBaseArith(String strSrc, String encName) {
		return CommonUtil.encrypt_EncryptByBaseArith(strSrc, encName);
	}

	/**
	 * 按MD5算法加密
	 * 
	 * @param strSrc
	 *            要加密的字符串
	 * @return
	 */
	public String encryptByMD5(String strSrc) {
		return encryptByBaseArith(strSrc, "MD5");
	}
}
