package com.kh.jdbc.day03.pstmt.member.view;

import java.util.Scanner;

import com.kh.jdbc.day03.pstmt.member.controller.MemberController;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberView {

	MemberController mController;
	
	// ������ �ȿ��� �ʱ�ȭ
	public MemberView() {
		mController = new MemberController();
	}
	
	public void startProgram() {
		finish :
		while(true) {
			int choice = this.mainMenu();
			switch(choice) {
				case 1 : 
					// ȸ�������� �Է¹��� ��
					// �Է¹��� ������ ��ü ������ ��
					Member member = this.inputInfo();
					// ��ü�� ��Ʈ�ѷ��� ����
					int result = mController.registerMember(member);
					if(result > 0) {
						printMessage("ȸ������ ����!");
					} else {
						printMessage("ȸ������ ����!");
					}
					break;
				case 2 : 
					member = this.inputLoginInfo();	// ������ Member member �����߱� ������ member�� �ᵵ ��
					// �Է��� ID�� PW�� DB�� �ִ°�?
					member = mController.checkLogin(member);
					if(member != null) {
						this.printOneMember(member);
					} else {
						printMessage("�������� �ʴ� �����Դϴ�.");
					}
					break;
				case 3 : 
					String memberId = inputMemberId();
					member = mController.checkMember(memberId);
					if (member != null) {
						// �����ϱ�
						member = modifyMember();
						member.setMemberId(memberId);
						result = mController.updateMember(member);
						if (result > 0) {
							printMessage("���� ���� ����!");
						}
					} else {
						printMessage("�������� �ʴ� �����Դϴ�.");
					}
					break;
				case 4 : 
					memberId = inputMemberId();
					member = mController.checkMember(memberId);
					if (member != null) {
						result = mController.removeMember(memberId);
						if (result > 0) {
							printMessage("���� ����!");
						}
					} else {
						printMessage("�������� �ʴ� �����Դϴ�.");
					}
					// �ؿ��� ���� �Ѱ� .. ������ check �־
//					result = mController.removeMember(memberId);
//					if(result > 0) {
//						printMessage("ȸ��Ż�� ����!");
//					} else {
//						printMessage("ȸ��Ż�� ����!");
//					}
					break;
				case 5 : break;
				case 6 : break;
				case 9 : 
					printMessage("���α׷� ����");	// this �� ��� ��
					break finish;
			}
		}
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= ȸ�� ���� ���α׷� =======");
		System.out.println("1. ȸ������");
		System.out.println("2. �α���");
		System.out.println("3. ȸ�� ���� ����");
		System.out.println("4. ȸ�� Ż��");
		System.out.println("9. ���α׷� ����");
		System.out.print("�޴� ���� : ");
		int input = sc.nextInt();
		// ȣ���ϴ� ������ ���ϱ� return input;
		return input;
	}

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���̵� �Է� : ");
		String memberId = sc.next();
		return memberId;
	}

	private Member modifyMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= ȸ�� ���� ���� =======");
		System.out.print("��й�ȣ : ");
		String memberPw = sc.next();
		System.out.print("�̸��� : ");
		String email = sc.next();
		System.out.print("��ȭ��ȣ : ");
		String phone = sc.next();
		System.out.print("�ּ� : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("��� : ");
		String hobby = sc.nextLine();
		Member member = new Member(memberPw, email, phone, address, hobby);
		// �̰͵��� �ʵ�� ������ member ������ ���� �����ؾ��Ѵ�. 
		return member;
	}

	private void printOneMember(Member member) {
		System.out.println("======= ȸ�� ���� ��� =======");
		System.out.printf("�̸� : %s\t ���� : %d\t ���̵� : %s\t ���� : %s\t �̸��� : %s\t"
					+" ��ȭ��ȣ : %s\t �ּ� : %s\t ��� : %s\t"
					+" ���Գ�¥ : %s\n"
					, member.getMemberName()
					, member.getAge()
					, member.getMemberId()
					, member.getGender()
					, member.getEmail()
					, member.getPhone()
					, member.getAddress()
					, member.getHobby()
					, member.getRegDate());
		
	}

	private Member inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= �α��� ���� �Է� =======");
		System.out.print("���̵� : ");
		String memberId = sc.next();
		System.out.print("��й�ȣ : ");
		String memberPw = sc.next();
		// ���� �������� �ȵǴϱ� ��ü �̿�
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		return member;
	}

	private Member inputInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= ȸ�� ���� �Է� =======");
		System.out.print("���̵� : ");
		String memberId = sc.next();
		System.out.print("��й�ȣ : ");
		String memberPw = sc.next();
		System.out.print("�̸� : ");
		String memberName = sc.next();
		System.out.print("���� : ");
		String gender = sc.next();
		System.out.print("���� : ");
		int age = sc.nextInt();
		System.out.print("�̸��� : ");
		String email = sc.next();
		System.out.print("��ȭ��ȣ : ");
		String phone = sc.next();
		System.out.print("�ּ� : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("��� : ");
		String hobby = sc.nextLine();
		// ���� �������� �ȵǴϱ� ��ü �̿�
		// �Ű����� ������ ��� -> ���ٷ� ����ϰ� 
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, address, hobby);
		return member;
	}

	private void printMessage(String message) {
		System.out.println("[���] : "+message);
	}
}
