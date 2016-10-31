package com.zhangkssh.frame.init;

import org.springframework.stereotype.Service;

@Service
public interface Frame_InitProjectService {

	/**
	 * 项目初始化
	 */
	public void initProject();
	
	/**
	 * 获取项目名称(在处理资源的时候会用到)
	 * @return
	 */
	public Frame_ProjectBean getProjectBean();
}
