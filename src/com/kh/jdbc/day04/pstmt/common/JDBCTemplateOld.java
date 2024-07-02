package com.kh.jdbc.day04.pstmt.common;

import java.sql.*;

public class JDBCTemplateOld {
	// DAO에서 맡는 역할 중 연결 부분은 common으로 옮겨준다.(DAO가 너무 많이해서)
	// employee보다 상위패키지에 있어야 한다 -> service에서 갖다쓰기위해서
	// singleton 사용 시 -> static으로 바꿔줌
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "yenJDBC";
	private static final String PASSWORD = "yenJDBC";
	
	private static Connection conn;
	
	// 여기서는 try-catch말고 예외처리는 던져준다. (각각 던져줌)
	public static Connection getConnection() 
			throws SQLException, ClassNotFoundException {
		// 싱글 톤 사용 ->  null 값이면 실행되도록 바꿔줌
		// isClosed도 추가해주면 닫혀있어도 실행되게 하도록
		if (conn == null||conn.isClosed())
		{		
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}	
}
