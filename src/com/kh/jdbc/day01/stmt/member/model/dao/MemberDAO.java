package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스 -> DB랑 연결된다.
	// JDBC 코딩이 있어야 함. 
	
	// 주소와 등등을 상수화하면 계속해서 꺼내서 쓸 수 있다.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public Member selectOne(String memberId) {
		// 이 멤버를 쿼리문에서 받아야된다면 1~6 처음부터 다시 해줘야한당.
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		Member member = null;	// null 로 우선 선언하고 member을 추가해줌
		try {
			Class.forName(DRIVER_NAME);
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId +"'"; 
			//쿼리문이므로 memberId에 따옴표를 써줘야한다 !! (시험포인트)
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
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		// 1. 왜 mList에 member가 들어가나요?
		// 2. rset을 mList에는 왜 못 넣는 건가요?
		// add라는 메소드 자체가 member만 넣을 수 있다. (ResultSet은 x)
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3.2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>(); 
		// return이 try문 바깥에 있으므로 List선언을 try바깥에다 해준다
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
			// 후처리 -> 반복적으로 할 필요 없다. 즉, 없으면 안해도 됨.
			while(rset.next()) {
				// rsetToMember
				Member member = new Member();	// 멤버 객체 생성
				member.setMemberName(rset.getString("MEMBER_NAME"));	// 멤버의 이름을 resultset에서 얻은 member_name의 값으로 설정해준다.
				// 변수 이용해서도 된다. -> 그러나 코드의 간결화를 위해 set안에 rset.get~을 한번에 넣어주는 것
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
				mList.add(member);	// 리스트에 멤버 하나씩 넣어줌 (위에서 멤버의 필드 다 설정해줌)
//				System.out.println("이름 : "+rset.getString("MEMBER_NAME"));	// 컬럼명 그대로
//				System.out.println("아이디 : "+rset.getString("MEMBER_ID"));	// 컬럼명 그대로 
//				System.out.println("이메일 : "+rset.getString("EMAIL"));		// 컬럼명 그대로
//				System.out.println("이메일 : "+rset.getInt("AGE"));		// 컬럼명 그대로
//				System.out.println("이메일 : "+rset.getDate("REG_DATE"));		// 컬럼명 그대로
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
	
	// 데이터를 삽입하는 메소드 생성
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
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
			//String query = "INSERT INTO MEMBER_TBL VALUES('khuser01', 'pass01', '일용자', '여자', 20, 'kh1@gmail.com', '01011112222', '서울시 중구 남대문로', '게임', DEFAULT)";			
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
			// member의 getter메소드를 사용해야해서 이렇게 해야한다
			// Resultset rset = stmt.executeQuery(query);	// SELECT 할 때만 ResultSet은 SELECT의 결과
			int result = stmt.executeUpdate(query); // DML(insert, update, delete) 의 경우 호출하는 메소드(시험)
			// DML 일때는 Resultset이 아니라 int형으로 한다.
			// 후처리
			if(result>0) {
				// 성공 메시지 출력
				// commit;
				System.out.println("데이터 등록 성공!");
			} else {
				// 실패 메시지 출력
				// rollback;
				System.out.println("데이터 등록 실패!");
			}
			// 6. 자원해제
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
