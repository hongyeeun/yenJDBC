package com.kh.jdbc.day04.pstmt.common;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

// ���� JDBCTemplate�� old ��������.
// �� ������ resources�� ���� dev.properties���� �ۼ��� ���� ������ ����
	
	// ���� ����ʵ�� ���Ϸ� �Ű��� -> ���߿� ������ �� �� �ְ�
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USERNAME = "yenJDBC";
//	private static final String PASSWORD = "yenJDBC";

	private static Properties prop;
	private static Connection conn;
	
	public static Connection getConnection() 
			throws SQLException, ClassNotFoundException, IOException {
		// file ������Լ��� ��������Ƿ� IO����ó�� ���ش�.
		prop = new Properties();
		Reader reader = new FileReader("resources/dev.properties");	// dev���ϰ��� �д´�.
		prop.load(reader);	// ���� ���� �ҷ��´�.
		
		// getProperty �޼ҵ带 ���ؼ� ���Ͽ� ���� ���� �����Ѵ�.
		String driverName = prop.getProperty("driverName");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		if (conn == null||conn.isClosed())
		{		
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}	
}
