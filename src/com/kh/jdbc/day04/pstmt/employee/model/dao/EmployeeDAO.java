package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

//import com.kh.jdbc.day04.pstmt.common.JDBCTemplateOld;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAO {
	// properties ���� ����
	
	private final String FILE_NAME = "resources/query.properties";
	private Properties prop;
	
	// �⺻������ �����ؼ� �ϴ� ���
	public EmployeeDAO() {
		try {
			prop = new Properties();
			Reader reader = new FileReader(FILE_NAME);
			prop.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Employee> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;
		String query = prop.getProperty("selectList");
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
	
	// ���⼱ throw�� ����ó������
	public int insertEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertEmployee");
		pstmt = conn.prepareStatement(query);
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
		String query = prop.getProperty("deleteEmployee ");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);
		result = pstmt.executeUpdate();
//		conn.close(); -> �����ش�. (��� �޼ҵ� �� �ش�..)
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
		Employee emp = new Employee();	// ����������Ѵپ�
		emp.setEmpId(rset.getString("EMP_ID"));	//����ó�� -> �������
		emp.setEmpName(rset.getString("EMP_NAME"));
		emp.setEmpNo(rset.getString("EMP_NO"));
		emp.setEmail(rset.getString("EMAIL"));
		emp.setPhone(rset.getString("PHONE"));
		emp.setDeptCode(rset.getString("DEPT_CODE"));
		emp.setJobCode(rset.getString("JOB_CODE"));
		emp.setSalLevel(rset.getString("SAL_LEVEL"));	// Employee VO���� 
		emp.setSalary(rset.getInt("SALARY"));	// salary �ʵ尡 int �ϱ� getInt
		emp.setBonus(rset.getDouble("BONUS"));	// bonus �ʵ尡 double�̴ϱ� getDouble
		emp.setManagerId(rset.getString("MANAGER_ID"));	
		emp.setHireDate(rset.getDate("HIRE_DATE"));	// hireDate �ʵ尡 Date�ϱ� getDate
		emp.setEntDate(rset.getDate("ENT_DATE"));	// entDate �ʵ尡 Date�ϱ� getDate
		emp.setEntYn(rset.getString("ENT_YN"));
		return emp;
	}

	public int updateEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateEmployee");
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
