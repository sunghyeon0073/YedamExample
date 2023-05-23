package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ModifyReplyControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터(댓글번호, 변경된 댓글내용)
		// update.
		
		// search.
		// String id = req.getParameter("id");
		String rid = req.getParameter("rid");
		String reply = req.getParameter("reply");
		
		boolean result = false;
		ReplyService service = new ReplyServiceImpl();
		ReplyVO vo = service.searchReply(Integer.parseInt(rid));
		
		//vo.setReplyWriter(id);
		vo.setReply(reply);
				
		
		result = service.modifyReply(vo);
		
		
		String json = "";
		
		Map<String, Object> map = new HashMap<>();
		
		if(result) {
			map.put("retCode", "Success");
			map.put("data", vo);
			
		} else {
			map.put("retCode", "Fail");
		}
		
		Gson gson = new GsonBuilder().create(); // gson 객체.
		json = gson.toJson(map);
		
		return json + ".json";
	}

}
