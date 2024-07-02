package com.kh.jdbc.day04.pstmt.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.pstmt.employee.controller.EmployeeController;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeView {

	EmployeeController empController;
	
	public EmployeeView() {
		empController = new EmployeeController();
	}
	
	public void startApp() {
		end : 
		while(true) {
			int menu = mainMenu();
			switch(menu) {
			case 1 :
				List<Employee> eList = empController.printAllEmp();
				this.showAllEmp(eList);
				break;
			case 2 :
				Employee emp = inputEmpInfo();
				int result = empController.insertEmployee(emp);
				if (result > 0) {
					printMessage("��� ����!");
				} else {
					printMessage("��� ����!");
				}
				break;
			case 3 :
				String empId = inputEmpId();
				emp = empController.selectOneById(empId);
				if (emp != null) {
					emp = modfiyEmpInfo();
					emp.setEmpId(empId);
					// ���� ����
					result = empController.updateEmployee(emp);
					if (result > 0) {
						printMessage("���� ����!");
					}
				} else {
					printMessage("�������� �ʴ� �����Դϴ�.");
				}
				break;
			case 4 :
				empId = inputEmpId();
				result = empController.deleteEmployee(empId);
				if (result > 0) {
					printMessage("���� ����!");
				} else {
					printMessage("���� ����!");
				} break;
			case 0 :
				printMessage("���α׷��� ����Ǿ����ϴ�.");
				break end;
			}
		}
		
	}

	private String inputEmpId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("��� �Է� : ");
//		String empId = sc.next(); �̰� �Ⱦ��� return���� sc.next()���� ���� �ִ�.
		return sc.next();
	}

	private Employee inputEmpInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== ��� ���� ��� ======");
		System.out.print("��� : ");
		String empId = sc.next();
		System.out.print("����� : ");
		String empName = sc.next();
		System.out.print("�ֹε�Ϲ�ȣ : ");
		String empNo = sc.next();
		System.out.print("�����ڵ� : ");
		String jobCode = sc.next();
		System.out.print("�޿���� : ");
		String salLevel = sc.next();
		// �⺻ ������ �����ϰ� set �޼ҵ� �̿��ؼ� �������ִ� ��� 
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setEmpNo(empNo);
		emp.setJobCode(jobCode);
		emp.setSalLevel(salLevel);
		return emp;
	}
	
	private Employee modfiyEmpInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== ��� ���� ���� ======");
		System.out.print("�̸��� : ");
		String email = sc.next();
		System.out.print("��ȭ��ȣ : ");
		String phone = sc.next();
		System.out.print("�μ��ڵ� : ");
		String deptCode = sc.next();
		System.out.print("�޿� : ");
		int salary = sc.nextInt();
		System.out.print("���ʽ��� : ");
		double bonus = sc.nextDouble();
		System.out.print("������ : ");
		String managerId = sc.next();
		Employee emp = new Employee();
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setDeptCode(deptCode);
		emp.setSalary(salary);
		emp.setBonus(bonus);
		emp.setManagerId(managerId);
		return emp;
	}

	private void showAllEmp(List<Employee> eList) {
		System.out.println(" ====== ��� ���� ��ü ��� ======");
		for(Employee emp : eList) {
			System.out.printf("������ :%s, ��� : %s, �̸��� : %s, ��ȭ��ȣ :%s, �Ի����� : %s, �������� : %s\n"
					, emp.getEmpName(), emp.getEmpId(), emp.getEmail()
					, emp.getPhone(), emp.getHireDate(), emp.getEntYn());
		}
	}

	private void printMessage(String message) {
		System.out.println("[���� ���] : "+message);
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ====== ��� ���� ���α׷� ======");
		System.out.println("1. ��� ��ü ���");
		System.out.println("2. ��� ���");
		System.out.println("3. ��� ���� ����");
		System.out.println("4. ��� ����");
		System.out.println("0. ���α׷� ����");
		System.out.print("�޴� ���� : ");
		int choice = sc.nextInt();
		return choice;
	}

}
