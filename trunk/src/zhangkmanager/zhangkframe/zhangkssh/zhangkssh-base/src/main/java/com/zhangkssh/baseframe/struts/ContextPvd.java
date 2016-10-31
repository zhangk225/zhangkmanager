package com.zhangkssh.baseframe.struts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * 必须在有web请求的项目中使用否则抛错
 * @author zhangk
 *
 */
public class ContextPvd {

	/**
	 * 获得系统绝对路径 如：F:\webapps\CmsSys
	 * 
	 * @param path
	 *            可以传入空串
	 * @return
	 */
	public static String getAppRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}

	/**
	 * 获得应用绝对根路径
	 * 
	 * @return
	 */
	public static String getAppRoot() {
		return getAppRealPath("/");
	}

	/**
	 * 获得系统根路径 如：/CmsSys
	 * 
	 * @return
	 */
	public static String getAppCxtPath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	/**
	 * 获得应用端口号
	 * 
	 * @return
	 */
	public static int getServerPort() {
		return ServletActionContext.getRequest().getServerPort();
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public static void logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 获得response
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 从Request的Attribute中获取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getRequestAttr(String key) {
		return ServletActionContext.getRequest().getAttribute(key);
	}

	/**
	 * 给Request的Attribute中赋值
	 * 
	 * @param key
	 * @param value
	 */
	public static void setRequestAttr(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	/**
	 * 从SESSION中获得值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(String key) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request == null) {
			return null;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}
	}

	/**
	 * 给session赋值
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSessionAttr(String key, Object value) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request != null) {
			HttpSession session =request.getSession();
			session.setAttribute(key, value);
		}
	}

	/**
	 * 移除session中的属性
	 * 
	 * @param key
	 */
	public static void removeAttribute(String key) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute(key);
	}

	/**
	 * 获取Application属性
	 * 
	 * @param key
	 * @return
	 */
	public static Object getApplicationAttr(String key) {
		return ServletActionContext.getServletContext().getAttribute(key);
	}

	/**
	 * 设置Application属性
	 * 
	 * @param key
	 * @param value
	 */
	public static void setApplicationAttr(String key, Object value) {
		ServletActionContext.getServletContext().setAttribute(key, value);
	}

	/**
	 * 获得sessionId
	 * 
	 * @param isCreate
	 *            如果session不存在是否创建
	 * @return
	 */
	public static String getSessionId(boolean isCreate) {
		HttpSession session = ServletActionContext.getRequest().getSession(
				isCreate);
		if (session == null) {
			return null;
		} else {
			return session.getId();
		}
	}

	/**
	 * 获得访问者IP
	 * 
	 * @return
	 */
	public static String getRemoteIp() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	/**
	 * 获得访问者PORT
	 * 
	 * @return
	 */
	public static int getRemotePort() {
		return ServletActionContext.getRequest().getRemotePort();
	}

	/**
	 * 获得访问者URL
	 * 
	 * @return
	 */
	public static String getRequestURL() {
		return ServletActionContext.getRequest().getRequestURL().toString();
	}

	/**
	 * 获得访问者浏览器
	 * 
	 * @return
	 */
	public static String getRequestBrowser() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 1) {
			return agents[1].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者操作系统
	 * 
	 * @return
	 */
	public static String getRequestOs() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 2) {
			return agents[2].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者的代理全部信息
	 * 
	 * @return
	 */
	public static String getRequestUserAgent() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String userAgent = req.getHeader("user-agent");
		return userAgent;
	}

	/**
	 * 添加cookie
	 * 
	 * @param cookie
	 */
	public static void addCookie(Cookie cookie) {
		ServletActionContext.getResponse().addCookie(cookie);
	}

	/**
	 * 获取cookie
	 * 
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(String name) {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * 是否是post请求
	 * 
	 * @return
	 */
	public static boolean isMethodPost() {
		String method = ServletActionContext.getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			return true;
		} else {
			return false;
		}
	}
}
