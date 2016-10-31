package com.zhangkssh.frame.service.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_ValidateTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_IpAddressTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	public void validateEmpty(Object object) throws Frame_Exception {
		if (object == null) {
			s_logger.error("check args is null in method Frame_ValidateTools.validateEmpty("
					+ object + ") ");
			throw exceptionTools
					.getFrameExceptionByErrorCode(Frame_Exception.ERRORCODE_ARGSCHECKERROR);
		}
		if (object instanceof String) {
			if (StringUtils.isEmpty(object.toString())) {
				s_logger.error("check args is null in method Frame_ValidateTools.validateEmpty("
						+ object + ") ");
				throw exceptionTools
						.getFrameExceptionByErrorCode(Frame_Exception.ERRORCODE_ARGSCHECKERROR);
			}
		}
	}
}
