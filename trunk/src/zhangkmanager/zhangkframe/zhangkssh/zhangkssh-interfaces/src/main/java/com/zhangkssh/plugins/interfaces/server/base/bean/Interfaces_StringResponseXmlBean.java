package com.zhangkssh.plugins.interfaces.server.base.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_RESPONSE_XMLROOT)
public class Interfaces_StringResponseXmlBean extends
		Interfaces_BaseResponseXmlBean {

	private String message;

	public Interfaces_StringResponseXmlBean() {
		super();
	}

	public Interfaces_StringResponseXmlBean(String reqId, int result,
			String message) {
		super(reqId, result);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
