package com.zhangkssh.frame.service.tools;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_DateTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_IpAddressTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

	/**
	 * 获取当前标准时间
	 */
	public Date getDateGMT() throws Frame_Exception {
		try {
			return CommonUtil.date_GetDateGMT();
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.getDateGMT() ",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
	}

	/**
	 * 获取当前本地时间
	 */
	public Date getDateLocal() throws Frame_Exception {
		try {
			return CommonUtil.date_GetDateLocal();
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.getDateLocal() ",
					e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
	}

	/**
	 * 获取某时区当前时间
	 * 
	 * @param zone
	 * @return
	 * @throws BaseException
	 */
	public Date getDate(String zone) throws Frame_Exception {
		try {
			return CommonUtil.date_GetDate(zone);
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.getDate("
							+ zone + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
	}
	
	/**
	 * 本地date转化为 本地String
	 * 
	 * @param date
	 * @return
	 */
	public String localDtos(Date date) {
		return CommonUtil.date_LocalDtos(date, "");
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
	public Date changeLocalDateByZone(Date date, String zone) {
		return CommonUtil.date_ChangeLocalDateByZone(date, zone);
	}

	/**
	 * 本地时间的String转化为格林威治时间的Date
	 * 
	 * @param date
	 *            本地时间string类型
	 * @return
	 * @throws BaseException
	 */
	public Date localStodGMT(String date) throws Frame_Exception {
		try {
			return CommonUtil.date_LocalStodGMT(date);
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.localStodGMT("
							+ date + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
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
	public Date localStod(String date, String zone) throws Frame_Exception {
		try {
			return CommonUtil.date_LocalStod(date, zone);
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.localDtosGMT("
							+ date + "," + zone + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
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
	public String localDtos(Date date, String zone) {
		return CommonUtil.date_LocalDtos(date, zone);
	}

	/**
	 * 格林威治时间(GMT)的string转化为本地date类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @return 失败返回null(时区)
	 */
	public Date gmtStodLocal(String date) throws Frame_Exception {
		try {
			return CommonUtil.date_GMTStodLocal(date);
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.gmtStodLocal("
							+ date + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
	}

	/**
	 * 格林威治时间(GMT)的date转化为本地String类型
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为格林威治时间
	 * @return 失败返回""
	 */
	public String gmtDtosLocal(Date date) {
		return CommonUtil.date_GMTDtosLocal(date);
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
	public Date gmtStod(String date, String zone) throws Frame_Exception {
		try {
			return CommonUtil.date_GMTStod(date, zone);
		} catch (ParseException e) {
			s_logger.error(
					"date parse error(ParseException) in method Frame_DateTools.gmtStod("
							+ date + "," + zone + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_PARSEEXCEPTION1);
		}
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
	public String gmtDtos(Date date, String zone) {
		return CommonUtil.date_GMTDtos(date, zone);
	}


	/**
	 * 将GMT时间转化为某时区时间
	 * 
	 * @param date
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return
	 */
	public Date changeGMTDateByZone(Date date, String zone) {
		return CommonUtil.date_ChangeGMTDateByZone(date, zone);
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
	public Date changeDateToDateByZone(Date date, String srcZone,
			String tarZone) {
		return CommonUtil.date_ChangeDateToDateByZone(date, srcZone, tarZone);
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
	public Date getTime(Date date, int num, int sign, boolean byTime) {
		return CommonUtil.date_GetTime(date, num, sign, byTime);
	}
}
