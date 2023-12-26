package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.model.ManufactureModel;
import com.comestic.service.IManufactureService;
import com.comestic.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-manufacture"})
public class ManufactureAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IManufactureService manufactureService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String action = req.getParameter("action");
		if(action != null) {
			if(action.equals("allItem")) {
				mapper.writeValue(resp.getOutputStream(), manufactureService.findAll());
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		
		ManufactureModel manufactureModel = HttpUtil.of(req.getReader()).toModel(ManufactureModel.class);
		if(manufactureModel != null) {
			manufactureModel = manufactureService.save(manufactureModel);
		}
		mapper.writeValue(resp.getOutputStream(), manufactureModel);
	}

}
