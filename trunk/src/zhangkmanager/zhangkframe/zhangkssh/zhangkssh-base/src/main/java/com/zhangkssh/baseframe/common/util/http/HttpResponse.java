package com.zhangkssh.baseframe.common.util.http;

/**
 * Created with IntelliJ IDEA. User: Derui Date: 13-12-9 Time: 下午3:13 To change
 * this template use File | Settings | File Templates.
 */
public class HttpResponse {
	public static final int CODE_SUCCESS = 200;

	private int responseCode = -1;

	private String responseContents;

	private String responseCookies;

	public HttpResponse() {
	}

	public HttpResponse(int responseCode, String responseContents,
			String responseCookies) {
		this.responseCode = responseCode;
		this.responseContents = responseContents;
		this.responseCookies = responseCookies;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseCookies() {
		return responseCookies;
	}

	public void setResponseCookies(String responseCookies) {
		this.responseCookies = responseCookies;
	}

	public String getResponseContents() {
		return responseContents;
	}

	public void setResponseContents(String responseContents) {
		this.responseContents = responseContents;
	}

}
