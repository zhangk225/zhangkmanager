package com.zhangkssh.plugins.interfaces.server.defaultuse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhangkssh.frame.util.Frame_Exception;
import com.zhangkssh.frame.util.Frame_MethodVO;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_BoolResponseXmlBean;
import com.zhangkssh.plugins.interfaces.server.base.bean.Interfaces_UserBean;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Exception;

@Service
public class Interfaces_DefaultLoginServiceImpl extends
		Interfaces_BaseDefaultLoginServiceImpl implements
		Interfaces_DefaultLoginService {

	private static final Logger s_logger = Logger
			.getLogger(Interfaces_DefaultLoginServiceImpl.class);

	@Override
	public String login(Interfaces_UserBean userMessage)
			throws Interfaces_Exception {
		if (defaultLoginPermission != null) {
			try {
				userMessage = defaultLoginPermission.login(userMessage);
				threadTools.localThread_SetAttribute(userMessage.getToken(),
						userMessage);
			} catch (Interfaces_Exception e) {
				s_logger.error(
						"login error (Interfaces_Exception) in method Interfaces_DefaultLoginServiceImpl.login("
								+ userMessage + ") ", e);
				throw e;
			}
		}
		return xmlAndJsonTools.objectToString(userMessage);
	}

	@Override
	public Boolean logout(Interfaces_UserBean userMessage) {
		if (defaultLoginPermission != null) {
			try {
				defaultLoginPermission.logout(userMessage);
				threadTools.localThread_removeAttribute(userMessage.getToken());
				return true;
			} catch (Interfaces_Exception e) {
				s_logger.error(
						"login error (Interfaces_Exception) in method Interfaces_DefaultLoginServiceImpl.login("
								+ userMessage + ") ", e);
				throw e;
			}
		}
		return false;
	}

	@Override
	public String doInvoke(String serviceName, String methodName, String xmlPrts) {
		s_logger.info(Interfaces_Constants.INTERFACES_PUBLISH_ARGS_METHODNAME
				+ ": " + methodName);
		s_logger.info(Interfaces_Constants.INTERFACES_PUBLISH_ARGS_XMLPRTS
				+ ": " + xmlPrts);
		String xml = "";
		String reqId = "";
		Frame_MethodVO methodVO = new Frame_MethodVO(serviceName, methodName,
				null);
		try {
			Interfaces_DefaultLoginRequestXmlBean request = (Interfaces_DefaultLoginRequestXmlBean) (xmlAndJsonTools
					.stringToObject(xmlPrts,
							Interfaces_DefaultLoginRequestXmlBean.class));
			String token = request.getToken();
			if (methodName
					.equals(Interfaces_Constants.METHODNAME_DEFAULTUSE_LOGIN)) {
				xml = login(request.getUserMessage());
			} else {
				this.checkToken(methodVO, token);
				reqId = request.getReqId();
				if (methodName
						.equals(Interfaces_Constants.METHODNAME_DEFAULTUSE_LOGOUT)) {
					Interfaces_BoolResponseXmlBean wjrxb = new Interfaces_BoolResponseXmlBean(
							reqId,
							token,
							Interfaces_BoolResponseXmlBean.INTERFACES_RESULT_SUCCESS,
							logout(request.getUserMessage()));
					xml = xmlAndJsonTools.objectToString(wjrxb);
				} else {
					Interfaces_Exception e = interfacesTools
							.getInterfacesExceptionByErrorCode(Interfaces_Exception.INTERFACES_METHODERROR);
					return interfacesTools
							.getInterfacesExceptionXMLByErrorCode(e, reqId);
				}
			}
			return xml;
		} catch (Interfaces_Exception e) {
			s_logger.error(
					"service error (Interfaces_Exception) in method Interfaces_DefaultLoginServiceImpl.doInvoke("
							+ methodName + "," + xmlPrts + ") ", e);
			return interfacesTools.getInterfacesExceptionXMLByErrorCode(e,
					reqId);
		} catch (Frame_Exception e) {
			s_logger.error(
					"service error (Interfaces_Exception) in method Interfaces_DefaultLoginServiceImpl.doInvoke("
							+ methodName + "," + xmlPrts + ") ", e);
			return interfacesTools.getInterfacesExceptionXMLByErrorCode(e,
					reqId);
		}
	}
}
