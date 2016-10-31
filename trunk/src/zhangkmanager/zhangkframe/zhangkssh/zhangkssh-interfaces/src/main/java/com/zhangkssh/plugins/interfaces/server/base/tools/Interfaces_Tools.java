package com.zhangkssh.plugins.interfaces.server.base.tools;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.frame.service.tools.Frame_ExceptionTools;
import com.zhangkssh.frame.service.tools.Frame_PropertiesTools;
import com.zhangkssh.frame.service.tools.Frame_XmlAndJsonTools;
import com.zhangkssh.frame.util.Frame_Exception;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_BaseResponseXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_ExceptionResponseXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_ExceptionXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

@Service
public class Interfaces_Tools {

	@Autowired
	private Frame_PropertiesTools propertiesTools;

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	@Autowired
	private Frame_XmlAndJsonTools xmlAndJsonTools;

	public String getInterfacesResourceInternationalization(String key,
			Map<String, String> valueMap) {
		return propertiesTools.getResourceInternationalization(
				propertiesTools.getResourceInternationalizationType(),
				Interfaces_Constants.INTERFACES_RESOURCE_PATH,
				Interfaces_Constants.INTERFACES_INTERNATIONALIZATION_NAME, key,
				valueMap);
	}

	public Interfaces_Exception getInterfacesExceptionByErrorCode(int errorCode) {
		return getInterfacesExceptionByErrorCode(errorCode, null);
	}

	public Interfaces_Exception getInterfacesExceptionByErrorCode(
			int errorCode, Map<String, String> valueMap) {
		return new Interfaces_Exception(exceptionTools.getFrameExceptionByCode(
				Interfaces_Constants.PROJECT_ID, errorCode, valueMap));
	}

	public String getInterfacesExceptionXMLByErrorCode(Frame_Exception e,
			String reqId) {
		Interfaces_ExceptionXmlBean exception = new Interfaces_ExceptionXmlBean(
				Integer.valueOf(e.getErrorCode()), e.getErrorText());
		Interfaces_ExceptionXmlBean message = new Interfaces_ExceptionXmlBean();
		message.setException(exception);
		Interfaces_ExceptionResponseXmlBean wvzrxb = new Interfaces_ExceptionResponseXmlBean(
				reqId, Interfaces_BaseResponseXmlBean.INTERFACES_RESULT_FAILED,
				message);
		return xmlAndJsonTools.objectToString(wvzrxb);
	}

	public Properties getInterfacesProperties() {
		String fileName = Interfaces_Constants.INTERFACES_PROPERTIES;
		return propertiesTools.getProperties(fileName);
	}
}
