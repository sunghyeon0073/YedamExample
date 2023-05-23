package com.yedam.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService{
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return mapper.loginCheck(vo);
	}
	@Override
	public MemberVO getMember(String memberId) {
		return mapper.searchMember(memberId);
	}
	@Override
	public boolean modifyMember(MemberVO vo) {
			return mapper.updateMember(vo) == 1;
	}
	
	@Override
	public Map<String, Object> memberByDept() {
		Map<String, Object> result = new HashMap<>();
		
		List<Map<String, Object>> list = mapper.memberByDept();
		// [{Adminstration, 1}, {Accounting, 2}....{}]
		for(Map<String, Object> map : list) {
			Set<String> set = map.keySet();
			System.out.println(map.get("DEPARTMENT_NAME") + ", " + map.get("CNT"));
			result.put((String) map.get("DEPARTMENT_NAME"), map.get("CNT"));	
			
		}
		return result;
	}
}
