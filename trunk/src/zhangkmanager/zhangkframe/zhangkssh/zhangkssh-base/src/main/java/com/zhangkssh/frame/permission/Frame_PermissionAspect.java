package com.zhangkssh.frame.permission;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhangkssh.frame.util.Frame_Exception;
import com.zhangkssh.frame.util.Frame_MethodVO;

@Aspect
@Component
public class Frame_PermissionAspect {

	@Autowired(required = false)
	private Frame_PermissionService framePermissionService;

	private static final Logger s_logger = Logger
			.getLogger(Frame_PermissionAspect.class);

	@Before(value = "!execution(public String com.zhangkssh.frame.action.Frame_ExceptionAction.execute()) && execution(public String com.zhangkssh..action.*._*()) && !@annotation(com.zhangkssh.frame.permission.Frame_PermissionExceptAnnotation)")
	public void checkPermission(JoinPoint jp) throws Frame_Exception {
		Frame_MethodVO methodVO = new Frame_MethodVO(jp.getClass().getName(),
				jp.getSignature().getName(), jp.getArgs());
		s_logger.info("Frame_PermissionAspect | checkPermission className="
				+ methodVO.getClassName() + ",methodName="
				+ methodVO.getMethodName() + ",args=" + methodVO.getArgs());
		if (framePermissionService != null) {
			framePermissionService.checkPermission(methodVO);
		} else {
			s_logger.error("Frame_PermissionAspect | checkPermission have no permission system,so your system will unsafed");
		}

	}

}
