package com.zhangkssh.plugins.interfaces.server.base.service.impl;

import java.util.Properties;

import com.zhangkssh.plugins.interfaces.server.base.service.Interfaces_BaseWebService;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

public abstract class Interfaces_BaseWebServiceImpl extends
		Interfaces_BaseServiceImpl implements Interfaces_BaseWebService {

	@Override
	public String getInterfacesUrlByServiceName(String ServiceName) {
		Properties p = interfacesTools.getInterfacesProperties();
		String webserviceurl = "http://"
				+ propertiesTools.getIpByProperties()
				+ ":"
				+ p.getProperty(Interfaces_Constants.PROPERTIESKEY_SERVICE_PORT)
				+ "/"
				+ p.getProperty(Interfaces_Constants.PROPERTIESKEY_SERVICE_PATH)
				+ "/" + ServiceName;
		return webserviceurl;
	}
}
