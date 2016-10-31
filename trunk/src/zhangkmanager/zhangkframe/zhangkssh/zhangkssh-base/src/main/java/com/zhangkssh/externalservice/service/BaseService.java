package com.zhangkssh.externalservice.service;

import java.util.List;
import java.util.Map;

public interface BaseService {

	/**
	 * 判断str1中包含str2的个数
	 * 
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	public int other_CountStr(String str1, String str2);
	
	/**
	 * 将map的value转化为list
	 * 
	 * @param map
	 * @return
	 */
	public <M, T> List<T> other_MapValue2List(Map<M, T> map);

}
