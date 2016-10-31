package com.zhangkssh.baseframe.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBException;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;

import com.zhangkssh.baseframe.common.util.encrypt.Encrypt;
import com.zhangkssh.baseframe.common.util.http.HttpResponse;
import com.zhangkssh.baseframe.common.util.http.HttpUtil;
import com.zhangkssh.baseframe.common.util.job.JobBean;
import com.zhangkssh.baseframe.common.util.job.JobInterface;
import com.zhangkssh.baseframe.common.util.job.JobService;
import com.zhangkssh.baseframe.common.util.threadlocal.ZhangkThreadLocalManager;
import com.zhangkssh.baseframe.common.util.xml.XmlUtil;
import com.zhangkssh.externalservice.commonobject.Constants;

public class CommonUtil {

	private static DateUtil dateUtil = new DateUtil();

	private static IpAddressUtil ipAddressUtil = new IpAddressUtil();

	private static PropertiesUtil propertiesUtil = new PropertiesUtil();

	private static OtherUtil otherUtil = new OtherUtil();

	private static ExcellUtil excellUtil = new ExcellUtil();

	private static HttpUtil httpUtil = new HttpUtil();

	private static Encrypt encrypt = new Encrypt();

	private static XmlUtil xmlUtil = new XmlUtil();

	private static JobService jobService = new JobService();

	private static ZhangkThreadLocalManager zhangkThreadLocalManager = new ZhangkThreadLocalManager();

	private static JavaRemote javaRemote = new JavaRemote();

	/**
	 * 验证ip地址是否正确(ipv4或者ipv6).
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address, false otherwise
	 */
	public static boolean ipAddress_IsValid(String address) {
		return ipAddressUtil.isValid(address);
	}

	/**
	 * 验证ipv4的地址是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	public static boolean ipAddress_IsValidIPv4(String address) {
		return ipAddressUtil.isValidIPv4(address);
	}

	/**
	 * 验证ipv6的地址是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid IPv4 address, false otherwise
	 */
	public static boolean ipAddress_IsValidIPv6(String address) {
		return ipAddressUtil.isValidIPv6(address);
	}

