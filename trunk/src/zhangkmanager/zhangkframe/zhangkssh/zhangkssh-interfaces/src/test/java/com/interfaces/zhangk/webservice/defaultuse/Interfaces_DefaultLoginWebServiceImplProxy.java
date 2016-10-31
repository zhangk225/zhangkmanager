package com.interfaces.zhangk.webservice.defaultuse;

public class Interfaces_DefaultLoginWebServiceImplProxy implements Interfaces_DefaultLoginWebServiceImpl {
  private String _endpoint = null;
  private Interfaces_DefaultLoginWebServiceImpl interfaces_DefaultLoginWebServiceImpl = null;
  
  public Interfaces_DefaultLoginWebServiceImplProxy() {
    _initInterfaces_DefaultLoginWebServiceImplProxy();
  }
  
  public Interfaces_DefaultLoginWebServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initInterfaces_DefaultLoginWebServiceImplProxy();
  }
  
  private void _initInterfaces_DefaultLoginWebServiceImplProxy() {
    try {
      interfaces_DefaultLoginWebServiceImpl = (new DefaultuseserviceLocator()).getInterfaces_DefaultLoginWebServiceImplPort();
      if (interfaces_DefaultLoginWebServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)interfaces_DefaultLoginWebServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)interfaces_DefaultLoginWebServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (interfaces_DefaultLoginWebServiceImpl != null)
      ((javax.xml.rpc.Stub)interfaces_DefaultLoginWebServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Interfaces_DefaultLoginWebServiceImpl getInterfaces_DefaultLoginWebServiceImpl() {
    if (interfaces_DefaultLoginWebServiceImpl == null)
      _initInterfaces_DefaultLoginWebServiceImplProxy();
    return interfaces_DefaultLoginWebServiceImpl;
  }
  
  public java.lang.String invoke(java.lang.String methodName, java.lang.String xmlPrts) throws java.rmi.RemoteException{
    if (interfaces_DefaultLoginWebServiceImpl == null)
      _initInterfaces_DefaultLoginWebServiceImplProxy();
    return interfaces_DefaultLoginWebServiceImpl.invoke(methodName, xmlPrts);
  }
  
  
}