package com.zhangkssh.frame.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zhangkssh.frame.service.Frame_ProjectManagerService;
import com.zhangkssh.frame.service.tools.Frame_ExceptionTools;
import com.zhangkssh.frame.util.Frame_Exception;

@SuppressWarnings("rawtypes")
@Component
// 启动tomcat的时候会初始化带注解的bean，但不会注入bean
public class Frame_Init implements ApplicationListener {

	private static final Logger s_logger = Logger.getLogger(Frame_Init.class);

	@Autowired
	List<Frame_InitProjectService> frameInitProjectServices;

	@Autowired
	Frame_ProjectManagerService frameProjectManagerService;

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	@Override
	public void onApplicationEvent(ApplicationEvent event)
			throws Frame_Exception {
		if (frameInitProjectServices != null
				&& frameInitProjectServices.size() > 0) {
			Map<Integer, Frame_ProjectBean> projectMap = new HashMap<Integer, Frame_ProjectBean>();
			for (Frame_InitProjectService frameInitProjectService : frameInitProjectServices) {
				Frame_ProjectBean projectBean = frameInitProjectService
						.getProjectBean();
				if (projectBean != null) {
					s_logger.info("register project "
							+ projectBean.getProjectName());
					frameInitProjectService.initProject();
					if (!projectMap.containsKey(projectBean.getProjectId())) {
						projectMap.put(projectBean.getProjectId(), projectBean);
						s_logger.info("register project "
								+ projectBean.getProjectName() + " success");
					} else {
						s_logger.error("project register error in method Frame_Init.onApplicationEvent() because the same projectid "
								+ projectBean.getProjectId());
						throw exceptionTools
								.getFrameExceptionByErrorCode(Frame_Exception.ERRORCODE_REGISTERPROJECTERROR);
					}
				} else {
					s_logger.error("project register error in method Frame_Init.onApplicationEvent() because no project info in Frame_Init ");
					throw exceptionTools
							.getFrameExceptionByErrorCode(Frame_Exception.ERRORCODE_REGISTERPROJECTERROR);
				}
			}
			frameProjectManagerService.setProjectMap(projectMap);
		} else {
			s_logger.info("no project register in system");
		}
	}
}
