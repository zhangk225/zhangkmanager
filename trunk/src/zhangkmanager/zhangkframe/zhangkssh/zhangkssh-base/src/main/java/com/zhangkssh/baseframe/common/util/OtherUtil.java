package com.zhangkssh.baseframe.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

class OtherUtil {

	/**
	 * 判断str1中包含str2的个数
	 * 
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	int countStr(String str1, String str2) {
		if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
			return 0;
		}
		int i = 0;
		while (str1.contains(str2)) {
			i++;
			str1 = str1.substring(str1.indexOf(str2) + str2.length(),
					str1.lastIndexOf(str2) + str2.length());
		}
		return i;
	}

	/**
	 * 将map的value转化为list
	 * 
	 * @param map
	 * @return
	 */
	<M, T> List<T> mapValue2List(Map<M, T> map) {
		List<T> list = new ArrayList<T>();
		if (map != null && map.size() > 0) {
			Iterator<Entry<M, T>> iter = map.entrySet().iterator(); // 获得map的Iterator
			while (iter.hasNext()) {
				Entry<M, T> entry = (Entry<M, T>) iter.next();
				list.add(entry.getValue());
			}
		}
		return list;
	}

	/**
	 * 转码
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String encode(String text) throws UnsupportedEncodingException {
		return java.net.URLEncoder.encode(text, "utf-8");
	}
	
	/**
	 * 解码
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	String decode(String text) throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(text, "utf-8");
	}

}
