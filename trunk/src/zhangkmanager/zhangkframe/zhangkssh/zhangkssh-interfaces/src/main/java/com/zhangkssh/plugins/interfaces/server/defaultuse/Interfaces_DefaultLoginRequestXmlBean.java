package com.zhangkssh.plugins.interfaces.server.defaultuse;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_BaseRequestXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_REQUEST_XMLROOT)
public class Interfaces_DefaultLoginRequestXmlBean extends
		Interfaces_BaseRequestXmlBean {

	private Interfaces_UserXmlBean userMessage;

	public Interfaces_DefaultLoginRequestXmlBean() {
		super();
	}

	public Interfaces_DefaultLoginRequestXmlBean(String reqId) {
		super(reqId);
	}

	public Interfaces_UserXmlBean getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(Interfaces_UserXmlBean userMessage) {
		this.userMessage = userMessage;
	}
}
