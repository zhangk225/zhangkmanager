package com.zhangkssh.frame.service;

import java.util.Map;

import com.zhangkssh.baseframe.common.util.job.JobInterface;
import com.zhangkssh.externalservice.service.BaseService;

public interface Frame_BaseService extends
		BaseService {

	/**
	 * 获取资源国际化 目前支持 CH 和 EN两种 支持变量替换 value中带 "@?@"的会被替换为具体值 此方法只被service层调用
	 * 
	 * @param resourcePath
	 *            资源文件路径 以"/"结尾
	 * @param resourceName
	 *            资源名称 不带".properties"
	 * @param key
	 *            资源主键
	 * @param String
	 *            ... replacevalue 要替换的值
	 * @return 资源值
	 */
	public String getResourceInternationalization(String resourcePath,
			String resourceName, String key, Map<String, String> valueMap);

	public String putJob(JobInterface jobInterface);

}
