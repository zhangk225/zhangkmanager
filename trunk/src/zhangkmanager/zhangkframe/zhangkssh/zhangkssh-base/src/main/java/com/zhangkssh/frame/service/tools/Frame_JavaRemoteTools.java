package com.zhangkssh.frame.service.tools;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;

@Service
public class Frame_JavaRemoteTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_JavaRemoteTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 远程执行linux命令
	 * 
	 * @param ip
	 * @param user
	 * @param password
	 * @param command
	 * @return
	 * @throws IOException
	 */
	public String remotesh(String ip, String user, String password,
			String command) throws BaseException {
		try {
			return CommonUtil.javaRemote_Remotesh(ip, user, password, command);
		} catch (IOException e) {
			s_logger.error(
					"excute remote linux command error(IOException) in method Frame_JavaRemoteTools.remotesh("
							+ ip
							+ ","
							+ user
							+ ", "
							+ password
							+ " ,"
							+ command + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_IOEXCEPTION3);
		}
	}
}
