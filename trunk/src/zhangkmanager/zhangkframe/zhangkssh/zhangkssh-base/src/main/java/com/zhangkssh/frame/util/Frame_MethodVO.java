package com.zhangkssh.frame.util;

/**
 * 检测权限的时候出入此对象，可以用来检测类、方法、参数，是否有权限访问
 * 
 * @author Administrator
 * 
 */
public class Frame_MethodVO {

	private String className;

	private String methodName;

	private Object[] args;

	public Frame_MethodVO(String className, String methodName, Object[] args) {
		this.className = className;
		this.methodName = methodName;
		this.args = args;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
