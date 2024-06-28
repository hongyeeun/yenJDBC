package com.kh.jdbc.day01.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day01.stmt.member.controller.MemberController;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberView {
	MemberController mController;
	Scanner sc = new Scanner(System.in);
	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() {
		// TODO Auto-generated method stub
//		mController.insertMember(); -- ���Ը޼ҵ�
		int choice = 0;
		��:
		while(true) {
			choice = this.printMainMenu();
			switch(choice) {
			case 0 : break ��;
			case 1 : 
				Member member = this.inputMember();
				mController.insertMember(member);
				break;
			case 2 : 
				List<Member> mList = mController.listMember(); // mList�����ϰ� mController���� �޾Ƽ� �־���
				this.displayMemberList(mList);
				break;
			case 3 : 
				String memberId = this.inputMemberId();	//memberId�� ���� input�޼ҵ�� �������ش�.
				member = mController.printOneMember(memberId);
				this.displayOne(member);
				break;
			default : this.displayMessage("�޴��� �ٽ� �������ּ���");
			}
		}
		
	}
	
	private String inputMemberId() {
		System.out.print("ȸ���� ���̵�� �Է��ϼ��� : ");
		Scanner sc = new Scanner(System.in);
		String memberId = sc.next();
		return memberId;
	}

	private void displayOne(Member member) {
		System.out.println(" == ȸ�� ���� ��� (���̵�� �˻�_ ==");
		System.out.printf("�̸� : %s, ���� : %d, ���̵� : %s, ���� : %s, �̸��� : %s"
				+", ��ȭ��ȣ : %s, �ּ� : %s, ��� : %s"
				+", ���Գ�¥ : %s\n"
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

	private Member inputMember() {

		System.out.println("==== ȸ�� ���� �Է� ====");
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
		// ��ȭ��ȣ���� �� ���͸� ���⼭ �Է¹޾ƾ��Ѵ�. 
		// next�� nextLine���� ���� �� next�� nextLine���̿� sc.nextLine���� ������Ѵ�. (����ϱ�)
		String address = sc.nextLine();	// ���Ⱑ �����Ƿ� nextLine���� ������Ѵ�.
		System.out.print("��� : ");
		String hobby = sc.nextLine();
		// ��¥�� ������ sysdate�� ���⶧���� ���� �Է����� �ʿ� x
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setAge(age);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setHobby(hobby);
		return member;
	}

	private void displayMessage(String msg) {
		System.out.println(msg);
	}

	private int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("==== ȸ�� ���� ���α׷� ====");
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ�� ��ü ��ȸ");
		System.out.println("3. ȸ�� �˻�(���̵�� ��ȸ)");
		System.out.print("�޴� ���� : ");
		int value = sc.nextInt();
		return value;
	}

	public void displayMemberList(List<Member> mList) {
		System.out.println("==== ȸ�� ���� ��ü ��� ====");
		// mList�� ��ü�� ����Ѵ�.
		for(Member member : mList) {
			System.out.printf("�̸� : %s, ���� : %d, ���̵� : %s, ���� : %s, �̸��� : %s"
					+", ��ȭ��ȣ : %s, �ּ� : %s, ��� : %s"
					+", ���Գ�¥ : %s\n"
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
	}
}
