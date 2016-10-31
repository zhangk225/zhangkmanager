package com.zhangkssh.plugins.interfaces.server.base.bean;

public class Interfaces_BaseResponseXmlBean {

	public static final int INTERFACES_RESULT_SUCCESS = 1;// 方法执行成功

	public static final int INTERFACES_RESULT_FAILED = 0;// 方法执行失败

	public Interfaces_BaseResponseXmlBean() {
		super();
	}
	
	public Interfaces_BaseResponseXmlBean(int result) {
		super();
		this.result = result;
	}
	
	public Interfaces_BaseResponseXmlBean(String reqId, int result) {
		super();
		this.reqId = reqId;
		this.result = result;
	}

	public Interfaces_BaseResponseXmlBean(String reqId, String token, int result) {
		super();
		this.reqId = reqId;
		this.result = result;
		this.token = token;
	}

	protected String reqId;

	protected String token;

	protected int result;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
