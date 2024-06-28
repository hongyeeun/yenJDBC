package com.kh.jdbc.day01.basic;

import java.sql.*;

public class JDBCRun {
	public static void main(String [] args) {
		/*
		 * JDBC �ڵ� ����
		 * 1. ����̹� ��� (jar)
		 * 2. DBMS ���� ���� (KH/KH Ȯ��)
		 * 3. Statement ��ü ���� (��ũ��Ʈ, ������ �����غ�)
		 * 4. SQL ���� 			  (CTRL+ENTER)
		 * 5. ����ޱ� 			  (REsultSet)
		 * 6. �ڿ�����
		 * 
		 */
		try {
			// 1. ����̹� ��� 
			// try - catch�� ��������Ѵ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS ���� ����
			// jdbc:oracle:thin:@ȣ��Ʈ�̸�:��Ʈ:SID �����ְ�, �������̵�, ��й�ȣ �����ش�.
			Connection conn =
						DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KH", "KH");
			// 3. Statement ��ü ����
			Statement stmt = conn.createStatement();	// Statement ��ü����
			String query = "SELECT * FROM DEPARTMENT";//������ �ۼ�
			// 4. SQL ����, 5. ����ޱ�
			ResultSet rset = stmt.executeQuery(query);	// ResultsSet�� ���๮ ����
			// ��ó��
			while(rset.next()) {	// �������� �ִ��� üũ
				// �÷����� ���� �� ������ �����´�. (�����÷��̸� ���� �� ����)
//				System.out.println("������ : "+rset.getString("EMP_NAME"));	// �÷��� ��Ÿ���� ����
				System.out.println("�μ��� : "+rset.getString("DEPT_TITLE")); 
				//DEPARTMENT ���̺�κ��� �޴´�. ���� EMP_NAME�̸� SELECT ���� EMPLOYEE�� �ٲ������.
			}
			// 6. �ڿ����� 
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
