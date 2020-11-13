package com.gg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DbUtil {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/db_exam?useSSL=true&characterEncoding=utf-8";
	private String dbUserName = "root";	//用户名
	private String dbPassword = "645464";	//密码
	private String jdbcName = "com.mysql.jdbc.Driver";	//驱动名称
	public Connection getCon() {
		Connection con=null;
		try {
			Class.forName(jdbcName);
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//			System.out.println("连接成功!");
		}catch(Exception e) {
			System.out.println("连接失败!");
		}
		return con;
		
	}

	/**
	 * 关闭数据库连接
	 * @param con
	 */
	public void closeCon(Connection con) {
		try {
			if(con == null) {
				con.close();
				System.out.println("关闭成功");
			}
		}catch(Exception e) {
			System.out.println("关闭失败");
		}
	}
	
	public static void main(String[] args) {
		DbUtil db = new DbUtil();
		db.getCon();
	}
}
