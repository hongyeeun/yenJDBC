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
//		mController.insertMember(); -- 삽입메소드
		int choice = 0;
		끝:
		while(true) {
			choice = this.printMainMenu();
			switch(choice) {
			case 0 : break 끝;
			case 1 : 
				Member member = this.inputMember();
				mController.insertMember(member);
				break;
			case 2 : 
				List<Member> mList = mController.listMember(); // mList생성하고 mController부터 받아서 넣어줌
				this.displayMemberList(mList);
				break;
			case 3 : 
				String memberId = this.inputMemberId();	//memberId의 값은 input메소드로 선언해준다.
				member = mController.printOneMember(memberId);
				this.displayOne(member);
				break;
			default : this.displayMessage("메뉴를 다시 선택해주세요");
			}
		}
		
	}
	
	private String inputMemberId() {
		System.out.print("회원의 아이디로 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		String memberId = sc.next();
		return memberId;
	}

	private void displayOne(Member member) {
		System.out.println(" == 회원 정보 출력 (아이디로 검색_ ==");
		System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s, 이메일 : %s"
				+", 전화번호 : %s, 주소 : %s, 취미 : %s"
				+", 가입날짜 : %s\n"
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

		System.out.println("==== 회원 정보 입력 ====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();	
		// 전화번호에서 들어간 엔터를 여기서 입력받아야한다. 
		// next와 nextLine같이 쓰일 때 next와 nextLine사이에 sc.nextLine으로 해줘야한다. (기억하긔)
		String address = sc.nextLine();	// 띄어쓰기가 있으므로 nextLine으로 해줘야한다.
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		// 날짜는 무조건 sysdate로 들어가기때문에 따로 입력해줄 필요 x
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
		System.out.println("==== 회원 관리 프로그램 ====");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디로 조회)");
		System.out.print("메뉴 선택 : ");
		int value = sc.nextInt();
		return value;
	}

	public void displayMemberList(List<Member> mList) {
		System.out.println("==== 회원 정보 전체 출력 ====");
		// mList의 전체를 출력한다.
		for(Member member : mList) {
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s, 이메일 : %s"
					+", 전화번호 : %s, 주소 : %s, 취미 : %s"
					+", 가입날짜 : %s\n"
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
