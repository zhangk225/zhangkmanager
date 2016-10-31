package com.zhangkssh.baseframe.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

class DateUtil {

	/**
	 * 日期计算
	 * 
	 * @param date
	 *            要计算的时间
	 * @param num
	 *            要计算的数据 可以为 0
	 * @param sign
	 *            标志位 1按天 2按月 3按年 4按小时 5按分钟 6按秒
	 * @param byTime
	 *            是否为日期(不带时分秒) 否则带时分秒
	 * @return 如果byTime为false 返回
	 */
	Date getTime(Date date, int num, int sign, boolean byTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		Calendar now = new GregorianCalendar(year, month, day, hour, minute,
				second);
		if (byTime) {
			now = new GregorianCalendar(year, month, day);
		} else {
			if (sign == 4) {// 按小时
				now.add(Calendar.HOUR, num);
			} else if (sign == 5) {// 按分钟
				now.add(Calendar.MINUTE, num);
			} else if (sign == 6) {// 按秒
				now.add(Calendar.SECOND, num);
			}
		}
		if (sign == 1) {// 按天
			now.add(Calendar.DATE, num);
		} else if (sign == 2) {// 按月
			now.add(Calendar.MONTH, num);
		} else if (sign == 3) {// 按年
			now.add(Calendar.YEAR, num);
		}

		date = now.getTime();
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
	Date changeLocalDateByZone(Date date, String zone) {
		if (!StringUtils.isEmpty(zone)) {
			TimeZone nowZone = TimeZone.getDefault();
			TimeZone timeZone = TimeZone.getTimeZone(zone);
			long datel = date.getTime();
			long offset = nowZone.getRawOffset() - timeZone.getRawOffset();
			long timeZonel = datel - offset;
			return new Date(timeZonel);
		} else {
			return date;
		}
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
	Date changeDateToDateByZone(Date date, String srcZone, String tarZone) {
		if (date != null) {
			TimeZone srcTimeZone = TimeZone.getDefault();
			TimeZone tarTimeZone = TimeZone.getDefault();
			if (!StringUtils.isEmpty(srcZone)) {
				srcTimeZone = TimeZone.getTimeZone(srcZone);
			}
			if (!StringUtils.isEmpty(tarZone)) {
				tarTimeZone = TimeZone.getTimeZone(tarZone);
			}
			long datel = date.getTime();
			long offset = srcTimeZone.getRawOffset()
					- tarTimeZone.getRawOffset();
			long timeZonel = datel - offset;
			return new Date(timeZonel);
		} else {
			return date;
		}
	}

	/**
	 * 将GMT时间转化为某时区时间
	 * 
	 * @param date
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return
	 */
	Date changeGMTDateByZone(Date date, String zone) {
		TimeZone timeZone = TimeZone.getDefault();
		if (!StringUtils.isEmpty(zone)) {
			timeZone = TimeZone.getTimeZone(zone);
		}
		long datel = date.getTime();
		long offset = timeZone.getRawOffset();
		long timeZonel = datel + offset;
		return new Date(timeZonel);
	}

	/**
	 * 本地时间的string转化为某时区date类型 失败返回null(时区)
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为本地时间
	 * @param zone
	 *            时区 GMT Asia/Shanghai 如果非法则为格林威治时间(GMT) 为空则是本地时间
	 * @return
	 */
	Date localStod(String date, String zone) throws ParseException {
		Date d = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d = sf.parse(date);
		return changeLocalDateByZone(d, zone);

	}

	/**
	 * 本地时间的date转化为某时区String类型 失败返回""
	 * 
	 * @param date
	 *            时间 String类型 yyyy-MM-dd HH:mm:ss 此时间为本地时间
	 * @param zone
	 *            时区 GMT
	 * @return
	 */
	String localDtos(Date date, String zone) {
		String d = "";
		if (date != null) {
			date = changeLocalDateByZone(date, zone);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			d = sf.format(date);
		}
		return d;
	}

}
