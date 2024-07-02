package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplateOld;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAOold {

	public List<Employee> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;
		String query = "SELECT * FROM EMPLOYEE";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			//rsetToEmployee
			eList = new ArrayList<Employee>();
			while(rset.next()) {
				Employee emp = rsetToEmployee(rset);
				eList.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eList;
	}
	
	// 여기선 throw로 예외처리해줌
	public int insertEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		// 모든 값을 입력하는 것이 아니기 때문에 컬럼명을 명시해줘야한다. + DEFAULT값도 추가해줌 (보통은)
		String query = "INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE, ENT_YN) "
				+ "VALUES(?, ?, ?, ?, ?, SYSDATE, DEFAULT)";
		pstmt = conn.prepareStatement(query);
		// 연결해서 워크시트 생성 -> 값 설정 -> 실행
		pstmt.setString(1, emp.getEmpId());
		pstmt.setString(2, emp.getEmpName());
		pstmt.setString(3, emp.getEmpNo());
		pstmt.setString(4, emp.getJobCode());
		pstmt.setString(5, emp.getSalLevel());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	public int deleteEmployee(Connection conn, String empId) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);
		result = pstmt.executeUpdate();
//		conn.close(); -> 없애준다. (모든 메소드 다 해당..)
		pstmt.close();
		return result;
	}

	public Employee selectOne(Connection conn, String empId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			emp = rsetToEmployee(rset);	
		}
		pstmt.close();
		rset.close();
		return emp;
	}
	
	private Employee rsetToEmployee(ResultSet rset) throws SQLException {
		Employee emp = new Employee();	// 생성해줘야한다아
		emp.setEmpId(rset.getString("EMP_ID"));	//예외처리 -> 던지기로
		emp.setEmpName(rset.getString("EMP_NAME"));
		emp.setEmpNo(rset.getString("EMP_NO"));
		emp.setEmail(rset.getString("EMAIL"));
		emp.setPhone(rset.getString("PHONE"));
		emp.setDeptCode(rset.getString("DEPT_CODE"));
		emp.setJobCode(rset.getString("JOB_CODE"));
		emp.setSalLevel(rset.getString("SAL_LEVEL"));	// Employee VO에서 
		emp.setSalary(rset.getInt("SALARY"));	// salary 필드가 int 니까 getInt
		emp.setBonus(rset.getDouble("BONUS"));	// bonus 필드가 double이니까 getDouble
		emp.setManagerId(rset.getString("MANAGER_ID"));	
		emp.setHireDate(rset.getDate("HIRE_DATE"));	// hireDate 필드가 Date니까 getDate
		emp.setEntDate(rset.getDate("ENT_DATE"));	// entDate 필드가 Date니까 getDate
		emp.setEntYn(rset.getString("ENT_YN"));
		return emp;
	}

	public int updateEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE EMPLOYEE SET EMAIL = ?, PHONE = ?, DEPT_CODE = ?, "
				+ "SALARY = ? , BONUS = ?, MANAGER_ID =? WHERE EMP_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, emp.getEmail());
		pstmt.setString(2, emp.getPhone());
		pstmt.setString(3, emp.getDeptCode());
		pstmt.setInt(4, emp.getSalary());
		pstmt.setDouble(5, emp.getBonus());
		pstmt.setString(6, emp.getManagerId());
		pstmt.setString(7, emp.getEmpId());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
}
