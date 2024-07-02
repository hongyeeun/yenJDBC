package com.kh.jdbc.day04.pstmt.common;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

// 기존 JDBCTemplate은 old 버전으로.
// 이 버전은 resources의 파일 dev.properties에서 작성한 것을 꺼내서 쓴다
	
	// 밑의 멤버필드들 파일로 옮겼음 -> 나중에 꺼내서 쓸 수 있게
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USERNAME = "yenJDBC";
//	private static final String PASSWORD = "yenJDBC";

	private static Properties prop;
	private static Connection conn;
	
	public static Connection getConnection() 
			throws SQLException, ClassNotFoundException, IOException {
		// file 입출력함수를 사용했으므로 IO예외처리 해준다.
		prop = new Properties();
		Reader reader = new FileReader("resources/dev.properties");	// dev파일값을 읽는다.
		prop.load(reader);	// 읽은 것을 불러온다.
		
		// getProperty 메소드를 통해서 파일에 적은 값을 전달한다.
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
