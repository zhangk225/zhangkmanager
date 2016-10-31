package com.zhangkssh.plugins.interfaces.server.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_BaseResponseXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@XmlRootElement(name = Interfaces_Constants.INTERFACES_RESPONSE_XMLROOT)
public class Interfaces_JobResponseXmlBean extends
		Interfaces_BaseResponseXmlBean {

	private Interfaces_JobXmlBean message;

	public Interfaces_JobResponseXmlBean() {
		super();
	}

	public Interfaces_JobResponseXmlBean(String reqId, String token,
			int result, Interfaces_JobXmlBean message) {
		super(reqId, token, result);
		this.message = message;
	}

	public Interfaces_JobXmlBean getMessage() {
		return message;
	}

	public void setMessage(Interfaces_JobXmlBean message) {
		this.message = message;
	}

}
