package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NoticeService service = new NoticeServiceImpl();
		// GET 방식 요청
		if (req.getMethod().equals("GET")) {
			String nid = req.getParameter("nid");
			
			NoticeVO vo = service.getNotice(Integer.parseInt(nid));
			req.setAttribute("noticeInfo", vo);
			
			return "notice/noticeModify.tiles";
		// POST 방식 요청.
		} else if (req.getMethod().equals("POST")) {
			String nid = req.getParameter("nid");
			String subject = req.getParameter("subject");
			String title = req.getParameter("title");
			
			NoticeVO vo = new NoticeVO();
			vo.setNoticeId(Integer.parseInt(nid));
			vo.setNoticeTitle(title);
			vo.setNoticeSubject(subject);
			
			service.modifyNotice(vo);
		}
		return "noticeList.do";
	}
}
