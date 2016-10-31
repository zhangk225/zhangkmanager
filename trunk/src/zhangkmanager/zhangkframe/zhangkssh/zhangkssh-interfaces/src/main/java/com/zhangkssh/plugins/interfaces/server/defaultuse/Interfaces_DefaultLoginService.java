package com.zhangkssh.plugins.interfaces.server.defaultuse;

import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_UserBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

public interface Interfaces_DefaultLoginService extends
		Interfaces_BaseDefaultLoginService {

	public String login(Interfaces_UserBean userMessage)
			throws Interfaces_Exception;

	public Boolean logout(Interfaces_UserBean userMessage)
			throws Interfaces_Exception;
}
