package com.zhangkssh.externalservice.commonobject;

import java.util.Date;

/**
 * 用来做Action的参数过滤
 * @author zhangk
 *
 */
public class BaseArgs {

	private Date[] startDate;

	private Date[] endDate;

	public Date[] getStartDate() {
		return startDate;
	}

	public void setStartDate(Date[] startDate) {
		this.startDate = startDate;
	}

	public Date[] getEndDate() {
		return endDate;
	}

	public void setEndDate(Date[] endDate) {
		this.endDate = endDate;
	}
	
}
