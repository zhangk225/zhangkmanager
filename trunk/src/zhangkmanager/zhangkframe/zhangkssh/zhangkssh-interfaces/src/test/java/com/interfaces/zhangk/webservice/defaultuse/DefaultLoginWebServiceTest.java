package com.interfaces.zhangk.webservice.defaultuse;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.plugins.interfaces.server.base.util.Interfaces_Constants;
import com.zhangkssh.plugins.interfaces.server.defaultuse.Interfaces_DefaultLoginResponseXmlBean;

public class DefaultLoginWebServiceTest {

	public static void main(String[] args){
		try {
		Interfaces_DefaultLoginWebServiceImpl defaultLoginService = new Interfaces_DefaultLoginWebServiceImplProxy().getInterfaces_DefaultLoginWebServiceImpl();
		String methodName1 = Interfaces_Constants.METHODNAME_DEFAULTUSE_LOGIN;
		String xml1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><request><reqId>mm</reqId></request>";
		String result1 = defaultLoginService.invoke(methodName1, xml1);
		System.out.println(methodName1 + ":" + result1);
		Interfaces_DefaultLoginResponseXmlBean webpr = (Interfaces_DefaultLoginResponseXmlBean) CommonUtil
				.xml_StringToObject(result1,
						Interfaces_DefaultLoginResponseXmlBean.class);
		String token = webpr.getToken();
		System.out.println("token:" + token);
		}catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
