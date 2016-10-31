package com.zhangkssh.plugins.interfaces.server.defaultuse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.plugins.interfaces.server.base.service.impl.Interfaces_BaseWebServiceImpl;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@Service
@WebService(serviceName = Interfaces_Constants.INTERFACES_DEFAULTUSE_SERVICENAME, targetNamespace = Interfaces_Constants.INTERFACES_PUBLISH_TARGETNAMESPACE)
public class Interfaces_DefaultLoginWebServiceImpl extends
		Interfaces_BaseWebServiceImpl implements
		Interfaces_DefaultLoginWebService {

	private static final Logger s_logger = Logger
			.getLogger(Interfaces_DefaultLoginWebServiceImpl.class);

	@Autowired
	private Interfaces_DefaultLoginService interfacesDefaultLoginService;

	@Override
	@WebMethod(action = Interfaces_Constants.INTERFACES_DEFAULTUSE_SERVICEACTION, operationName = Interfaces_Constants.INTERFACES_PUBLISH_METHOD_NAME)
	@WebResult(name = Interfaces_Constants.INTERFACES_PUBLISH_RESULT_NAME)
	public String invoke(
			@WebParam(name = Interfaces_Constants.INTERFACES_PUBLISH_ARGS_METHODNAME) String methodName,
			@WebParam(name = Interfaces_Constants.INTERFACES_PUBLISH_ARGS_XMLPRTS) String xmlPrts) {
		return interfacesDefaultLoginService.doInvoke(
				Interfaces_Constants.INTERFACES_DEFAULTUSE_SERVICENAME,
				methodName, xmlPrts);
	}

	@Override
	@WebMethod (exclude=true)
	public void publish() {
		String httpurl = getInterfacesUrlByServiceName(Interfaces_Constants.INTERFACES_DEFAULTUSE_SERVICEPATH);
		s_logger.info("======start public service at url" + httpurl);
		Endpoint.publish(httpurl, this);
	}

}
