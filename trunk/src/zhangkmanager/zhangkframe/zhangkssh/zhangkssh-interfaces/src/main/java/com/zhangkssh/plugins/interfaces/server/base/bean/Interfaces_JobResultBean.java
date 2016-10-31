package com.zhangkssh.plugins.interfaces.server.base.bean;

public class Interfaces_JobResultBean {

	public final static int JOB_RUNNING = 0;

	public final static int JOB_SUCCESS = 1;

	public final static int JOB_FAILED = 2;

	private int result;

	private String jobId;

	private String errorCode;

	private String errorText;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
