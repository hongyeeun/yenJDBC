package com.kh.jdbc.day01.basic;

import java.sql.*;

public class JDBCRun {
	public static void main(String [] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록 (jar)
		 * 2. DBMS 연결 생성 (KH/KH 확인)
		 * 3. Statement 객체 생성 (워크시트, 쿼리문 실행준비)
		 * 4. SQL 전송 			  (CTRL+ENTER)
		 * 5. 결과받기 			  (REsultSet)
		 * 6. 자원해제
		 * 
		 */
		try {
			// 1. 드라이버 등록 
			// try - catch로 감싸줘야한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결 생성
			// jdbc:oracle:thin:@호스트이름:포트:SID 적어주고, 계정아이디, 비밀번호 적어준다.
			Connection conn =
						DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KH", "KH");
			// 3. Statement 객체 생성
			Statement stmt = conn.createStatement();	// Statement 객체생성
			String query = "SELECT * FROM DEPARTMENT";//쿼리문 작성
			// 4. SQL 전송, 5. 결과받기
			ResultSet rset = stmt.executeQuery(query);	// ResultsSet에 실행문 저장
			// 후처리
			while(rset.next()) {	// 다음값이 있는지 체크
				// 컬럼명을 적고 그 정보를 가져온다. (여러컬럼이면 여러 개 적기)
//				System.out.println("직원명 : "+rset.getString("EMP_NAME"));	// 컬럼명 오타없이 적기
				System.out.println("부서명 : "+rset.getString("DEPT_TITLE")); 
				//DEPARTMENT 테이블로부터 받는다. 만약 EMP_NAME이면 SELECT 문을 EMPLOYEE로 바꿔줘야함.
			}
			// 6. 자원해제 
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
