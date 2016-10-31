package com.zhangkssh.frame.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zhangkssh.frame.init.Frame_ProjectBean;
import com.zhangkssh.frame.service.Frame_ProjectManagerService;

@Service
public class Frame_ProjectManagerServiceImpl implements
		Frame_ProjectManagerService {

	private Map<Integer, Frame_ProjectBean> projectMap = new HashMap<Integer, Frame_ProjectBean>();

	@Override
	public Map<Integer, Frame_ProjectBean> getProjectMap() {
		return projectMap;
	}

	@Override
	public void setProjectMap(Map<Integer, Frame_ProjectBean> projectMap) {
		this.projectMap = projectMap;
	}

	@Override
	public Frame_ProjectBean getProjectById(Integer projectId) {
		return projectMap.get(projectId);
	}
}
