package com.zhangkssh.plugins.interfaces.server.base.service.impl;

import org.springframework.stereotype.Service;

import com.zhangkssh.frame.init.Frame_InitProjectService;
import com.zhangkssh.frame.init.Frame_ProjectBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;

@Service
public class Interfaces_InitProjectServiceImpl implements
		Frame_InitProjectService {

	@Override
	public void initProject() {

	}

	@Override
	public Frame_ProjectBean getProjectBean() {
		Frame_ProjectBean frameProjectBean = new Frame_ProjectBean();
		frameProjectBean.setProjectId(Interfaces_Constants.PROJECT_ID);
		frameProjectBean.setProjectName(Interfaces_Constants.PROJECT_NAME);
		frameProjectBean
				.setProjectPath(Interfaces_Constants.INTERFACES_RESOURCE_PATH);
		return frameProjectBean;
	}

}
