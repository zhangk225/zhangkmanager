package com.zhangkssh.frame.service.tools;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.externalservice.commonobject.Constants;
import com.zhangkssh.frame.init.Frame_ProjectBean;
import com.zhangkssh.frame.service.Frame_ProjectManagerService;
import com.zhangkssh.frame.util.Frame_Constants;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_ExceptionTools {

	@Autowired
	private Frame_ProjectManagerService projectManagerService;

	@Autowired
	private Frame_ThreadTools threadTools;

	@Autowired
	private Frame_PropertiesTools propertiesTools;

	/**
	 * 根据错误码抛出一个基础异常
	 * 
	 * @param errorCode
	 * @return
	 */
	public Frame_Exception getBaseExceptionByErrorCode(int errorCode) {
		return getFrameExceptionByCode(Constants.PROJECTID, errorCode, null);
	}

	/**
	 * 根据错误码抛出一个基础异常
	 * 
	 * @param errorCode
	 * @param valueMap
	 * @return
	 */
	public Frame_Exception getBaseExceptionByErrorCode(int errorCode,
			Map<String, String> valueMap) {
		return getFrameExceptionByCode(Constants.PROJECTID, errorCode, valueMap);
	}

	/**
	 * 根据错误码抛出一个框架异常
	 * 
	 * @param errorCode
	 * @return
	 */
	public Frame_Exception getFrameExceptionByErrorCode(int errorCode) {
		return getFrameExceptionByCode(Frame_Constants.PROJECT_ID, errorCode,
				null);
	}

	/**
	 * 根据错误码抛出一个框架异常
	 * 
	 * @param errorCode
	 * @return
	 */
	public Frame_Exception getFrameExceptionByErrorCode(int errorCode,
			Map<String, String> valueMap) {
		return getFrameExceptionByCode(Frame_Constants.PROJECT_ID, errorCode,
				valueMap);
	}

	public Frame_Exception getFrameExceptionByCode(int projectId,
			int errorCode, Map<String, String> valueMap) {
		String key = Frame_Exception.EXCEPTION_PROPERTIES_KEY + errorCode;
		String typeSuffix = threadTools
				.localThread_GetResourceInternationalizationType();
		if (StringUtils.isEmpty(typeSuffix)) {
			typeSuffix = propertiesTools.getResourceInternationalizationType();
		}
		Frame_ProjectBean projectBean = projectManagerService
				.getProjectById(projectId);
		String resourcePath = "";
		String resourceName = "";
		int eCode = 0;
		if (projectBean != null) {
			resourcePath = projectBean.getProjectPath();
			resourceName = projectBean.getProjectName();
			eCode = computeCode(projectId, errorCode);
		} else {
			eCode = errorCode;
		}
		if (StringUtils.isEmpty(resourceName)) {
			resourceName = Frame_Exception.EXCEPTION_PROPERTIES_NAME;
		} else {
			resourceName += "_" + Frame_Exception.EXCEPTION_PROPERTIES_NAME;
		}
		if (StringUtils.isEmpty(resourcePath)) {
			resourcePath = "";
		}

		String eText = propertiesTools.getResourceInternationalization(
				typeSuffix, resourcePath, resourceName, key, valueMap);
		if (eText == null) {
			eCode = BaseException.ERRORCODE_EXCEPTIONCATCHERROR1;
			key = Frame_Exception.EXCEPTION_PROPERTIES_KEY + eCode;
			resourcePath = "";
			resourceName = Frame_Exception.EXCEPTION_PROPERTIES_NAME;
			eText = propertiesTools.getResourceInternationalization(typeSuffix,
					resourcePath, resourceName, key, valueMap);
		}
		return new Frame_Exception(eCode, eText);
	}

	/**
	 * 通过项目id生成错误码
	 * 
	 * @param projectId
	 * @param errorCode
	 * @return
	 */
	private int computeCode(int projectId, int errorCode) {
		return projectId * 100 + errorCode;
	}
}
