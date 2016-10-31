package com.zhangkssh.frame.util.json;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Frame_QueryJobResponse extends Frame_BaseResponse {

	@Expose
	@SerializedName("jobstatus")
	private Integer jobStatus;

	@Expose
	@SerializedName("jobresult")
	private Object jobResult;

	@Expose
	@SerializedName("jobinstanceid")
	private String jobInstanceId;

	@Expose
	@SerializedName("created")
	private Date created;

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Object getJobResult() {
		return jobResult;
	}

	public void setJobResult(Object jobResult) {
		this.jobResult = jobResult;
	}

	public String getJobInstanceId() {
		return jobInstanceId;
	}

	public void setJobInstanceId(String jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
