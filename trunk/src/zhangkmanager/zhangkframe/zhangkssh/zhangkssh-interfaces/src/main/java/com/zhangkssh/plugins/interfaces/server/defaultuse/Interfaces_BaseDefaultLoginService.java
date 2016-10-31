package com.zhangkssh.plugins.interfaces.server.defaultuse;

import com.zhangkssh.frame.util.Frame_MethodVO;
import com.zhangkssh.plugins.interfaces.server.base.service.Interfaces_BaseService;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

public interface Interfaces_BaseDefaultLoginService extends
		Interfaces_BaseService {

	/**
	 * 处理webservice业务的方法(加入权限处理)
	 * 
	 * @param serviceName 如果是webservice为服务名称 ，其它为类名
	 * @param methodName
	 * @param request
	 * @return
	 */
	public String doInvoke(String serviceName,String methodName, String xmlPrts);

	/**
	 * 检测token是否正确
	 * 
	 * @param token
	 * @throws Interfaces_Exception
	 *             不正确则抛异常
	 */
	public void checkToken(Frame_MethodVO methodVO, String token)
			throws Interfaces_Exception;
}
