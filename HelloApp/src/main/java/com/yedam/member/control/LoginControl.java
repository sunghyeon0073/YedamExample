package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;

public class LoginControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// MemberService/ MemberServiceImpl, MemberMapper.java(.xml)
		// service: MemberVO loginCheck(MemberVO vo)
		// mapper: MemberVO loginCheck(MemberVO vo)

		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		MemberVO vo = new MemberVO();
		vo.setEmail(email);
		vo.setPassword(pass);
		
		MemberService service = new MemberServiceImpl();
		vo = service.loginCheck(vo);

		if (vo != null) {
//			req.setAttribute("loginInfo", vo); // 타겟화면 jsp에서 사용하기 위한 용도 사용법 jsp 화면에서 loginInfo.get...
			
			// 세션에 로그인정보 지정. 
			HttpSession session = req.getSession(); // 다른 화면에 넘어갔을 때에도 로그인 정보 유지하기 위함
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("name", vo.getName());
			
			return "noticeList.do";
		} else {
			return "loginForm.do";
		}

	}

}
