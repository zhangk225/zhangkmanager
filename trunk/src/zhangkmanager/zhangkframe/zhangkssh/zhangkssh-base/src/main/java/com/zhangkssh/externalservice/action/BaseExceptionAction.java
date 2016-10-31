package com.zhangkssh.externalservice.action;

import com.opensymphony.xwork2.ActionContext;

import com.zhangkssh.baseframe.struts.ZhangkBaseAction;
import com.zhangkssh.externalservice.commonobject.BaseException;

public class BaseExceptionAction extends ZhangkBaseAction {

	private static final long serialVersionUID = 1L;

	private BaseException exception;

	public BaseException getException() {
		return exception;
	}

	public void setException(BaseException exception) {
		this.exception = exception;
	}

	public String execute() {
		ActionContext.getContext().getValueStack()
				.push(this.exception.getMessage());// 放到值栈顶
		return SUCCESS;
	}

}
