package com.yedam.member.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.member.domain.MemberVO;

public interface MemberMapper {
	public MemberVO loginCheck(MemberVO vo);
	public MemberVO searchMember(String memberId);
	public int updateMember(MemberVO vo);
	
	public List<Map<String, Object>> memberByDept();
}
