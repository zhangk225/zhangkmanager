package com.zhangkssh.frame.log;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhangkssh.frame.init.Frame_Init;
import com.zhangkssh.frame.init.Frame_ProjectBean;
import com.zhangkssh.frame.service.Frame_ProjectManagerService;
import com.zhangkssh.frame.util.Frame_Exception;

@Aspect
@Component
public class Frame_LogAspect {

	private static final Logger s_logger = Logger.getLogger(Frame_Init.class);

	@Autowired(required = false)
	private Frame_LogService frameLogService;

	@Autowired
	Frame_ProjectManagerService frameProjectManagerService;

	/*
	 * *
	 * A Join Point is defined in the action layer where the method needs a
	 * permission check.
	 */
	// execution 方法的切入语法,在什么地方执行切面
	@Pointcut("@annotation(com.zhangkssh.frame.log.Frame_LogEventAnnotation)||@annotation(com.zhangkssh.frame.log.Frame_LogAlertAnnotation)")
	public void logAccess() {
	}

	@AfterReturning(pointcut = "logAccess()&&@annotation(cloudLogEventAnnotation)", argNames = "cloudLogEventAnnotation")
	public void addEvent(JoinPoint jp,
			Frame_LogEventAnnotation frameLogEventAnnotation)
			throws Frame_Exception {
		if (frameLogService != null) {
			String projectId = frameLogEventAnnotation.projectId();
			if (!StringUtils.isEmpty(projectId)) {
				Frame_ProjectBean frameProjectBean = frameProjectManagerService
						.getProjectById(Integer.valueOf(projectId));
				if (frameProjectBean != null) {
					String projectName = frameProjectBean.getProjectName();
					if (!StringUtils.isEmpty(projectName)) {
						frameLogService.addlog(projectName,
								frameLogEventAnnotation.eventType());
					}
				} else {
					s_logger.error("error project id in Frame_LogAspect");
				}
			} else {
				s_logger.error("error project id in Frame_LogAspect");
			}
		} else {
			s_logger.warn("not hava record event");
		}
	}

	// @AfterThrowing(pointcut =
	// "logAccess()&&@annotation(cloudLogAlertAnnotation,rl)", argNames =
	// "cloudLogAlertAnnotation", throwing = "ex")
	// public void addAlert(JoinPoint jp,Exception ex,
	// Cloud_LogAlertAnnotation cloudLogAlertAnnotation) throws Exception {
	// String ss = cloudLogAlertAnnotation.alertType();
	// System.out.println(ss + "============================");
	// }
}
