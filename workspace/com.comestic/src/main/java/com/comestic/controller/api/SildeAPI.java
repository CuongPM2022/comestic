package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.service.ISlideService;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.Paging;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-slide"})
public class SildeAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	ISlideService slideService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Paging paging = (new HttpUtil(req.getParameter("paging"))).toModel(Paging.class);
		ObjectMapper mapper = new ObjectMapper();
		
		String action = req.getParameter("action");
		if(action == null) {
			mapper.writeValue(resp.getOutputStream(), null);
		}
		
		if(action.equals("allSlide")) {
			mapper.writeValue(resp.getOutputStream(), slideService.findAll(paging));
		}
		
	}

}
