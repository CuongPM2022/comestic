package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.service.IStatisticService;
import com.comestic.utils.Check;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.Paging;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-statistic"})
public class AdminStatisticAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IStatisticService statisticService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		if(Check.isLogin(req)) {
			Paging paging = (new HttpUtil(req.getParameter("paging"))).toModel(Paging.class);
			mapper.writeValue(resp.getOutputStream(), statisticService.getStatistic(paging));
		}
		else {
			mapper.writeValue(resp.getOutputStream(), null);
		}
	}

}
