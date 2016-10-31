package com.zhangkssh.baseframe.common.util.threadlocal;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来出同一线程中资源访问，例如面向切面编程(aop)中目标方法和切面方法之间的数据通信
 * 
 * @author zhangk
 * 
 */
public class ZhangkThreadLocalManager {

	private final static ThreadLocal<ZhangkSession> zhangkLocal = new ThreadLocal<ZhangkSession>();

	private static ExecutorService threadService = Executors
			.newFixedThreadPool(10);

	private ZhangkSession getZhangkSession() {
		// 获取本地线程变量并强制转换为Student类型
		ZhangkSession zhangkSession = (ZhangkSession) zhangkLocal.get();
		// 线程首次执行此方法的时候，studentLocal.get()肯定为null
		if (zhangkSession == null) {
			// 创建一个Student对象，并保存到本地线程变量studentLocal中
			zhangkSession = ZhangkSession.getInstance();
			zhangkLocal.set(zhangkSession);
		}
		return zhangkSession;
	}

	/**
	 * 获取本地线程属性
	 * 
	 * @param attributeName
	 * @return
	 */
	public Object getZhangkAttribute(String attributeName) {
		return getZhangkSession().getAttribute(attributeName);
	}

	/**
	 * 设置本地线程属性
	 * 
	 * @param attributeName
	 * @param value
	 */
	public void setZhangkAttribute(String attributeName, Object value) {
		getZhangkSession().setAttribute(attributeName, value);
	}

	/**
	 * 与其创建子线程共享属性
	 */
	public void putThread(final ZhangkRunnable zhangkRunnable) {
		final Map<String,Object> am = getZhangkSession().getAttributeMap();
		threadService.submit(new Runnable() {
			@Override
			public void run() {
				Map<String,Object> am1 =getZhangkSession().getAttributeMap();
				am1.putAll(am);
				getZhangkSession().setAttributeMap(am1);
				zhangkRunnable.run();
			}
		});
	}

}
