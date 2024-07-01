package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.*;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {

	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public int insertMember(Member member) {
		// memOne 이든 member이든 이름 상관없다. 타입만이 중요
		// 근데 똑같이 하는게 좋다.. (헷갈려서) 
		Connection conn = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());	//age는 int형이므로 setInt
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			result = pstmt.executeUpdate();
			//			stmt = conn.createStatement();
//			String query = "INSERT INTO MEMBER_TBL VALUES('"
//			+member.getMemberId()+"', '"+member.getMemberPw()+"', '"+member.getMemberName()+"', '"
//			+member.getGender()+"', '"+member.getAge()+"', '"+member.getEmail()+"', '"
//			+member.getPhone()+"', '"+member.getAddress()+"', '"+member.getHobby()+"', DEFAULT)";
			// 쿼리문은 '' 인데, member의 필드를 적어줘야하므로'' 안에 "" 적어주고 get메소드 적어준다.
			// 그리고 +로 연결되어있으므로 양옆에 + 붙여줌.
			// '' -> '""' -> '"++"' -> '"+member.get요소+"
//			result = stmt.executeUpdate(query);		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// remove하는 또 다른 메소드
	public int deleteMember(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();	// 쿼리문 안들어감
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBER_T3BL SET MEMBER_PW = ?, EMAIL=?, PHONE=?, ADDRESS=?, HOBBY=? WHERE MEMBER_ID = ?";	
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member selectOne(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;	//보안상 문제 때문에 이걸 사용한다.
		ResultSet rset = null; // SELECT 할거니까 resultset 필요함
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 쿼리문을 그대로 실행하는 Statement와는 다르게
			// 쿼리문을 이용하여 컴파일을 미리하여 객체를 생성함.
			// 쿼리문에는 값이 들어가는 자리를 위치홀더->?로 표시해줘야함. 
			
			// PreparedStatement 사용
			String query ="SELECT * FROM MEMBER_TBL WHERE MEMBER_ID =? AND MEMBER_PW =?";
			pstmt = conn.prepareStatement(query);	// 워크시트 만들때 query 적어준다.
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();	//실행할때는 query를 안적어준다.
			
			// Statement 사용
			// stmt 와 psmt 차이 비교 -> 시험 범위
//			stmt = conn.createStatement();
//			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ='"
//			+member.getMemberId()+"' AND MEMBER_PW = '"+member.getMemberPw()+"'";
//			rset = stmt.executeQuery(query);
			// rsetToMember
			if(rset.next()) {
				result = rsetToMember(rset);	
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Member selectOne(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;	//보안상 문제 때문에 이걸 사용한다.
		ResultSet rset = null; // SELECT 할거니까 resultset 필요함
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query ="SELECT * FROM MEMBER_TBL WHERE MEMBER_ID =?";
			pstmt = conn.prepareStatement(query);	// 워크시트 만들때 query 적어준다.
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();	//실행할때는 query를 안적어준다.
			if(rset.next()) {
				result = rsetToMember(rset);	
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		String memberId = rset.getString("MEMBER_ID");
		member.setMemberId(memberId);
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setRegDate(rset.getDate("REG_DATE"));
		return member;
	}
}
