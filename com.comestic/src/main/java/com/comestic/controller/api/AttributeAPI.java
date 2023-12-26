package com.comestic.controller.api;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.model.AttributeModel;
import com.comestic.service.IAttributeService;
import com.comestic.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-attribute"})
public class AttributeAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IAttributeService attributeService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		
		String action = req.getParameter("action");
		if(action != null) {
			if(action.equals("allAttribute")) {
				mapper.writeValue(resp.getOutputStream(), attributeService.findAll());
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String action = req.getParameter("action");
		if(action.equals("list")) {
			List<AttributeModel> listAttribute = List.of(HttpUtil.of(req.getReader()).toModel(AttributeModel[].class));
			if(listAttribute != null) {
				listAttribute = attributeService.saveAll(listAttribute);
			}
			mapper.writeValue(resp.getOutputStream(), listAttribute);
		}
	}

}
