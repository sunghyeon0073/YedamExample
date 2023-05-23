package com.yedam.member.service;

import java.util.List;

import com.yedam.member.domain.EventVO;

public interface EventService {
	// 이벤트목록
	public List<EventVO> events();
	// 이벤트등록
	public boolean addEvent(EventVO event);
	// 이벤트삭제
	public boolean removeEvent(EventVO event);
}
