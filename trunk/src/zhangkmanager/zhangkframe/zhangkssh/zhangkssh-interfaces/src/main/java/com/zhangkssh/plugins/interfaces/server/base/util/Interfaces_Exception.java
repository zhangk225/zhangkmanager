package com.zhangkssh.plugins.interfaces.server.base.util;

import com.zhangkssh.frame.util.Frame_Exception;

public class Interfaces_Exception extends Frame_Exception {

	private static final long serialVersionUID = 1L;

	public static final String INTERFACES_EXCEPTION_RESOURCENAME = "interfaces_exception";

	public static final int INTERFACES_METHODERROR = 1;// 方法参数不存在

	public static final int INTERFACES_PASSWORDERROR = 2;// 密码错误

	public static final int INTERFACES_USERNOEXSIT = 3;// 用户名不存在

	public static final int INTERFACES_USERNAMEPASSWORDISEMPTY = 4;// 用户名密码不能为空

	public static final int INTERFACES_TOKENERROR = 5;// 认证码错误

	public static final int INTERFACES_NOTLOGIN = 6;// 尚未登录

	public static final int INTERFACES_JOBNOTEXIST = 7;// 异步任务不存在

	public Interfaces_Exception(Frame_Exception fe) {
		super(fe.getErrorCode(), fe.getErrorText());
	}
}
