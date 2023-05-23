package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;

public class modifyMemberControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberServiceImpl();
		if (req.getMethod().equals("GET")) {
//			String id = req.getParameter("id");
			String id = (String)req.getSession().getAttribute("id");
			System.out.println(id);
			MemberVO vo = service.getMember(id);
			System.out.println(vo);
			req.setAttribute("memberInfo", vo);
			return "member/memberModify.tiles";
			
		} else if (req.getMethod().equals("POST")){
			String id = req.getParameter("email");
			String pass = req.getParameter("password");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			
			MemberVO vo = new MemberVO();
			vo.setEmail(id);
			vo.setPassword(pass);
			vo.setPhone(phone);
			vo.setAddress(address);
			service.modifyMember(vo);
			
			return "noticeList.do";
		}
		return null;
	}

}
