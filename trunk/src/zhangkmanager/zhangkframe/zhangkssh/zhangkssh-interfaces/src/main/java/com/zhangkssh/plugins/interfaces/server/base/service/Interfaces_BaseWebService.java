package com.zhangkssh.plugins.interfaces.server.base.service;

import com.zhangkssh.frame.webservice.service.Frame_WebservicePublishService;

public interface Interfaces_BaseWebService extends Interfaces_BaseService,
		Frame_WebservicePublishService {

	/**
	 * 发布的方法
	 * 
	 * @param methodName
	 * @param xmlPrts
	 * @return
	 */
	public String invoke(String methodName, String xmlPrts);

	/**
	 * 获取发布interfaces的url
	 * 
	 * @param ServiceName
	 * @return
	 */
	public String getInterfacesUrlByServiceName(String ServiceName);

}
