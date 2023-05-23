package com.yedam.member.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.member.domain.EventVO;
import com.yedam.member.service.EventService;
import com.yedam.member.service.EventServiceImpl;

public class RemoveEventControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		
		EventVO vo = new EventVO();
		vo.setTitle(title);
		EventService service = new EventServiceImpl();
		boolean result = service.removeEvent(vo);
		
		String json = "";
		
		Map<String, Object> map = new HashMap<>();
		
		if(result) {
			map.put("retCode", "Success");
			
		} else {
			map.put("retCode", "Fail");
		}
		
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(map);
		
		return json + ".json";
	}
	
}
