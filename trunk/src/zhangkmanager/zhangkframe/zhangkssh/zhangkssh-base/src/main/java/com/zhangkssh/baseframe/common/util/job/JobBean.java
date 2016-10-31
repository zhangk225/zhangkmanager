package com.zhangkssh.baseframe.common.util.job;

import java.util.Date;

public class JobBean {

	public final static int JOB_DOING = 0; // 任务正在进行

	public final static int JOB_SUCCESS = 1;// 任务执行成功

	public final static int JOB_ERROR = 2;// 任务执行失败

	private int result;

	private String message;

	private String jobId;

	private Date created;

	private int errorCode;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
