package com.zhangkssh.externalservice.action;

public class BaseJobAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	protected String jobId;

	  public String getJobId() {
	    return jobId;
	  }

	  public void setJobId(String jobId) {
	    this.jobId = jobId;
	  }
}
