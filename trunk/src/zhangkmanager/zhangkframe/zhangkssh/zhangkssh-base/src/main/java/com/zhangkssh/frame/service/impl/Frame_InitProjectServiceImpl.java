package com.zhangkssh.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.frame.init.Frame_InitProjectService;
import com.zhangkssh.frame.init.Frame_ProjectBean;
import com.zhangkssh.frame.util.Frame_Constants;
import com.zhangkssh.frame.webservice.service.Frame_WebservicePublishService;

@Service
public class Frame_InitProjectServiceImpl implements Frame_InitProjectService {

	@Autowired(required = false)
	private List<Frame_WebservicePublishService> frameWebservicePublishServices;

	@Override
	public void initProject() {
		if (frameWebservicePublishServices != null
				&& frameWebservicePublishServices.size() > 0) {
			for (Frame_WebservicePublishService frameWebservicePublishService : frameWebservicePublishServices) {
				frameWebservicePublishService.publish();
			}
		}
	}

	@Override
	public Frame_ProjectBean getProjectBean() {
		Frame_ProjectBean frameProjectBean = new Frame_ProjectBean();
		frameProjectBean.setProjectId(Frame_Constants.PROJECT_ID);
		frameProjectBean.setProjectName(Frame_Constants.PROJECT_NAME);
		frameProjectBean.setProjectPath(Frame_Constants.FRAME_PATH);
		return frameProjectBean;
	}

}
