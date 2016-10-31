package com.zhangkssh.frame.log;

import com.zhangkssh.frame.util.Frame_Exception;

public interface Frame_LogService {

	public void addlog(String projectName, String eventType)
			throws Frame_Exception;

}
