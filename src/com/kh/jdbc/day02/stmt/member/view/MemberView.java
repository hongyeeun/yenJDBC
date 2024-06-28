package com.kh.jdbc.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.stmt.member.controller.MemberController;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {

	MemberController mController; 	// View Ŭ�������� ��� ���Ŵ� �ʵ�� �д�.
	
	public MemberView() {
		// �⺻�����ڿ��� �ʱ�ȭ����
		mController = new MemberController(); 
	}
	public void startProgram() {	
		end :
		while(true) {
			int value = this.printMainMenu();
			switch(value) {
				case 1 :
					// 1�� �����ٸ� ȸ���� ������ �Է¹޾ƾ� ��
					Member member = this.inputMember(); // inputMember()�� ������� ���� �ޱ�
					// ID���� ��̱��� ����� member ��ü�� ��Ʈ�ѷ��� ����
					mController.insertMember(member);
					break;
				case 2 :
					// 2�� �����ٸ� ȸ���� ��ü ������ ����ؾ���
					// 1. ��񿡼� ������ ��������, ��ü ȸ�� �����ϱ� ������, �������ϱ� ����Ʈ List, ����ϱ� List<Member>
					List<Member> mList = mController.printAllMember();
					// 2. view�� �޼ҵ带 �̿��ؼ� ����ϱ�
					this.printAllMembers(mList);
					break;
				case 3 : 
					// 3�� �����ٸ� ȸ���� ������ �˻��ؾ� �� (���̵�� �˻�)
					// ����ڰ� �˻��� ���̵� �Է¹޾ƾ� �Ǵϱ� inputMember();
					String memberId = this.inputMemberId();
					// �Է¹��� ���̵� ��񿡼� �˻��� �;� �Ǵϱ� printOneMember()
					// ��Ʈ�ѷ��� �����ؾ� �Ǵϱ� printOneMember(memberId);
					member = mController.printOneMember(memberId);
					// ��񿡼� ������ ���� ����ؾ� �Ǵϱ� printOneMember
					this.printOneMember(member);
					break;
				case 4 :
					// 4�� �����ٸ� ȸ���� ������ �����ؾ��� (���̵�� ������ �����ϴ��� Ȯ�� �� ������ ���� ������ ����)
					// ����ڰ� ������ ���̵� �Է¹޾ƾ� �Ǵϱ� inputMember();
					memberId = inputMemberId();
					// �����ϴ� ������ ���������� Ÿ�ߵǴϱ�, printOneMember()ȣ��
					// memberId�����ؾߵǴϱ� printOneMember(memberId);
					// DB���� ������ �� �����ؾ� �Ǵϱ� member = mController.printOneMember(memberId);
					member = mController.printOneMember(memberId);
					// DB���� �����͸� �����Դ��� üũ�ؾߵǴϱ� if(member != null)
					// �����Ͱ� ���ٸ� member�� null�� ����.
					if(member != null) {
						// ������ ������ ������ ������ �Է��ؾߵǴϱ� modifyMember(member); member�� �����ؾ���.
						// �� ������ ������ ������ �ִ� member ��ü�� �ʿ���. 
						Member modifyInfo = this.inputModifyInfo();
						// UPDATE�� �� ������ ���� �߿��� ���� WHERE �������̴ϱ�, WHERE�� �� �����͸� ��������� ��.
						// modifyMember(member) ���� modifyInfo�� memberId�� �� �־��־�� �ϴϱ� modifyInfo.setMemberId(memberId);
						modifyInfo.setMemberId(memberId);
						// DML�� ����� int�ϱ� int result
						int result = mController.modifyMember(modifyInfo);
						if(result > 0) {
							this.displayMessage("���� ����!");
						} else {
							this.displayMessage("���� ����!");
						}
					} else {
						this.displayMessage("�������� �ʴ� �����Դϴ�.");
					}
					break;
				case 5:
					// 5�� �����ٸ� ȸ���� ������ �����ؾ���(���̵�� ����)
					// ����ڰ� ������ ���̵� �Է¹޾ƾ� �Ǵϱ� printOneMember()
					memberId = this.inputMemberId();
					// �����ϴ� ������ ���������� Ÿ�ߵǴϱ�, printOneMember()ȣ��
					// memberId�����ؾߵǴϱ� printOneMember(memberId);
					// DB���� ������ �� �����ؾ� �Ǵϱ� member = mController.printOneMember(memberId);
					member = mController.printOneMember(memberId);
					// DB���� �����͸� �����Դ��� üũ�ؾߵǴϱ� if(member != null)
					// �����Ͱ� ���ٸ� member�� null�� ����.
					if(member != null) {
						// �Է¹��� ���̵�� ��񿡼� ���� �ؾߵǴϱ� removeMember();
						// ��Ʈ�ѷ��� �����ؾ� �Ǵϱ� removeMember(memberId);
						// DML�� ����� int�ϱ� int result
						int result = mController.removeMember(memberId);
						if(result > 0) {
							this.displayMessage("���� ����!");
						} else {
							this.displayMessage("���� ����!");
						}
					} else {
						this.displayMessage("�������� �ʴ� �����Դϴ�.");
					}
					break;
				case 0 : break end;
			}
		}
	}

	// MemverView : 54, 56
	private void displayMessage(String msg) {
		System.out.println("[���� ���] : "+msg);		
	}
	private void printOneMember(Member member) {
		System.out.println("==== ȸ�� ���� ��� ====");
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
		
	
	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���̵� �Է� : ");
		String memberId = sc.next();
		// ȣ���� ������ ��ߵǴϱ� return memberId, MemberView:38
		return memberId;
	}
	// MemberView:36
	private void printAllMembers(List<Member> mList) {
		System.out.println("==== ȸ�� ���� ��ü ��� ====");
		// mList�� ��ü�� ����Ѵ�.
		for(Member member : mList) {
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
	}
	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== ȸ�� ���� ��� =====");
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
		String hobby = sc.next();
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
	
	private Member inputModifyInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== ȸ�� ���� ���� =====");
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
		String hobby = sc.next();
		Member member = new Member();
		member.setMemberPw(memberPw);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setHobby(hobby);
		return member;
	}
	
	public int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== ȸ�� ���� ���α׷� =====");
		System.out.println("1. ȸ������");
		System.out.println("2. ��ü ȸ�� ��ȸ");
		System.out.println("3. ȸ�� �˻�");
		System.out.println("4. ȸ�� ���� ����");
		System.out.println("5. ȸ�� Ż��");
		System.out.println("0. ����");
		System.out.print("�޴����� : ");
		int choice = sc.nextInt();
		return choice; // �ٸ��޼ҵ忡�� �� �� �ֵ��� ����
	}
}
