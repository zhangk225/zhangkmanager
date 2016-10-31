package com.zhangkssh.baseframe.common.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

class IpAddressUtil {

	private static final Logger s_logger = Logger.getLogger(IpAddressUtil.class
			.getName());

	/**
	 * Validate the given IPv4 or IPv6 address.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address, false otherwise
	 */
	boolean isValid(String address) {
		return isValidIPv4(address) || isValidIPv6(address);
	}

	/**
	 * Validate the given IPv4 or IPv6 address and netmask.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	boolean isValidWithNetMask(String address) {
		return isValidIPv4WithNetmask(address)
				|| isValidIPv6WithNetmask(address);
	}

	/**
	 * Validate the given IPv4 address.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	boolean isValidIPv4(String address) {
		if (address.length() == 0) {
			return false;
		}

		int octet;
		int octets = 0;

		String temp = address + ".";

		int pos;
		int start = 0;
		while (start < temp.length()
				&& (pos = temp.indexOf('.', start)) > start) {
			if (octets == 4) {
				return false;
			}
			try {
				octet = Integer.parseInt(temp.substring(start, pos));
			} catch (NumberFormatException ex) {
				return false;
			}
			if (octet < 0 || octet > 255) {
				return false;
			}
			start = pos + 1;
			octets++;
		}

		return octets == 4;
	}

	/**
	 * 验证 IPv4 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	boolean isValidIPv4WithNetmask(String address) {
		int index = address.indexOf("/");
		String mask = address.substring(index + 1);

		return (index > 0) && isValidIPv4(address.substring(0, index))
				&& (isValidIPv4(mask) || isMaskValue(mask, 32));
	}

	/**
	 * 验证 IPv6 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	boolean isValidIPv6WithNetmask(String address) {
		int index = address.indexOf("/");
		String mask = address.substring(index + 1);

		return (index > 0)
				&& (isValidIPv6(address.substring(0, index)) && (isValidIPv6(mask) || isMaskValue(
						mask, 128)));
	}

	private boolean isMaskValue(String component, int size) {
		try {
			int value = Integer.parseInt(component);

			return value >= 0 && value <= size;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Validate the given IPv6 address.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	boolean isValidIPv6(String address) {
		if (address.length() == 0) {
			return false;
		}

		int octet;
		int octets = 0;

		String temp = address + ":";
		boolean doubleColonFound = false;
		int pos;
		int start = 0;
		while (start < temp.length()
				&& (pos = temp.indexOf(':', start)) >= start) {
			if (octets == 8) {
				return false;
			}

			if (start != pos) {
				String value = temp.substring(start, pos);

				if (pos == (temp.length() - 1) && value.indexOf('.') > 0) {
					if (!isValidIPv4(value)) {
						return false;
					}

					octets++; // add an extra one as address covers 2 words.
				} else {
					try {
						octet = Integer
								.parseInt(temp.substring(start, pos), 16);
					} catch (NumberFormatException ex) {
						return false;
					}
					if (octet < 0 || octet > 0xffff) {
						return false;
					}
				}
			} else {
				if (pos != 1 && pos != temp.length() - 1 && doubleColonFound) {
					return false;
				}
				doubleColonFound = true;
			}
			start = pos + 1;
			octets++;
		}

		return octets == 8 || doubleColonFound;
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
	String getLinuxIPV4ByNetworkCardName(String networkCardName)
			throws SocketException {
		Enumeration<NetworkInterface> netInterfaces = null;
		String ip = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				if (ni.getName().equals(networkCardName)) {
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						InetAddress inetAddress = ips.nextElement();
						if (inetAddress instanceof Inet6Address) {
							continue;
						} else {
							ip = inetAddress.getHostAddress();
						}
					}
					break;
				}
			}
		} catch (SocketException e) {
			s_logger.error(
					"get localip error (SocketException) IpAddressUtil.getLinuxIPV4ByNetworkCardName("
							+ networkCardName + ") ", e);
			throw e;
		}
		return ip;
	}

	/**
	 * 多网卡的情况下，获取不同网卡地址
	 */
	Set<String> getAllLocalIp() throws SocketException {
		Set<String> localps = new HashSet<String>();
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress inetAddress = ips.nextElement();
					if (inetAddress instanceof Inet6Address) {
						continue;
					} else {
						localps.add(inetAddress.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			s_logger.error(
					"get localip error (SocketException) IpAddressUtil.getAllLocalIp() ",
					e);
			throw e;
		}
		return localps;
	}

	/**
	 * 判断当前系统是否是windows
	 * 
	 * @return
	 */
	boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * ipv4字符串转化为Long型
	 * 
	 * @param ipaddress
	 * @return
	 */
	long ipStrToLong(String ipaddress) {
		long[] ip = new long[4];
		int i = 0;
		for (String ipStr : ipaddress.split("\\.")) {
			ip[i] = Long.parseLong(ipStr);
			i++;
		}
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * Long 转 IP(String) ip long to String
	 * */

	/**
	 * Long 转 IPV4(String) ip long to String
	 * 
	 * @param ipaddress
	 * @return
	 */
	String ip4LongToString(long ipaddress) {
		String ip4 = String.valueOf((ipaddress >>> 24));
		String ip3 = String.valueOf((ipaddress & 0x00FFFFFF) >>> 16);
		String ip2 = String.valueOf((ipaddress & 0x0000FFFF) >>> 8);
		String ip1 = String.valueOf((ipaddress & 0x000000FF));
		return ip4 + "." + ip3 + "." + ip2 + "." + ip1;
	}

	/**
	 * mac地址标准化
	 * 
	 * @param mac
	 * @return
	 */
	String macStandardization(String mac) {
		String macs = mac;
		if (!StringUtils.isEmpty(macs)) {

			if (macs.length() < 12) {
				String zero = "";
				int a = 12 - macs.length();
				for (int i = 0; i < a; i++) {
					zero += "0";
				}
				macs = zero + macs;
			}
			return macs.substring(0, 2) + ":" + macs.substring(2, 4) + ":"
					+ macs.substring(4, 6) + ":" + macs.substring(6, 8) + ":"
					+ macs.substring(8, 10) + ":" + macs.substring(10, 12);
		} else {
			return "00:00:00:00:00:00";
		}
	}
}
