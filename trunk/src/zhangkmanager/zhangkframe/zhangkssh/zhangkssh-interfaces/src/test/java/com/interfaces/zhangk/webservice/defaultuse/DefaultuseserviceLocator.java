/**
 * DefaultuseserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.interfaces.zhangk.webservice.defaultuse;

public class DefaultuseserviceLocator extends org.apache.axis.client.Service implements Defaultuseservice {

    public DefaultuseserviceLocator() {
    }


    public DefaultuseserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DefaultuseserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Interfaces_DefaultLoginWebServiceImplPort
    private java.lang.String Interfaces_DefaultLoginWebServiceImplPort_address = "http://192.168.0.103:8095/zhangkservice/defaultuse";

    public java.lang.String getInterfaces_DefaultLoginWebServiceImplPortAddress() {
        return Interfaces_DefaultLoginWebServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Interfaces_DefaultLoginWebServiceImplPortWSDDServiceName = "Interfaces_DefaultLoginWebServiceImplPort";

    public java.lang.String getInterfaces_DefaultLoginWebServiceImplPortWSDDServiceName() {
        return Interfaces_DefaultLoginWebServiceImplPortWSDDServiceName;
    }

    public void setInterfaces_DefaultLoginWebServiceImplPortWSDDServiceName(java.lang.String name) {
        Interfaces_DefaultLoginWebServiceImplPortWSDDServiceName = name;
    }

    public Interfaces_DefaultLoginWebServiceImpl getInterfaces_DefaultLoginWebServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Interfaces_DefaultLoginWebServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInterfaces_DefaultLoginWebServiceImplPort(endpoint);
    }

    public Interfaces_DefaultLoginWebServiceImpl getInterfaces_DefaultLoginWebServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            Interfaces_DefaultLoginWebServiceImplPortBindingStub _stub = new Interfaces_DefaultLoginWebServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getInterfaces_DefaultLoginWebServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInterfaces_DefaultLoginWebServiceImplPortEndpointAddress(java.lang.String address) {
        Interfaces_DefaultLoginWebServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (Interfaces_DefaultLoginWebServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                Interfaces_DefaultLoginWebServiceImplPortBindingStub _stub = new Interfaces_DefaultLoginWebServiceImplPortBindingStub(new java.net.URL(Interfaces_DefaultLoginWebServiceImplPort_address), this);
                _stub.setPortName(getInterfaces_DefaultLoginWebServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Interfaces_DefaultLoginWebServiceImplPort".equals(inputPortName)) {
            return getInterfaces_DefaultLoginWebServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://zhangk.interfaces.com", "defaultuseservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://zhangk.interfaces.com", "Interfaces_DefaultLoginWebServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Interfaces_DefaultLoginWebServiceImplPort".equals(portName)) {
            setInterfaces_DefaultLoginWebServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
