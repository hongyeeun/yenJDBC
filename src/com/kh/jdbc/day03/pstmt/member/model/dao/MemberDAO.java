package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.*;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {

	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public int insertMember(Member member) {
		// memOne �̵� member�̵� �̸� �������. Ÿ�Ը��� �߿�
		// �ٵ� �Ȱ��� �ϴ°� ����.. (�򰥷���) 
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
			pstmt.setInt(5, member.getAge());	//age�� int���̹Ƿ� setInt
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
			// �������� '' �ε�, member�� �ʵ带 ��������ϹǷ�'' �ȿ� "" �����ְ� get�޼ҵ� �����ش�.
			// �׸��� +�� ����Ǿ������Ƿ� �翷�� + �ٿ���.
			// '' -> '""' -> '"++"' -> '"+member.get���+"
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

	// remove�ϴ� �� �ٸ� �޼ҵ�
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
			result = pstmt.executeUpdate();	// ������ �ȵ�
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
		PreparedStatement pstmt = null;	//���Ȼ� ���� ������ �̰� ����Ѵ�.
		ResultSet rset = null; // SELECT �ҰŴϱ� resultset �ʿ���
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// �������� �״�� �����ϴ� Statement�ʹ� �ٸ���
			// �������� �̿��Ͽ� �������� �̸��Ͽ� ��ü�� ������.
			// ���������� ���� ���� �ڸ��� ��ġȦ��->?�� ǥ���������. 
			
			// PreparedStatement ���
			String query ="SELECT * FROM MEMBER_TBL WHERE MEMBER_ID =? AND MEMBER_PW =?";
			pstmt = conn.prepareStatement(query);	// ��ũ��Ʈ ���鶧 query �����ش�.
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();	//�����Ҷ��� query�� �������ش�.
			
			// Statement ���
			// stmt �� psmt ���� �� -> ���� ����
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
		PreparedStatement pstmt = null;	//���Ȼ� ���� ������ �̰� ����Ѵ�.
		ResultSet rset = null; // SELECT �ҰŴϱ� resultset �ʿ���
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query ="SELECT * FROM MEMBER_TBL WHERE MEMBER_ID =?";
			pstmt = conn.prepareStatement(query);	// ��ũ��Ʈ ���鶧 query �����ش�.
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();	//�����Ҷ��� query�� �������ش�.
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
