package com.bs.lec21.member.dao;

import com.bs.lec21.member.Member;

import java.util.Map;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	Member memberUpdate(Member member);
	Map<String, Member> memberDelete(Member member);
}
