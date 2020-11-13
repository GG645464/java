package com.gg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * ���ݿ⹤����
 * @author Administrator
 *
 */
public class DbUtil {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/db_exam?useSSL=true&characterEncoding=utf-8";
	private String dbUserName = "root";	//�û���
	private String dbPassword = "645464";	//����
	private String jdbcName = "com.mysql.jdbc.Driver";	//��������
	public Connection getCon() {
		Connection con=null;
		try {
			Class.forName(jdbcName);
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//			System.out.println("���ӳɹ�!");
		}catch(Exception e) {
			System.out.println("����ʧ��!");
		}
		return con;
		
	}

	/**
	 * �ر����ݿ�����
	 * @param con
	 */
	public void closeCon(Connection con) {
		try {
			if(con == null) {
				con.close();
				System.out.println("�رճɹ�");
			}
		}catch(Exception e) {
			System.out.println("�ر�ʧ��");
		}
	}
	
	public static void main(String[] args) {
		DbUtil db = new DbUtil();
		db.getCon();
	}
}
