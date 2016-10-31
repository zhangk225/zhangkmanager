package com.zhangkssh.frame.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhangkssh.baseframe.common.util.gson.GsonUtil;
import com.zhangkssh.baseframe.common.util.job.JobBean;
import com.zhangkssh.baseframe.common.util.job.JobInterface;
import com.zhangkssh.externalservice.service.impl.BaseServiceImpl;
import com.zhangkssh.frame.service.Frame_BaseService;
import com.zhangkssh.frame.service.Frame_ProjectManagerService;
import com.zhangkssh.frame.service.tools.Frame_DateTools;
import com.zhangkssh.frame.service.tools.Frame_EncryptTools;
import com.zhangkssh.frame.service.tools.Frame_ExcellTools;
import com.zhangkssh.frame.service.tools.Frame_ExceptionTools;
import com.zhangkssh.frame.service.tools.Frame_HttpTools;
import com.zhangkssh.frame.service.tools.Frame_IpAddressTools;
import com.zhangkssh.frame.service.tools.Frame_JavaRemoteTools;
import com.zhangkssh.frame.service.tools.Frame_PropertiesTools;
import com.zhangkssh.frame.service.tools.Frame_ThreadTools;
import com.zhangkssh.frame.service.tools.Frame_XmlAndJsonTools;
import com.zhangkssh.frame.util.json.Frame_QueryJobResponse;

public class Frame_BaseServiceImpl extends BaseServiceImpl implements
		Frame_BaseService {

	@Autowired
	protected Frame_PropertiesTools propertiesTools;

	@Autowired
	protected Frame_DateTools dateTools;

	@Autowired
	protected Frame_EncryptTools encryptTools;

	@Autowired
	protected Frame_ExcellTools excellTools;

	@Autowired
	protected Frame_HttpTools httpTools;

	@Autowired
	protected Frame_IpAddressTools ipAddressTools;

	@Autowired
	protected Frame_ExceptionTools eceptionTools;

	@Autowired
	protected Frame_XmlAndJsonTools xmlAndJsonTools;

	@Autowired
	protected Frame_ThreadTools threadTools;
	
	@Autowired
	protected Frame_JavaRemoteTools frameJavaRemoteTools;

	@Autowired
	protected Frame_ProjectManagerService projectManagerService;

	@Override
	public String putJob(JobInterface jobInterface) {
		String jobId = UUID.randomUUID().toString();
		Frame_QueryJobResponse qjr = new Frame_QueryJobResponse();
		qjr.setJobStatus(JobBean.JOB_DOING);
		qjr.setCreated(new Date());
		qjr.setJobInstanceId(jobId);
		String message = GsonUtil.toJson(qjr);
		JobBean jb = new JobBean();
		jb.setJobId(jobId);
		jb.setCreated(qjr.getCreated());
		jb.setMessage(message);
		threadTools.job_StartJob(jobInterface, jb);
		return jobId;
	}

	@Override
	public String getResourceInternationalization(String resourcePath,
			String resourceName, String key, Map<String, String> valueMap) {
		return propertiesTools.getResourceInternationalization(
				threadTools.localThread_GetResourceInternationalizationType(),
				resourcePath, resourceName, key, valueMap);
	}

}
