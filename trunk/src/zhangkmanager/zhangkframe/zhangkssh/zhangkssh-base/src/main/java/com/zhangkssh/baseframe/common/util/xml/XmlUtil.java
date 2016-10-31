package com.zhangkssh.baseframe.common.util.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class XmlUtil {
	private static final Logger s_logger = Logger.getLogger(XmlUtil.class
			.getName());

	/**
	 * 将对象转化成xmlString
	 * 
	 * @param o
	 * @return
	 * @throws JAXBException
	 */
	public String objectToString(Object o) throws JAXBException,
			IOException {
		String result = "";
		if (o == null) {
			return result;
		}
		try {
			JAXBContext jc;
			jc = JAXBContext.newInstance(o.getClass());
			Marshaller mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			StringWriter writer = new StringWriter();
			mar.marshal(o, writer);
			result = writer.toString();
			writer.close();
		} catch (JAXBException e) {
			s_logger.error(
					"object change to StringXML error (JAXBException) XmlUtil.objectToString("
							+ o + ") ", e);
			throw e;
		} catch (IOException e) {
			s_logger.error(
					"object change to StringXML error (IOException) XmlUtil.objectToString("
							+ o + ") ", e);
			throw e;
		}
		return result;
	}

	/**
	 * 将xmlString转化成对象
	 * @param xmlStr
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public Object StringToObject(String xmlStr, Class<? extends Object> c)
			throws JAXBException {
		Object o = null;
		if (StringUtils.isEmpty(xmlStr)) {
			return o;
		}
		try {
			JAXBContext jc = JAXBContext.newInstance(c);
			Unmarshaller unmar = jc.createUnmarshaller();
			o = unmar.unmarshal(new StringReader(xmlStr));
		} catch (JAXBException e) {
			s_logger.error(
					"StringXML change to Object error (JAXBException) XmlUtil.StringToObject("
							+ o + ") ", e);
			throw e;
		}
		return o;
	}
}
