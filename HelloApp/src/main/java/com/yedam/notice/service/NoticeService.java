package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.domain.NoticeVO;

public interface NoticeService {
	// C(reate)R(ead)U(pdate)D(elete)
	public List<NoticeVO> noticeList(int page);
	
	public boolean addNotice(NoticeVO vo);
	
	public boolean modifyNotice(NoticeVO vo);
	
	public boolean modifyNoticeFile(NoticeVO vo); //파일정보변경
	
	public boolean removeNotice(int noticeId);
	
	public NoticeVO getNotice(int noticeId);
	
	public int totalCount();
	// json
	public List<NoticeVO> noticeListJson();
}
