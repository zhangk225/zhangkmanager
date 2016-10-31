package com.zhangkssh.frame.permission;

import com.zhangkssh.frame.util.Frame_Exception;
import com.zhangkssh.frame.util.Frame_MethodVO;

public interface Frame_PermissionService {

	public void checkPermission(Frame_MethodVO methodVO) throws Frame_Exception;
}
