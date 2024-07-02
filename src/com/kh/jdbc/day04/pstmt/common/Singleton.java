package com.kh.jdbc.day04.pstmt.common;

public class Singleton {
	// static�̸鼭 Ŭ����Ÿ���� ������� �ʿ�
	private static Singleton instance;	
	
	// static�̸鼭 public�̰� ����Ÿ���� Singleton�� �޼ҵ� �ʿ�
	// �޼ҵ� �ȿ����� if������ ��üũ �� Null�̸� ��ü ����
	// Null�� �ƴϸ� �״�� ����
	public static Singleton getInstance() {
		if (instance == null) {
			// null���̸� ��ü�� ��������
			instance = new Singleton();
		}
		return instance;
		// return ���� instance 
	}
}
