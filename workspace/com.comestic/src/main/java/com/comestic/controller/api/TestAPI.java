package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.dao.ICartItemDAO;
import com.comestic.service.ICartService;
import com.comestic.service.IProductService;
import com.comestic.service.IStatisticService;
import com.comestic.service.IVarietyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-test"})
public class TestAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	IVarietyService varietyService;
	
	@Inject
	IProductService productService;
	
	@Inject
	ICartItemDAO cartItemDAO;
	
	@Inject
	ICartService cartService;
	
	@Inject
	IStatisticService statisticService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), statisticService.getTotalAccess());
	}
}
