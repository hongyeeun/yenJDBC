package com.kh.jdbc.day04.pstmt.employee.model.service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplate;
//import com.kh.jdbc.day04.pstmt.common.JDBCTemplateOld;
import com.kh.jdbc.day04.pstmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeService {
	//JDBCTemplate (����)�� import�Ͽ� EmployeeService�� �ϵ��� �Ѵ�
	//�׷��� JDBCTemplate�� ������Ű���� ����
	
	// singleton �̸� JDBCTemplate�� static�̴ϱ� ���� ���ص��ȴ�
//	private JDBCTemplate jdbcTemplate;
	private EmployeeDAO eDao;
	
	public EmployeeService() {
//		jdbcTemplate = new JDBCTemplate();
		eDao = new EmployeeDAO();
	}
	
	public List<Employee> selectList(){
		Connection conn = null;
		List<Employee> eList = null;
		try {
//			conn = jdbcTemplate.getConnection();
			conn = JDBCTemplate.getConnection();	// static���� ������ �̷���
			eList = eDao.selectList(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// �ٲ� JDBCTemplate ����ϸ� -> IO����ó���� ������Ѵ�.
			e.printStackTrace();
		}
		return eList;
	}
	
	public int insertEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.insertEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmployee(String empId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.deleteEmployee(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Employee selectOneById(String empId) {
		Connection conn = null;
		Employee emp = null;
		try {
			conn = JDBCTemplate.getConnection();
			emp = eDao.selectOne(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.updateEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}