package com.zhangkssh.frame.action;

import java.lang.reflect.Type;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhangkssh.baseframe.common.util.gson.ApiResponseGsonHelper;
import com.zhangkssh.baseframe.common.util.gson.GsonUtil;
import com.zhangkssh.baseframe.common.util.job.JobBean;
import com.zhangkssh.baseframe.struts.ContextPvd;
import com.zhangkssh.externalservice.action.BaseJobAction;
import com.zhangkssh.externalservice.commonobject.BaseArgs;
import com.zhangkssh.externalservice.commonobject.Constants;
import com.zhangkssh.frame.service.tools.Frame_DateTools;
import com.zhangkssh.frame.service.tools.Frame_EncryptTools;
import com.zhangkssh.frame.service.tools.Frame_ExcellTools;
import com.zhangkssh.frame.service.tools.Frame_ExceptionTools;
import com.zhangkssh.frame.service.tools.Frame_HttpTools;
import com.zhangkssh.frame.service.tools.Frame_IpAddressTools;
import com.zhangkssh.frame.service.tools.Frame_PropertiesTools;
import com.zhangkssh.frame.service.tools.Frame_ThreadTools;
import com.zhangkssh.frame.service.tools.Frame_XmlAndJsonTools;
import com.zhangkssh.frame.util.Frame_Constants;
import com.zhangkssh.frame.util.json.Frame_ErrorResponse;
import com.zhangkssh.frame.util.json.Frame_QueryJobResponse;

public class Frame_Action extends BaseJobAction {

	private static final long serialVersionUID = 1L;

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

	protected Date startDate = null;

	protected Date endDate = null;

	protected String strs;

	/**
	 * 过滤请求
	 */

	@Override
	public void validate() {
		super.validate();
		// 页面传来的时间转化成"GMT"
		actionArgsFilter(getActionArgs());
		setLocalThread();
	}

	private void setLocalThread() {
		Object timeZoneO = ContextPvd
				.getSessionAttr(Frame_Constants.SESSIONKEY_TIMEZONE);
		Object resourceInternationalizationTypeO = ContextPvd
				.getSessionAttr(Frame_Constants.SESSIONKEY_RESOURCEINTERNATIONALIZATIONTYPE);
		if (timeZoneO != null && !"".equals(timeZoneO.toString())) {
			threadTools.localThread_SetAttribute(
					Constants.LOCALTHREADKEY_TIMEZONE, timeZoneO.toString());
		}
		if (resourceInternationalizationTypeO != null
				&& !"".equals(resourceInternationalizationTypeO.toString())) {
			threadTools.localThread_SetAttribute(
					Constants.LOCALTHREADKEY_RESOURCEINTERNATIONALIZATIONTYPE,
					resourceInternationalizationTypeO.toString());
		}
	}

	/**
	 * 用来进行参数过滤，可以修改页面传过来的用户时区时间转化为标准时区时间， 子Action中的参数对象必须继承BaseArgs,
	 * 且名称为actionArgs 当前用来进行时区转化 ,将页面传来的某时区时间格式转化为标准时区GMT时间 不继承BaseArgs就不进行时区转化
	 * 时区时间放在session中
	 * 
	 * @return
	 */
	protected BaseArgs actionArgsFilter(BaseArgs baseArgs) {
		if (baseArgs != null) {
			String timeZone = ContextPvd
					.getSessionAttr(Frame_Constants.SESSIONKEY_TIMEZONE) == null ? ""
					: ContextPvd.getSessionAttr(
							Frame_Constants.SESSIONKEY_TIMEZONE).toString();
			Date[] startDate = baseArgs.getStartDate();
			Date[] endDate = baseArgs.getEndDate();
			if (startDate != null && startDate.length > 0) {
				for (int i = 0; i < startDate.length; i++) {
					startDate[i] = dateTools.changeDateToDateByZone(
							startDate[i], timeZone, Constants.TIMEZONE_GMT);
				}
			}
			if (endDate != null && endDate.length > 0) {
				for (int i = 0; i < endDate.length; i++) {
					endDate[i] = dateTools.changeDateToDateByZone(
							endDate[i], timeZone, Constants.TIMEZONE_GMT);
				}
			}
			baseArgs.setStartDate(startDate);
			baseArgs.setEndDate(endDate);
		}
		return baseArgs;
	}

	/**
	 * 子类需重写方法
	 * 
	 * @return
	 */
	protected BaseArgs getActionArgs() {
		return null;
	}

	/**
	 * 异步处理
	 * 
	 * @return
	 */
	public String _getjob() {
		String message = threadTools.job_GetJobMessage(jobId);
		String result = "";
		if (!StringUtils.isEmpty(message)) {
			result = "{\"queryasyncjobresultresponse\":" + message + "}";
		}
		jobId = "";
		strs = result;
		return "string";
	}

	protected JobBean getJobBean(JobBean jobBean, Object o) {
		Gson gson = ApiResponseGsonHelper.getBuilder().create();
		Type type = new TypeToken<Frame_QueryJobResponse>() {
		}.getType();
		String json = jobBean.getMessage();
		Frame_QueryJobResponse qjr = (Frame_QueryJobResponse) gson.fromJson(
				json, type);
		if (JobBean.JOB_SUCCESS == jobBean.getResult()) {
			qjr.setJobStatus(JobBean.JOB_SUCCESS);
			qjr.setJobResult(o);// 必须是json格式不能是字符串否则前台出错，转化出来也会带反斜杠
		} else {
			qjr.setJobStatus(JobBean.JOB_ERROR);
			String errortext = o == null ? "" : o.toString();
			Frame_ErrorResponse er = new Frame_ErrorResponse(jobBean.getErrorCode(), errortext);
			qjr.setJobResult(er);
		}
		jobBean.setMessage(GsonUtil.toJson(qjr));
		return jobBean;
	}

	protected void setStartEndDate() {
		BaseArgs actionArgs = getActionArgs();
		startDate = (actionArgs == null || actionArgs.getStartDate() == null || actionArgs
				.getStartDate().length <= 0) ? null
				: actionArgs.getStartDate()[0];
		endDate = (actionArgs == null || actionArgs.getEndDate() == null || actionArgs
				.getEndDate().length < 1) ? null : actionArgs.getEndDate()[0];
	}

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

	protected void clear() {
		id = null;
		startDate = null;
		endDate = null;
		strs = "";
	}
}
