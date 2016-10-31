package com.zhangkssh.baseframe.struts;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ServletRedirectResult;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * 扩展Struts2返回类型,直接返回String
 * 
 * 
 * 
 * @author Carmack Created on 2009-3-24 下午03:36:32
 */

public class StringResult extends ServletRedirectResult {

	/**
	 * 
	 * @author Carmack Created on 2009-3-24 下午03:36:24
	 */

	private static final long serialVersionUID = -2800270132418148253L;

	private String contentTypeName;
	private String stringName = "";

	public StringResult() {

		super();

	}

	public StringResult(String location) {

		super(location);

	}

	public void doExecute(String finalLocation, ActionInvocation invocation)
			throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext().get(HTTP_RESPONSE);
		String contentType = conditionalParse(contentTypeName, invocation);
		if (contentType == null) {
			contentType = "text/plain; charset=UTF-8";
		}
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "inline");
		PrintWriter out = response.getWriter();
		String result = (String) invocation.getStack().findValue(stringName);
		out.println(result);
		out.flush();
		out.close();
	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

	public String getStringName() {
		return stringName;
	}

	public void setStringName(String stringName) {
		this.stringName = stringName;
	}

}
