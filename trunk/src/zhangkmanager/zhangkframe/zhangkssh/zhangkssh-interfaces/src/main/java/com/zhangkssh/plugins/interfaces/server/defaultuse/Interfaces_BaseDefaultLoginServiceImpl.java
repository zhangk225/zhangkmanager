package com.zhangkssh.plugins.interfaces.server.defaultuse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangkssh.frame.util.Frame_MethodVO;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_UserBean;
import com.zhangkssh.plugins.interfaces.server.base.service.impl.Interfaces_BaseServiceImpl;
import com.zhangkssh.plugins.interfaces.server.base.tools.Interfaces_Tools;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

public abstract class Interfaces_BaseDefaultLoginServiceImpl extends
		Interfaces_BaseServiceImpl implements
		Interfaces_BaseDefaultLoginService {

	private static final Logger s_logger = Logger
			.getLogger(Interfaces_BaseDefaultLoginServiceImpl.class);

	@Autowired
	protected Interfaces_Tools interfacesTools;

	@Autowired(required = false)
	protected Interfaces_DefaultLoginPermissionService defaultLoginPermission;

	@Override
	public void checkToken(Frame_MethodVO methodVO, String token)
			throws Interfaces_Exception {
		Object userObject = threadTools
				.localThread_GetAttribute(Interfaces_Constants.DEFAULTUSE_TOKENMAP_KEY);
		if (userObject == null) {
			s_logger.error("error token int Interfaces_BaseDefaultLoginServiceImpl.checkToken("
					+ methodVO + "," + token + ")");
			throw interfacesTools
					.getInterfacesExceptionByErrorCode(Interfaces_Exception.INTERFACES_TOKENERROR);
		}
		Interfaces_UserBean userbean = (Interfaces_UserBean) userObject;
		if (defaultLoginPermission != null) {
			defaultLoginPermission.checkToken(userbean.getUser().getUserId(),
					token);
		}
	}
}
