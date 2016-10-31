package com.zhangkssh.plugins.interfaces.server.defaultuse;

import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_UserBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

public interface Interfaces_DefaultLoginPermissionService {

	/**
	 * 检测token是否存在，如果接口中需要校验则应实现此方法
	 * 
	 * @param userId
	 * @param token
	 * @return
	 * @throws Interfaces_Exception
	 */
	public String checkToken(String userId, String token)
			throws Interfaces_Exception;

	/**
	 * 登录操作
	 * 
	 * @param user
	 * @return
	 */
	public Interfaces_UserBean login(Interfaces_UserBean user)
			throws Interfaces_Exception;

	/**
	 * 登出操作
	 * 
	 * @param user
	 * @throws Interfaces_Exception
	 */
	public void logout(Interfaces_UserBean user) throws Interfaces_Exception;
}
