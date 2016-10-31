package com.zhangkssh.plugins.interfaces.server.base.bean;

public class Interfaces_BaseRequestXmlBean {
	
	public Interfaces_BaseRequestXmlBean() {
		super();
	}

	public Interfaces_BaseRequestXmlBean(String reqId) {
		super();
		this.reqId = reqId;
	}

	private String reqId;
	
	private String token;
	
	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
