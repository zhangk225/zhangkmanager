package com.zhangkssh.plugins.interfaces.server.base.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_RESPONSE_XMLROOT)
public class Interfaces_ExceptionResponseXmlBean extends
		Interfaces_BaseResponseXmlBean {

	private Interfaces_ExceptionXmlBean message;

	public Interfaces_ExceptionResponseXmlBean() {
		super();
	}

	public Interfaces_ExceptionResponseXmlBean(String reqId, int result,
			Interfaces_ExceptionXmlBean message) {
		super(reqId, result);
		this.message = message;
	}

	public Interfaces_ExceptionXmlBean getMessage() {
		return message;
	}

	public void setMessage(Interfaces_ExceptionXmlBean message) {
		this.message = message;
	}
}
