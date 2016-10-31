package com.zhangkssh.plugins.interfaces.server.defaultuse;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_BaseResponseXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_RESPONSE_XMLROOT)
public class Interfaces_DefaultLoginResponseXmlBean extends
		Interfaces_BaseResponseXmlBean {

	private Interfaces_UserXmlBean userMessage;

	public Interfaces_DefaultLoginResponseXmlBean() {
		super();
	}

	public Interfaces_DefaultLoginResponseXmlBean(String reqId, String token,
			int result, Interfaces_UserXmlBean userMessage) {
		super(reqId, token, result);
		this.userMessage = userMessage;
	}

	public Interfaces_UserXmlBean getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(Interfaces_UserXmlBean userMessage) {
		this.userMessage = userMessage;
	}

}