	/**
	 * 验证 IPv4 or IPv6 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public static boolean ipAddress_IsValidWithNetMask(String address) {
		return ipAddressUtil.isValidWithNetMask(address);
	}

	/**
	 * 验证 IPv4 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public static boolean ipAddress_IsValidIPv4WithNetmask(String address) {
		return ipAddressUtil.isValidIPv4WithNetmask(address);
	}

	/**
	 * 验证 IPv6 掩码是否正确.
	 * 
	 * @param address
	 *            the IP address as a String.
	 * 
	 * @return true if a valid address with netmask, false otherwise
	 */
	public static boolean ipAddress_IsValidIPv6WithNetmask(String address) {
		return ipAddressUtil.isValidIPv6WithNetmask(address);
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
	public static String ipAddress_GetLinuxIPV4ByNetworkCardName(
			String networkCardName) throws SocketException {
		return ipAddressUtil.getLinuxIPV4ByNetworkCardName(networkCardName);
	}

	/**
	 * 多网卡的情况下，获取不同网卡地址
	 */
	public static Set<String> ipAddress_GetAllLocalIp() throws SocketException {
		return ipAddressUtil.getAllLocalIp();
	}

	/**
	 * mac地址标准化
	 * 
	 * @param mac
	 * @return
	 */
	public static String ipAddress_MacStandardization(String mac) {
		return ipAddressUtil.macStandardization(mac);
	}

	/**
	 * Long型ip转化String
	 * 
	 * @param mac
	 * @return
	 */
	public static String ipAddress_Ip4LongToString(long ipaddress) {
		return ipAddressUtil.ip4LongToString(ipaddress);
	}

	/**
	 * 判断当前系统是否是windows
	 * 
	 * @return
	 */
	public static boolean ipAddress_IsWindowsOS() {
		return ipAddressUtil.isWindowsOS();
	}

	/**
	 * 通过名称获取资源文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties properties_GetProperties(String fileName)
			throws IOException {
		return propertiesUtil.getProperties(fileName);
	}

	/**
	 * 得到数据库资源文件
	 * 
	 * @return
	 */
	public static Properties properties_GetDBProperties() throws IOException {
		String fileName = Constants.DBFILE;
		return properties_GetProperties(fileName);
	}

	/**
	 * 判断str1中包含str2的个数
	 * 
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	public static int other_CountStr(String str1, String str2) {
		return otherUtil.countStr(str1, str2);
	}

	/**
	 * 将map的value转化为list
	 * 
	 * @param map
	 * @return
	 */
	public static <M, T> List<T> other_MapValue2List(Map<M, T> map) {
		return otherUtil.mapValue2List(map);
	}

	/**
	 * 转码
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String other_Encode(String text)
			throws UnsupportedEncodingException {
		return otherUtil.encode(text);
	}

	/**
	 * 解码
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String other_Decode(String text)
			throws UnsupportedEncodingException {
		return otherUtil.decode(text);
	}

	/**
	 * 通过SSL发送https请求，并获得返回
	 * 
	 * @param urlStr
	 *            - url字符串
	 * @param type
	 *            - 编码类型
	 * @param requestMethod
	 *            - http请求类型]
	 * @param json
	 *            - json信息
	 * @param cookie
	 *            - cookie字符串
	 * @return HttpResponse，包含response
	 *         code，内容和cookie信息，注意如果未能得到信息，code将返回-1，内容和cookie将返回空字符串
	 */
	public static HttpResponse http_SendSSLWithJson(String urlStr, String type,
			String requestMethod, String json, String cookie) {
		return httpUtil.sendSSLWithJson(urlStr, type, requestMethod, json,
				cookie);
	}

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String http_GetContent(String url)
			throws UnsupportedEncodingException {
		return httpUtil.getContent(url, Constants.UTF8);
	}

	/**
	 * 按基础算法加密
	 * 
	 * @param strSrc
	 *            要加密的字符串
	 * @param encName
	 *            算法类型 MD5 SHA-1 SHA-256
	 * @return
	 */
	public static String encrypt_EncryptByBaseArith(String strSrc,
			String encName) {
		return encrypt.encrypt(strSrc, encName);
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 *            要计算的时间
	 * @param num
	 *            要计算的数据 可以为 0
	 * @param sign
	 *            标志位 1按天 2按月 3按年
	 * @param byTime
	 *            是否为日期(不带时分秒) 否则带时分秒
	 * @return 如果byTime为false 返回
	 */
	public static Date date_GetTime(Date date, int num, int sign, boolean byTime) {
		return dateUtil.getTime(date, num, sign, byTime);
	}

	/**
	 * 本地时间的String转化为格林威治时间的Date
	 * 
	 * @param date
	 *            本地时间string类型
	 * @return
	 * @throws ParseException
	 */
	public static Date date_LocalStodGMT(String date) throws ParseException {
		return date_LocalStod(date, "GMT");
	}

	/**
	 * 本地时间的Date转化为格林威治时间的String
	 * 
	 * @param date
	 *            本地时间的
	 * @return
	 */
	public static String date_LocalDtosGMT(Date date) {
		return date_LocalDtos(date, "GMT");
	}

	/**
	 * 本地时间的string转化为某时区date类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为本地时间
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return 失败返回null(时区)
	 */
	public static Date date_LocalStod(String date, String zone)
			throws ParseException {
		return dateUtil.localStod(date, zone);
	}

	/**
	 * 本地时间的date转化为某时区String类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为本地时间
	 * @param zone
	 *            时区 GMT
	 * @return 失败返回""
	 */
	public static String date_LocalDtos(Date date, String zone) {
		return dateUtil.localDtos(date, zone);
	}

	/**
	 * 格林威治时间(GMT)的string转化为本地date类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @return 失败返回null(时区)
	 */
	public static Date date_GMTStodLocal(String date) throws ParseException {
		return date_GMTStod(date, "");
	}

	/**
	 * 格林威治时间(GMT)的date转化为本地String类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @return 失败返回""
	 */
	public static String date_GMTDtosLocal(Date date) {
		return date_GMTDtos(date, "");
	}

	/**
	 * 格林威治时间(GMT)的string转化为某时区date类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return 失败返回null(时区)
	 */
	public static Date date_GMTStod(String date, String zone)
			throws ParseException {
		Date d = date_LocalStod(date, "");
		return dateUtil.changeGMTDateByZone(d, zone);
	}

	/**
	 * 格林威治时间(GMT)的date转化为某时区String类型 失败返回""
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @param zone
	 *            时区 GMT
	 * @return
	 */
	public static String date_GMTDtos(Date date, String zone) {
		Date d = dateUtil.changeGMTDateByZone(date, zone);
		return date_LocalDtos(d, "");
	}

	/**
	 * 获取当前标准时间
	 */
	public static Date date_GetDateGMT() throws ParseException {
		return date_GetDate("GMT");
	}

	/**
	 * 获取当前本地时间
	 */
	public static Date date_GetDateLocal() throws ParseException {
		return date_GetDate("");
	}

	/**
	 * 通过时区获取当前时间
	 * 
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date date_GetDate(String zone) throws ParseException {
		Date date = new Date();
		if (!StringUtils.isEmpty(zone)) {
			date = date_LocalStod(date_LocalDtos(date, zone), "");
		}
		return date;
	}

	/**
	 * 将本地时间转化为 某时区时间
	 * 
	 * @param date
	 *            本地时间
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return
	 */
	public static Date date_ChangeLocalDateByZone(Date date, String zone) {
		return dateUtil.changeLocalDateByZone(date, zone);
	}

	/**
	 * 将GMT时间转化为某时区时间
	 * 
	 * @param date
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return
	 */
	public static Date date_ChangeGMTDateByZone(Date date, String zone) {
		return dateUtil.changeGMTDateByZone(date, zone);
	}

	/**
	 * 将某时区的时间转换成目标时区时间
	 * 
	 * @param date
	 *            源时区时间
	 * @param srcZone
	 *            源时区
	 * @param tarZone
	 *            目标时区
	 * @return
	 */
	public static Date date_ChangeDateToDateByZone(Date date, String srcZone,
			String tarZone) {
		return dateUtil.changeDateToDateByZone(date, srcZone, tarZone);
	}

	/**
	 * 根据指定数据生成excell文件
	 * 
	 * @param filename
	 *            文件名(完整路径)
	 * @param sheetname
	 *            sheet名称
	 * @param filednames
	 *            字段名称组
	 * @param datas
	 *            数据组
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static File excell_FromList(String filename, String sheetname,
			String[] filednames, List<String[]> datas) throws IOException,
			RowsExceededException, WriteException {
		return excellUtil.fromList(filename, sheetname, filednames, datas);
	}

	/**
	 * 将对象转化成xmlString
	 * 
	 * @param o
	 * @return
	 * @throws JAXBException
	 */
	public static String xml_ObjectToString(Object o) throws JAXBException,
			IOException {
		return xmlUtil.objectToString(o);

	}

	/**
	 * 将xmlString转化成对象
	 * 
	 * @param xmlStr
	 * @param c
	 * @return
	 * @throws JAXBException
	 */
	public static Object xml_StringToObject(String xmlStr,
			Class<? extends Object> c) throws JAXBException {
		return xmlUtil.StringToObject(xmlStr, c);
	}

	/**
	 * 开始一个异步任务
	 * 
	 * @param jobInterface
	 * @param jobBean
	 * @param service
	 */
	public static void job_StartJob(JobInterface jobInterface, JobBean jobBean) {
		jobService.startJob(jobInterface, jobBean);
	}

	/**
	 * 通过任务主键获取任务信息
	 * 
	 * @param jobId
	 *            任务主键
	 * @return
	 */
	public static String job_GetJobMessage(String jobId) {
		return jobService.getJobMessage(jobId);
	}

	/**
	 * 获取本地线程中某一属性值
	 * 
	 * @param attributeName
	 * @return
	 */
	public static Object localThread_GetAttribute(String attributeName) {
		return zhangkThreadLocalManager.getZhangkAttribute(attributeName);
	}

	/**
	 * 获取本地线程某一属性值
	 * 
	 * @param attributeName
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Object localThread_GetAttribute(String attributeName,
			Object defaultValue) {
		Object o = localThread_GetAttribute(attributeName);
		if (o == null) {
			return defaultValue;
		}
		return o;
	}

	/**
	 * 将属性值放入本地线程
	 * 
	 * @param attributeName
	 * @param value
	 */
	public static void localThread_SetAttribute(String attributeName,
			Object value) {
		zhangkThreadLocalManager.setZhangkAttribute(attributeName, value);
	}
	
	/**
	 * 将本地线程中的某一属性置为null
	 * 
	 * @param attributeName
	 */
	public static void localThread_removeAttribute(String attributeName) {
		zhangkThreadLocalManager.setZhangkAttribute(attributeName, null);
	}

	/**
	 * 远程执行linux命令
	 * 
	 * @param ip
	 * @param user
	 * @param password
	 * @param command
	 * @return
	 * @throws IOException
	 */
	public static String javaRemote_Remotesh(String ip, String user, String password,
			String command) throws IOException{
		return javaRemote.remotesh(ip, user, password, command);
	}

}
