package com.frame.service.tools;

import java.util.Date;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.test.SpringTestBase;

public class Frame_DateToolsTest extends SpringTestBase {

	private String dates = "2001-01-02 11:22:33";

	private Date date = null;

	private static final String TIMEZONE_JAPAN = "JST";// +9:00 日本(标准时间为准)

	private static final String TIMEZONE_EST = "EST";// -05:00  东部标准时间

	@Before
	public void init() {
		date = dateTools.localStod(dates, "");
	}

	@Test
	public void testDate() {
		Date date1 = dateTools.getTime(date, 1, 1, true);
		String dates1 = dateTools.localDtos(date1);
		Assert.assertEquals("2001-01-03 00:00:00", dates1);
		Date date2 = dateTools.getTime(date, -1, 2, false);
		String dates2 = dateTools.localDtos(date2, TIMEZONE_JAPAN);
		Assert.assertEquals("2000-12-02 12:22:33", dates2);
		Date date3 = dateTools.getTime(date, -10, 3, false);
		String dates3 = dateTools.gmtDtosLocal(date3);
		Assert.assertEquals("1991-01-02 19:22:33", dates3);
		Date date4 = dateTools.getTime(date, -10, 0, false);
		String dates4 = dateTools.gmtDtos(date4, TIMEZONE_EST);
		Assert.assertEquals("2001-01-02 06:22:33", dates4);
		String date5=dateTools.localDtos(null);
		Assert.assertEquals("", date5);
		Date date6 = dateTools.changeDateToDateByZone(date, TIMEZONE_JAPAN, TIMEZONE_EST);
		String dates6=dateTools.localDtos(date6);
		Assert.assertEquals("2001-01-01 21:22:33", dates6);
		Date date7 = dateTools.changeDateToDateByZone(date, "", TIMEZONE_EST);
		String dates7=dateTools.localDtos(date7);
		Assert.assertEquals("2001-01-01 22:22:33", dates7);
		Date date8 = dateTools.changeDateToDateByZone(date, "", "");
		String dates8=dateTools.localDtos(date8);
		Assert.assertEquals("2001-01-02 11:22:33", dates8);
		Date date9 = dateTools.changeDateToDateByZone(null, "", "");
		Assert.assertNull(date9);
	}
}
