package com.zhangkssh.frame.service.tools;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_XmlAndJsonTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_XmlAndJsonTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 将对象转化成xmlString
	 * 
	 * @param o
	 * @return
	 * @throws JAXBException
	 */
	public String objectToString(Object o) throws Frame_Exception {
		try {
			return CommonUtil.xml_ObjectToString(o);
		} catch (JAXBException e) {
			s_logger.error(
					"error create xml object(JAXBException) in method Frame_XmlAndJsonTools.objectToString("
							+ o + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_JAXBEXCEPTION2);
		} catch (IOException e) {
			s_logger.error(
					"error create xml object(IOException) in method Frame_XmlAndJsonTools.objectToString("
							+ o + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_IOEXCEPTION3);
		}
	}

	/**
	 * 将xmlString转化成对象
	 * 
	 * @param xmlStr
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public Object stringToObject(String xmlStr, Class<? extends Object> c)
			throws Frame_Exception {
		try {
			return CommonUtil.xml_StringToObject(xmlStr, c);
		} catch (JAXBException e) {
			s_logger.error(
					"error create xml object(JAXBException) in method Frame_XmlAndJsonTools.stringToObject("
							+ xmlStr + "," + c + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_JAXBEXCEPTION2);
		}
	}
}
