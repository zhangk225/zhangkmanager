package com.zhangkssh.frame.util;

public class Frame_Constants {

	public static final int PROJECT_ID = 0;

	public static final String PROJECT_NAME = "frame";

	public static final String FRAME_PATH = "com/frame/";

	public static final String SQLFILED_REMOVED = "removed";

	public static final String RELSELECT_CONDITION = " removed is null ";

	public static final int PAGE_NUMBER = 15;// 分页每页记录数

	/**
	 * session的属性主键
	 */
	// 时区标准化用到 在action中进行参数处理的时候 要从session中获取时区来转化成当前用户时区时间
	public static final String SESSIONKEY_TIMEZONE = "sessionkey.timezone";

	// 资源类型标准化用到 在action中进行参数处理的时候 要从session中获取时区来转化成当前用户资源类型
	public static final String SESSIONKEY_RESOURCEINTERNATIONALIZATIONTYPE = "sessionkey.resourceinternationalizationtype";
}
