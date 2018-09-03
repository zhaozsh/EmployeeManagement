package com.startdima.utils;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
/**
 * 获取数据库连接工具类
 * @author zhaozsh
 * @date 2018年8月27日 
 */
public class DBUtils {
	// 获取连接
	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		String username = "root";
	    String password = "123456";
	    Connection conn = null;
	    try {
			Class.forName(driver);
			conn = (Connection)DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return conn;
	}
}
