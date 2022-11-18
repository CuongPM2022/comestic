package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.service.IProductService;
import com.comestic.utils.Check;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.Paging;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-product"})
public class ProductAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IProductService productService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Paging paging = (new HttpUtil(req.getParameter("paging"))).toModel(Paging.class);
		ObjectMapper mapper = new ObjectMapper();
		
		String action = req.getParameter("action");
		if(action.equals("allProduct")) {
			mapper.writeValue(resp.getOutputStream(), productService.findAll(paging));
		}
		else if(action.equals("sellProduct")) {
			mapper.writeValue(resp.getOutputStream(), productService.finByBestSeller(paging));
		}
		else if(action.equals("detailProduct")) {
			String id = req.getParameter("id");
			if(id != null && Check.isLong(id)) {
				mapper.writeValue(resp.getOutputStream(), productService.findOne(Long.parseLong(id)));
			}
		}
		else {
			mapper.writeValue(resp.getOutputStream(), null);
		}
		
	}
	
}
