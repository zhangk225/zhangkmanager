/**
 * Defaultuseservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.interfaces.zhangk.webservice.defaultuse;

import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface Defaultuseservice extends Service {
    public String getInterfaces_DefaultLoginWebServiceImplPortAddress();

    public Interfaces_DefaultLoginWebServiceImpl getInterfaces_DefaultLoginWebServiceImplPort() throws ServiceException;

    public Interfaces_DefaultLoginWebServiceImpl getInterfaces_DefaultLoginWebServiceImplPort(URL portAddress) throws ServiceException;
}
