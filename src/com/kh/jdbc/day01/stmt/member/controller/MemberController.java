package com.kh.jdbc.day01.stmt.member.controller;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day01.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public void insertMember(Member member) {
		mDao.insertMember(member);
	}
	
	public List<Member> listMember() {
		return mDao.selectList();
	}

	public Member printOneMember(String memberId) {
		// member을 쿼리문에서 받아야하므로 mDao에서 select한 정보를 넣어준다.
		Member member = mDao.selectOne(memberId);
		return member;
		// return mDao.selectOne(memberId); 이렇게 해도 됨.
	}
}
