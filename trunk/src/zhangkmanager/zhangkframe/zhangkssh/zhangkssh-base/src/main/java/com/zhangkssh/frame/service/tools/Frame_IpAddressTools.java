package com.zhangkssh.frame.service.tools;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.externalservice.commonobject.Constants;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_IpAddressTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_IpAddressTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	@Autowired
	private Frame_PropertiesTools propertiesTools;

	/**
	 * 验证ip地址是否正确(ipv4或者ipv6).
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address, false otherwise
	 */
	public boolean isValid(String address) {
		return CommonUtil.ipAddress_IsValid(address);
	}

	/**
	 * 验证ipv4的地址是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	public boolean isValidIPv4(String address) {
		return CommonUtil.ipAddress_IsValidIPv4(address);
	}

	/**
	 * 验证ipv6的地址是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	public boolean isValidIPv6(String address) {
		return CommonUtil.ipAddress_IsValidIPv6(address);
	}

	/**
	 * 验证 IPv4 or IPv6 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public boolean isValidWithNetMask(String address) {
		return CommonUtil.ipAddress_IsValidWithNetMask(address);
	}

	/**
	 * 验证 IPv4 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public boolean isValidIPv4WithNetmask(String address) {
		return CommonUtil.ipAddress_IsValidIPv4WithNetmask(address);
	}

	/**
	 * 验证 IPv6 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public boolean isValidIPv6WithNetmask(String address) {
		return CommonUtil.ipAddress_IsValidIPv6WithNetmask(address);
	}

	/**
	 * 
	 * @Title: linux系统中通过网卡名获取ipv4地址
	 * @param String
	 *            networkCardName 网卡名称
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String getLinuxIPV4ByNetworkCardName(String networkCardName)
			throws Frame_Exception {
		try {
			return CommonUtil
					.ipAddress_GetLinuxIPV4ByNetworkCardName(networkCardName);
		} catch (SocketException e) {
			s_logger.error(
					"ipAddress get NetworkCardName error (SocketException) in method Frame_IpAddressTools.ipAddress_GetLinuxIPV4ByNetworkCardName("
							+ networkCardName + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_SOCKETEXCEPTION4);
		}
	}

	/**
	 * 
	 * @Title: linux系统中通过网卡名获取ipv4地址
	 * @Description: 拿到网卡“eth0”的IPV４的地址
	 * @param String
	 *            networkCardName 网卡名称
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String getLinuxIPV4ByNetworkCardName() throws Frame_Exception {
		String networkCardName = propertiesTools.getProperties().getProperty(
				Constants.NETWORKCARD_NAME);
		return getLinuxIPV4ByNetworkCardName(networkCardName);
	}

	/**
	 * 多网卡的情况下，获取不同网卡地址
	 */
	public Set<String> getAllLocalIp() throws Frame_Exception {
		try {
			return CommonUtil.ipAddress_GetAllLocalIp();
		} catch (SocketException e) {
			s_logger.error(
					"ipAddress get AllLocalIp error (SocketException) in method Frame_IpAddressTools.getAllLocalIp() ",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_SOCKETEXCEPTION4);
		}
	}

	/**
	 * 获取本机ip
	 * 
	 * @return
	 */
	public String getLocalip() throws Frame_Exception {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();//
			if (ip.equals("localhost") || ip.equals("127.0.0.1")
					|| !ipAddress_isWindowsOS()) {
				ip = getLinuxIPV4ByNetworkCardName();
			}
		} catch (UnknownHostException e) {
			s_logger.error(
					"get localip error (UnknownHostException) in method Frame_IpAddressTools.getLocalip()",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_UNKNOWNHOSTEXCEPTION5);
		}
		return ip == null ? "" : ip;
	}

	/**
	 * mac地址标准化
	 * 
	 * @param mac
	 * @return
	 */
	public String macStandardization(String mac) {
		return CommonUtil.ipAddress_MacStandardization(mac);
	}

	/**
	 * Long型ip转化String
	 * 
	 * @param mac
	 * @return
	 */
	public String ip4LongToString(long ipaddress) {
		return CommonUtil.ipAddress_Ip4LongToString(ipaddress);
	}

	/**
	 * 判断当前系统是否是windows
	 * 
	 * @return
	 */
	public boolean ipAddress_isWindowsOS() {
		return CommonUtil.ipAddress_IsWindowsOS();
	}
}
