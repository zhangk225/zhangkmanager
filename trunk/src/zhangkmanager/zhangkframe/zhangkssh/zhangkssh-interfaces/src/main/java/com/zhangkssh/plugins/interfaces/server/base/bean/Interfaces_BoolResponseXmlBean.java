package com.zhangkssh.plugins.interfaces.server.base.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_RESPONSE_XMLROOT)
public class Interfaces_BoolResponseXmlBean extends
		Interfaces_BaseResponseXmlBean {

	private Boolean message;

	public Interfaces_BoolResponseXmlBean() {
		super();
	}

	public Interfaces_BoolResponseXmlBean(String reqId, String token,
			int result, Boolean message) {
		super(reqId, token, result);
		this.message = message;
	}

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

}
