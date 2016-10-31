package com.zhangkssh.frame.service;

import java.util.Map;

import com.zhangkssh.frame.init.Frame_ProjectBean;

public interface Frame_ProjectManagerService {

	/**
	 * 获取项目列表
	 * @return
	 */
	public Map<Integer, Frame_ProjectBean> getProjectMap();

	/**
	 * 更新项目列表
	 * @param projectMap
	 */
	public void setProjectMap(Map<Integer, Frame_ProjectBean> projectMap);

	/**
	 * 通过项目id获取项目信息
	 * @param projectId
	 * @return
	 */
	public Frame_ProjectBean getProjectById(Integer projectId);

}
