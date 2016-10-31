package com.zhangkssh.baseframe.common.util.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里使用单例模式，保证此对象在同一线程中之有一个
 * (ZhangkThreadLocalManager会对其进行处理，保证单例可在各个线程中创建)
 * 
 * @author zhangk
 * 
 */
public class ZhangkSession {

	private static ZhangkSession zhangkSession;

	private ZhangkSession() {
	}

	static ZhangkSession getInstance() {
		if (zhangkSession == null) {
			zhangkSession = new ZhangkSession();
		}
		return zhangkSession;
	}

	private Map<String, Object> attributeMap = new HashMap<String, Object>();

	/**
	 * 获取属性
	 * 
	 * @param attributeName
	 * @return
	 */
	Object getAttribute(String attributeName) {
		Object o = attributeMap.get(attributeName);
		return o;
	}

	/**
	 * 设置属性
	 * 
	 * @param attributeName
	 * @param Value
	 */
	void setAttribute(String attributeName, Object value) {
		attributeMap.put(attributeName, value);
	}

	
	/**
	 * 向子线程传值时用到
	 * @return
	 */
	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	/**
	 * 向子线程传值时用到
	 * @param attributeMap
	 */
	public void setAttributeMap(Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}
	
}
