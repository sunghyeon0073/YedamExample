package com.yedam.notice.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class AddNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일업로드/ db 입력처리/ 목록이동.
		String job = req.getParameter("job");
		job = job == null ? "multi" : "ajax";
				
		if(job.equals("ajax")) {
			String title = req.getParameter("title");
			String subject = req.getParameter("subject");
			String writer = req.getParameter("writer");
			String attach = req.getParameter("attach");	// input:file 인 경우.
			
			// 사용자의 입력값을 NoticeVO 입력.
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(attach);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
				
			NoticeService service = new NoticeServiceImpl();
			// 정상처리 -> 목록이동.
			// map => {retCode:Success, retVal:vo}
			// map => {retCode:Fail, retVal:null}
			Map<String, Object> map = new HashMap<>();
			Gson gson = new GsonBuilder().create();
			if (service.addNotice(vo)) {
				map.put("retCode", "Success");
				map.put("retVal", vo);
				
//				return "Success.json";
			} else {
				map.put("retCode", "Fail");
				map.put("retVal", "알수없는 에러발생");
//				return "Fail.json";
			}
			return gson.toJson(map) + ".json";	// 객체 => json문자열.
			
		} else {	
			// 멀티파트요청: 요청정보, 저장경로, 최대파일사이즈, 인코딩, 리네임정책인스턴스.
			String saveDir = req.getServletContext().getRealPath("images");
			int maxSize = 5 * 1024 * 1024;
			String encoding = "UTF-8";
			DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
			MultipartRequest multi = new MultipartRequest(req, saveDir, maxSize, encoding, rn);
	
			Enumeration<?> enu = multi.getFileNames();
			while (enu.hasMoreElements()) {
				String file = (String) enu.nextElement();
				System.out.println("file : " + file);
			}
	
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String title = multi.getParameter("title");
			String attach = multi.getFilesystemName("attach");
			// 사용자의 입력값을 NoticeVO 입력.
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(attach);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			System.out.println(vo);
			NoticeService service = new NoticeServiceImpl();
			// 정상처리 -> 목록이동.
//			if (service.addNotice(vo)) {
//				return "noticeList.do";
//			} else {
//				return "main.do";
//			}
			
			Map<String, Object> map = new HashMap<>();
			Gson gson = new GsonBuilder().create();
			if (service.addNotice(vo)) {
				map.put("retCode", "Success");
				map.put("retVal", vo);
				
//				return "Success.json";
			} else {
				map.put("retCode", "Fail");
				map.put("retVal", "알수없는 에러발생");
//				return "Fail.json";
			}
			return gson.toJson(map) + ".json";	// 객체 => json문자열.
		}	// end of job().
	}	// end of method().
}	// end of class.
