package com.zhangkssh.plugins.interfaces.server.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhangkssh.frame.service.Frame_ProjectManagerService;
import com.zhangkssh.frame.service.impl.Frame_BaseServiceImpl;
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
import com.zhangkssh.plugins.interfaces.server.base.service.Interfaces_BaseService;
import com.zhangkssh.plugins.interfaces.server.base.tools.Interfaces_Tools;

public class Interfaces_BaseServiceImpl extends Frame_BaseServiceImpl implements Interfaces_BaseService {

	@Autowired
	protected Interfaces_Tools interfacesTools;

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

}
