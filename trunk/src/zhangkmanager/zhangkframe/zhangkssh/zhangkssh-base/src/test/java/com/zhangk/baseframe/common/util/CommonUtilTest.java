package com.zhangk.baseframe.common.util;

import junit.framework.Assert;

import org.junit.Test;

import com.zhangkssh.baseframe.common.util.CommonUtil;

public class CommonUtilTest {

	@Test
	public void testIpAddressIsValid() {
		String ip = "aaaaaaa";
		Assert.assertEquals(false, CommonUtil.ipAddress_IsValid(ip));
	}
}