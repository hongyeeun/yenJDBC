package com.kh.jdbc.day04.pstmt.common;

import java.sql.*;

public class JDBCTemplateOld {
	// DAO���� �ô� ���� �� ���� �κ��� common���� �Ű��ش�.(DAO�� �ʹ� �����ؼ�)
	// employee���� ������Ű���� �־�� �Ѵ� -> service���� ���پ������ؼ�
	// singleton ��� �� -> static���� �ٲ���
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "yenJDBC";
	private static final String PASSWORD = "yenJDBC";
	
	private static Connection conn;
	
	// ���⼭�� try-catch���� ����ó���� �����ش�. (���� ������)
	public static Connection getConnection() 
			throws SQLException, ClassNotFoundException {
		// �̱� �� ��� ->  null ���̸� ����ǵ��� �ٲ���
		// isClosed�� �߰����ָ� �����־ ����ǰ� �ϵ���
		if (conn == null||conn.isClosed())
		{		
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}	
}
