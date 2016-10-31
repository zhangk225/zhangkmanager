package com.zhangkssh.baseframe.hibernate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zhangkssh.baseframe.common.util.CommonUtil;

public class DbConnection {

	// 私有静态对象实例
	// private static DbConnection dbc = null;
	private Connection dbConn = null;
	private String type;

	/**
	 * 私有构造函数。
	 */
	public DbConnection(String type) {
		this.type = type;
	}

	// /**
	// * 静态方法，获取一个工具类实例。
	// *
	// * @return
	// */
	// public static DbConnection getInstance() {
	// if (dbc == null) {
	// dbc = new DbConnection();
	// }
	// return dbc;
	// }

	public synchronized Connection geConnetion(String dbFileName)
			throws IOException {
		Properties p = CommonUtil.properties_GetProperties(dbFileName);
		String driverName = p.getProperty("db.driver" + type);
		// 加载JDBC驱动
		String dbURL = p.getProperty("db.url" + type);
		// 连接服务器和数据库sample
		String userName = p.getProperty("db.user" + type); // 默认用户名
		String userPwd = p.getProperty("db.password" + type); // 密码
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			// System.out.println("Connection Successful!"); //如果连接成功
			// 控制台输出Connection
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;

	}

	public void close() {
		if (dbConn != null) {
			try {
				dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
