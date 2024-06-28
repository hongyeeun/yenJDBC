package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC�� �̿��Ͽ�
	// Oracle DB�� �����ϴ� Ŭ���� -> DB�� ����ȴ�.
	// JDBC �ڵ��� �־�� ��. 
	
	// �ּҿ� ����� ���ȭ�ϸ� ����ؼ� ������ �� �� �ִ�.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public Member selectOne(String memberId) {
		// �� ����� ���������� �޾ƾߵȴٸ� 1~6 ó������ �ٽ� ������Ѵ�.
		/*
		 * 1. ����̹� ���
		 * 2. DBMS ���� ����
		 * 3. Statement ����
		 * 4. ������ ����
		 * 5. ����� �ޱ�
		 * 6. �ڿ�����
		 */
		Member member = null;	// null �� �켱 �����ϰ� member�� �߰�����
		try {
			Class.forName(DRIVER_NAME);
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId +"'"; 
			//�������̹Ƿ� memberId�� ����ǥ�� ������Ѵ� !! (��������Ʈ)
			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				// rsetToMember
				member = new Member();
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
			}
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
		return member;	
	}
	public List<Member> selectList() {
		/*
		 * 1. ����̹� ���
		 * 2. DBMS ���� ����
		 * 3. Statement ����
		 * 4. ������ ����
		 * 5. ����� �ޱ�
		 * 6. �ڿ�����
		 */
		// 1. �� mList�� member�� ������?
		// 2. rset�� mList���� �� �� �ִ� �ǰ���?
		// add��� �޼ҵ� ��ü�� member�� ���� �� �ִ�. (ResultSet�� x)
		// 3. rset�� member�� ��� �ٲٳ���?
		// 3.1 Member Ŭ�������� �ʵ�� ����/���� �ʿ�
		// 3.2 ResultSet�� getString(), getInt(), getDate() �ʿ�
		List<Member> mList = new ArrayList<Member>(); 
		// return�� try�� �ٱ��� �����Ƿ� List������ try�ٱ����� ���ش�
		try {
			// 1.
			Class.forName(DRIVER_NAME);
			// 2.
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);;
			// 3.
			Statement stmt = conn.createStatement();
			// 4. /5.
			String query = "SELECT * FROM MEMBER_TBL";
			ResultSet rset = stmt.executeQuery(query);
			// ��ó�� -> �ݺ������� �� �ʿ� ����. ��, ������ ���ص� ��.
			while(rset.next()) {
				// rsetToMember
				Member member = new Member();	// ��� ��ü ����
				member.setMemberName(rset.getString("MEMBER_NAME"));	// ����� �̸��� resultset���� ���� member_name�� ������ �������ش�.
				// ���� �̿��ؼ��� �ȴ�. -> �׷��� �ڵ��� ����ȭ�� ���� set�ȿ� rset.get~�� �ѹ��� �־��ִ� ��
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
				mList.add(member);	// ����Ʈ�� ��� �ϳ��� �־��� (������ ����� �ʵ� �� ��������)
//				System.out.println("�̸� : "+rset.getString("MEMBER_NAME"));	// �÷��� �״��
//				System.out.println("���̵� : "+rset.getString("MEMBER_ID"));	// �÷��� �״�� 
//				System.out.println("�̸��� : "+rset.getString("EMAIL"));		// �÷��� �״��
//				System.out.println("�̸��� : "+rset.getInt("AGE"));		// �÷��� �״��
//				System.out.println("�̸��� : "+rset.getDate("REG_DATE"));		// �÷��� �״��
			}
			// 6. 
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
		return mList;
	}
	
	// �����͸� �����ϴ� �޼ҵ� ����
	public void insertMember(Member member) {
		/*
		 * 1. ����̹� ���
		 * 2. DBMS ����
		 * 3. Statement ����
		 * 4. ������ ����
		 * 5. ����� �ޱ�
		 * 6. �ڿ�����
		 * 
		 */
		try {
			// 1. 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			Connection conn = 
					DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yenJDBC", "yenJDBC");
			// 3.
			Statement stmt = conn.createStatement();
			// 4. /5. 
			//String query = "INSERT INTO MEMBER_TBL VALUES('khuser01', 'pass01', '�Ͽ���', '����', 20, 'kh1@gmail.com', '01011112222', '����� �߱� ���빮��', '����', DEFAULT)";			
			String query = "INSERT INTO MEMBER_TBL VALUES('"
			+member.getMemberId()+"', '"
			+member.getMemberPw()+"', '"
			+member.getMemberName()+"', '"
			+member.getGender()+"', '"
			+member.getAge()+"', '"
			+member.getEmail()+"', '"
			+member.getPhone()+"', '"
			+member.getAddress()+"', '"
			+member.getHobby()+"', DEFAULT)";
			// member�� getter�޼ҵ带 ����ؾ��ؼ� �̷��� �ؾ��Ѵ�
			// Resultset rset = stmt.executeQuery(query);	// SELECT �� ���� ResultSet�� SELECT�� ���
			int result = stmt.executeUpdate(query); // DML(insert, update, delete) �� ��� ȣ���ϴ� �޼ҵ�(����)
			// DML �϶��� Resultset�� �ƴ϶� int������ �Ѵ�.
			// ��ó��
			if(result>0) {
				// ���� �޽��� ���
				// commit;
				System.out.println("������ ��� ����!");
			} else {
				// ���� �޽��� ���
				// rollback;
				System.out.println("������ ��� ����!");
			}
			// 6. �ڿ�����
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
