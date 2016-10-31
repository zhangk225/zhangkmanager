package com.zhangkssh.frame.service.tools;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.baseframe.common.util.job.JobBean;
import com.zhangkssh.baseframe.common.util.job.JobInterface;
import com.zhangkssh.externalservice.commonobject.Constants;

@Service
public class Frame_ThreadTools {

	@Autowired
	private Frame_PropertiesTools propertiesTools;

	/**
	 * 开始一个异步任务
	 * 
	 * @param jobInterface
	 * @param jobBean
	 */
	public void job_StartJob(JobInterface jobInterface, JobBean jobBean) {
		CommonUtil.job_StartJob(jobInterface, jobBean);
	}

	/**
	 * 通过任务主键获取任务信息
	 * 
	 * @param jobId
	 *            任务主键
	 * @return
	 */
	public String job_GetJobMessage(String jobId) {
		return CommonUtil.job_GetJobMessage(jobId);
	}

	/**
	 * 获取本地线程中某一属性值
	 * 
	 * @param attributeName
	 * @return
	 */
	public Object localThread_GetAttribute(String attributeName) {
		return CommonUtil.localThread_GetAttribute(attributeName);
	}

	/**
	 * 获取本地线程某一属性值
	 * 
	 * @param attributeName
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public Object localThread_GetAttribute(String attributeName,
			Object defaultValue) {
		return CommonUtil.localThread_GetAttribute(attributeName, defaultValue);

	}

	/**
	 * 将属性值放入本地线程
	 * 
	 * @param attributeName
	 * @param value
	 */
	public void localThread_SetAttribute(String attributeName, Object value) {
		CommonUtil.localThread_SetAttribute(attributeName, value);
	}

	/**
	 * 将本地线程中的某一属性置为null
	 * 
	 * @param attributeName
	 */
	public void localThread_removeAttribute(String attributeName) {
		CommonUtil.localThread_removeAttribute(attributeName);
	}

	/**
	 * 获取资源国际化后缀 CH EN 先从本地线程变量中取（如果需要为每个web登录用户显示他所在地区的内容）,取不到则从配置文件中取
	 * 
	 * @return
	 */
	public String localThread_GetResourceInternationalizationType() {
		String retype = localThread_GetAttribute(
				Constants.LOCALTHREADKEY_RESOURCEINTERNATIONALIZATIONTYPE,
				propertiesTools.getResourceInternationalizationType())
				.toString();
		return retype;
	}

	/**
	 * 获取时区 先从本地线程取（如果需要为每个web登录用户显示他所在地时间） 如果取不到则认为是gmt
	 * 
	 * @return
	 */
	public String localThread_GetTimeZone() {
		String timezone = localThread_GetAttribute(
				Constants.LOCALTHREADKEY_TIMEZONE,
				TimeZone.getDefault().getID()).toString();
		return timezone;
	}
}
