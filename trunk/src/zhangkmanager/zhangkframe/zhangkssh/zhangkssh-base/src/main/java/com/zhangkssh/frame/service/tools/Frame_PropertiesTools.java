package com.zhangkssh.frame.service.tools;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.externalservice.commonobject.Constants;

@Service
public class Frame_PropertiesTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_IpAddressTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 得到数据库资源文件
	 * 
	 * @return
	 */
	public Properties getDBProperties() throws BaseException {
		String fileName = Constants.DBFILE;
		return getProperties(fileName);
	}

	/**
	 * 得到默认资源文件
	 * 
	 * @return
	 */
	public Properties getProperties() throws BaseException {
		String fileName = Constants.GLOBALFILE;
		return getProperties(fileName);
	}

	/**
	 * 通过名称获取资源文件
	 * 
	 * @param fileName
	 * @return
	 */
	public Properties getProperties(String fileName) throws BaseException {
		try {
			return CommonUtil.properties_GetProperties(fileName);
		} catch (IOException e) {
			s_logger.error(
					"get Properties error(IOException) in method Frame_PropertiesTools.getDBProperties("
							+ fileName + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_IOEXCEPTION3);
		}
	}

	/**
	 * 通过配置文件获取ip地址(Constants.GLOBALFILE)
	 * 
	 * @return
	 */
	public String getIpByProperties() throws BaseException {
		Properties p = getProperties();
		return p.getProperty(Constants.LOCAL_IP);
	}

	/**
	 * 获取资源格式化类型
	 */
	public String getResourceInternationalizationType() throws BaseException {
		Properties p = getProperties();
		return p.getProperty(Constants.RESOURCEINTERNATIONALIZATION_TYPE,
				Constants.RESOURCEINTERNATIONALIZATION_TYPE_CH);
	}

	/**
	 * 获取资源配置中的本机ip
	 * 
	 * @return
	 */
	public String getLocalIp() throws BaseException {
		Properties p = getProperties();
		return p.getProperty(Constants.LOCAL_IP, "");
	}

	/**
	 * 资源文件国际化 资源名称+加上后缀+.properties为资源完整名称
	 * 
	 * @param typeSuffix
	 *            后缀 CH EN
	 * @param resourcePath
	 *            资源所在路径
	 * @param resourceName
	 *            资源名称
	 * @param key
	 *            资源中key
	 * @param valueMap
	 *            支持动态变量 将配置文件中的${key}替换为value (key和value指的就是valueMap的key value)
	 * 
	 * @return ""表示值为空 null表示没有找到文件或者key值不存在
	 */
	public String getResourceInternationalization(
			String typeSuffix, String resourcePath, String resourceName,
			String key, Map<String, String> valueMap) throws BaseException {
		String path = resourcePath + resourceName + "_" + typeSuffix
				+ ".properties";
		Properties p = getProperties(path);
		if (p != null && !StringUtils.isEmpty(key)) {
			String value = "";
			value = p.getProperty(key);
			if (!StringUtils.isEmpty(value) && valueMap != null
					&& !valueMap.isEmpty()) {
				for (Map.Entry<String, String> entry : valueMap.entrySet()) {
					String enkey = entry.getKey();
					String envalue = entry.getValue();
					if (!StringUtils.isEmpty(enkey) && envalue != null) {
						value = value.replace("${" + enkey + "}", envalue);
					}
				}
			}
			return value;
		} else {
			return null;
		}
	}

}
