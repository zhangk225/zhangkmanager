package com.zhangkssh.plugins.interfaces.server.base.bean;

import java.util.Date;

import com.zhangkssh.frame.permission.Frame_UserVO;

public class Interfaces_UserBean {

	private Frame_UserVO user;

	private String token;

	private Date accessDate;
	
	public Frame_UserVO getUser() {
		return user;
	}

	public void setUser(Frame_UserVO user) {
		this.user = user;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
