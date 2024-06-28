package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC 코딩절차
	// JDBC을 통해 DB의 데이터를 가져옴
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "yenJDBC";
	private final String PASSWORD = "yenJDBC";
	
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성 (워크시트)
		 * 4. SQL문 전송
		 * 5. 결과받기
		 */
		// try문 바깥에서 선언하고 초기화해준다.
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();	//워크시트열기
			// 쿼리문 작성, ; 오타조심!, '(홑따옴표)조심!
			// 홑따옴표 '' 안에 meber.get~ 써줘야하는데 + 연결되어있으므로.. ? +로 
			String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()+"', '"+member.getMemberPw()+"', '"
					+member.getMemberName()+"', '"+member.getGender()+"',"+member.getAge()+", '"+member.getEmail()+"', '"
					+member.getPhone()+"', '"+member.getAddress()+"', '"+member.getHobby()+"', DEFAULT)";
			
			// DML의 경우 성공한 행의 갯수가 리턴, 메소드는 executeUpdate()
			int result = stmt.executeUpdate(query);
			if(result > 0) { // 지금은 자동커밋임.
				// commit
				// 성공해서
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
			// 다 쓴 자원해제 해줌
			// 예외가 발생하든 안하든 무조건 실행
			// 자원반납을 통해 오류 발생 방지
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
		// try안에서 쓴 변수는 return안되니까 try 밖에서 int result = 0;
		try {
			// 1. 드라이버 등록
			// checked Exception이니까 try ~ catch
			Class.forName(DRIVER_NAME);
			// 2. 연결 생성
			// checked Exception이니까 catch절 추가
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 밑에서 커밋/롤백할거니까 자동커밋 해제, setAutoCommit(false)
			conn.setAutoCommit(false);
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 문자열은 '(홑따옴표)로 감싸야 되니까 "'"
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"+modifyInfo.getMemberPw()
							+"', EMAIL ='"+modifyInfo.getEmail()
							+"', PHONE = '"+modifyInfo.getPhone()
							+"', ADDRESS ='"+modifyInfo.getAddress()
							+"', HOBBY ='"+modifyInfo.getHobby()
							+"' WHERE MEMBER_ID ='"+ modifyInfo.getMemberId()+"'";
			// 4. 쿼리문 전송 및 // 5. 결과 받기
			// DML의 결과값은 성공한 행의 개수니까 int result
			// 쿼리 실행 메소드는 DML이니까 executeUpdate(query);
			result = stmt.executeUpdate(query);
			// 쿼리 성공하면 커밋, 실패하면 롤백해야하니까 if(result>0)
			if(result>0) {
				conn.commit();	// 커밋, 영구저장
			} else {
				conn.rollback();	// 롤백, 최근 커밋시점으로 이동(원복)
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
		// finally에서 close()하니까 try바깥에서 선언
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			// 1. 드라이버 등록
			// checked Exception이니까 try ~ catch
			Class.forName(DRIVER_NAME);
			// 2. 연결 생성
			// checked Exception이니까 catch절 추가
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 밑에서 커밋/롤백할거니까 자동커밋 해제, setAutoCommit(false)
			conn.setAutoCommit(false);
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 문자열은 '(홑따옴표)로 감싸야 되니까 "'"
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID= '"+memberId+"'";
			// 4. 쿼리문 전송 및 // 5. 결과 받기
			// DML의 결과값은 성공한 행의 개수니까 int result
			// 쿼리 실행 메소드는 DML이니까 executeUpdate(query);
			result = stmt.executeUpdate(query);
			// 쿼리 성공하면 커밋, 실패하면 롤백해야하니까 if(result>0)
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
		// try 안에서 쓴 변수는 return 안되니까 try 밖에서 int result = 0;
		// 호출한 곳에서 써야하니까 return result, MemberController : 25 
		return result;
	}

	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성 (워크시트)
		 * 4. SQL문 전송
		 * 5. 결과받기
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// 디비에서 가져온 값 넘겨줘야 하니까
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 연결 생성
			stmt = conn.createStatement();	// 워크시트 열기
			String query = "SELECT * FROM MEMBER_TBL";	// 쿼리문 작성
			// 실행 CTRL + ENTER
			rset = stmt.executeQuery(query); // SELECT는 executeQuery(query)
			// 후처리, 여러개니까 while, 전부 가져올때까지 돈다
			// null 이면 안되니까 newArrayList<>()
			mList = new ArrayList<Member>();
			while(rset.next()) {
				// rset은 바로 못쓰니까 Member
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
		// 호출한 곳에서 써야되니까 return mList, MemberController:23
		return mList;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		// select니까 ResultSet
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID ="+"'" + memberId +"'";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				member = rsetToMember(rset); //this생략
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try 안에서 쓴 변수는 return 안되니까 try 밖에서 Member member = null;
		// 호출한 곳에서 써야하니까 return member, MemberView : 41
		return member;
	}
	
	// rsetToMember을 캡슐화하여 편하게 쓰게 한다.
	public Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		// 비어있으면 안되니까 setter
		// resultset에서 값을 가져와야되니까 rset.getString("컬럼명")
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
		// member에 다 담았고 호출한 곳에서 써야 하니까 return member;
		return member;
	}



}
