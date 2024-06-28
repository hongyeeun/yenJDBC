package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {

	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public void insertMember(Member member) {
		mDao.insertMember(member);
	}
	
	public int modifyMember(Member modifyInfo) {
		// DML�� ����� int�ϱ� int result
		// memberId �����ؾߵǴϱ� updateMember(modifyInfo)
		int result = mDao.updateMember(modifyInfo);
		// ȣ���� ������ ��ߵǴϱ� return result
		return result;
	}
	
	// View���� memberId�� �޾ƾ��ϴϱ� removeMember(String memberId)
	// return�ϴ� ���� �ڷ����� int�ϱ� void��� int
	public int removeMember(String memberId) {
		// DML�� ����� int�ϱ� int result
		// memberId �����ؾߵǴϱ� deleteMember(memberId)
		int result = mDao.deleteMember(memberId);
		// ȣ���� ������ ��ߵǴϱ� return result, MemberView:52
		return result;
	}
	
	public List<Member> printAllMember() {
		// �������ϱ� List, ����ϱ� List<Member>
		List<Member> mList = mDao.selectList();
		// ȣ���� ������ ��ߵǴϱ� return
		return mList;
	}
	// View�� �� �� �޾ƾ� �Ǵϱ� printOneMember(String memberId)
	public Member printOneMember(String memberId) {
		// �Ѱ��ϱ� List��� ��, Member
		Member member = mDao.selectOne(memberId);
		// ȣ���� ������ ��ߵǴϱ� return member, MemberView:41
		return member;
	}
}
