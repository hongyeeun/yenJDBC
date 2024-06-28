package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC �ڵ�����
	// JDBC�� ���� DB�� �����͸� ������
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public void insertMember(Member member) {
		/*
		 * 1. ����̹� ���
		 * 2. ���� ����
		 * 3. Statement ���� (��ũ��Ʈ)
		 * 4. SQL�� ����
		 * 5. ����ޱ�
		 */
		// try�� �ٱ����� �����ϰ� �ʱ�ȭ���ش�.
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();	//��ũ��Ʈ����
			// ������ �ۼ�, ; ��Ÿ����!, '(Ȭ����ǥ)����!
			// Ȭ����ǥ '' �ȿ� meber.get~ ������ϴµ� + ����Ǿ������Ƿ�.. ? +�� 
			String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()+"', '"+member.getMemberPw()+"', '"
					+member.getMemberName()+"', '"+member.getGender()+"',"+member.getAge()+", '"+member.getEmail()+"', '"
					+member.getPhone()+"', '"+member.getAddress()+"', '"+member.getHobby()+"', DEFAULT)";
			
			// DML�� ��� ������ ���� ������ ����, �޼ҵ�� executeUpdate()
			int result = stmt.executeUpdate(query);
			if(result > 0) { // ������ �ڵ�Ŀ����.
				// commit
				// �����ؼ�
			} else {
				// rollback
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �� �� �ڿ����� ����
			// ���ܰ� �߻��ϵ� ���ϵ� ������ ����
			// �ڿ��ݳ��� ���� ���� �߻� ����
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int updateMember(Member modifyInfo) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		// try�ȿ��� �� ������ return�ȵǴϱ� try �ۿ��� int result = 0;
		try {
			// 1. ����̹� ���
			// checked Exception�̴ϱ� try ~ catch
			Class.forName(DRIVER_NAME);
			// 2. ���� ����
			// checked Exception�̴ϱ� catch�� �߰�
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// �ؿ��� Ŀ��/�ѹ��ҰŴϱ� �ڵ�Ŀ�� ����, setAutoCommit(false)
			conn.setAutoCommit(false);
			// 3. Statement ����
			stmt = conn.createStatement();
			// ���ڿ��� '(Ȭ����ǥ)�� ���ξ� �Ǵϱ� "'"
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"+modifyInfo.getMemberPw()
							+"', EMAIL ='"+modifyInfo.getEmail()
							+"', PHONE = '"+modifyInfo.getPhone()
							+"', ADDRESS ='"+modifyInfo.getAddress()
							+"', HOBBY ='"+modifyInfo.getHobby()
							+"' WHERE MEMBER_ID ='"+ modifyInfo.getMemberId()+"'";
			// 4. ������ ���� �� // 5. ��� �ޱ�
			// DML�� ������� ������ ���� �����ϱ� int result
			// ���� ���� �޼ҵ�� DML�̴ϱ� executeUpdate(query);
			result = stmt.executeUpdate(query);
			// ���� �����ϸ� Ŀ��, �����ϸ� �ѹ��ؾ��ϴϱ� if(result>0)
			if(result>0) {
				conn.commit();	// Ŀ��, ��������
			} else {
				conn.rollback();	// �ѹ�, �ֱ� Ŀ�Խ������� �̵�(����)
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
	
	public int deleteMember(String memberId) {
		// finally���� close()�ϴϱ� try�ٱ����� ����
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			// 1. ����̹� ���
			// checked Exception�̴ϱ� try ~ catch
			Class.forName(DRIVER_NAME);
			// 2. ���� ����
			// checked Exception�̴ϱ� catch�� �߰�
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// �ؿ��� Ŀ��/�ѹ��ҰŴϱ� �ڵ�Ŀ�� ����, setAutoCommit(false)
			conn.setAutoCommit(false);
			// 3. Statement ����
			stmt = conn.createStatement();
			// ���ڿ��� '(Ȭ����ǥ)�� ���ξ� �Ǵϱ� "'"
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID= '"+memberId+"'";
			// 4. ������ ���� �� // 5. ��� �ޱ�
			// DML�� ������� ������ ���� �����ϱ� int result
			// ���� ���� �޼ҵ�� DML�̴ϱ� executeUpdate(query);
			result = stmt.executeUpdate(query);
			// ���� �����ϸ� Ŀ��, �����ϸ� �ѹ��ؾ��ϴϱ� if(result>0)
			if(result>0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// try �ȿ��� �� ������ return �ȵǴϱ� try �ۿ��� int result = 0;
		// ȣ���� ������ ����ϴϱ� return result, MemberController : 25 
		return result;
	}

	public List<Member> selectList() {
		/*
		 * 1. ����̹� ���
		 * 2. ���� ����
		 * 3. Statement ���� (��ũ��Ʈ)
		 * 4. SQL�� ����
		 * 5. ����ޱ�
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// ��񿡼� ������ �� �Ѱ���� �ϴϱ�
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // ���� ����
			stmt = conn.createStatement();	// ��ũ��Ʈ ����
			String query = "SELECT * FROM MEMBER_TBL";	// ������ �ۼ�
			// ���� CTRL + ENTER
			rset = stmt.executeQuery(query); // SELECT�� executeQuery(query)
			// ��ó��, �������ϱ� while, ���� �����ö����� ����
			// null �̸� �ȵǴϱ� newArrayList<>()
			mList = new ArrayList<Member>();
			while(rset.next()) {
				// rset�� �ٷ� �����ϱ� Member
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ȣ���� ������ ��ߵǴϱ� return mList, MemberController:23
		return mList;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		// select�ϱ� ResultSet
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ="+"'" + memberId +"'";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				member = rsetToMember(rset); //this����
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try �ȿ��� �� ������ return �ȵǴϱ� try �ۿ��� Member member = null;
		// ȣ���� ������ ����ϴϱ� return member, MemberView : 41
		return member;
	}
	
	// rsetToMember�� ĸ��ȭ�Ͽ� ���ϰ� ���� �Ѵ�.
	public Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		// ��������� �ȵǴϱ� setter
		// resultset���� ���� �����;ߵǴϱ� rset.getString("�÷���")
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setRegDate(rset.getDate("REG_DATE"));
		// member�� �� ��Ұ� ȣ���� ������ ��� �ϴϱ� return member;
		return member;
	}



}
