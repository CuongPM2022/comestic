package com.comestic.controller.api;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.model.CategoryModel;
import com.comestic.service.ICategoryService;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.Paging;
import com.comestic.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-category"})
public class CategoryAPI extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Paging paging = (new HttpUtil(req.getParameter("paging"))).toModel(Paging.class);
		ObjectMapper mapper = new ObjectMapper();
		
		String action = req.getParameter("action");
		if(action.equals("allCategory")) {
			mapper.writeValue(resp.getOutputStream(), categoryService.findAll(paging));
		} else if(action.equals("listParent")) {
			mapper.writeValue(resp.getOutputStream(), categoryService.findListParent());
		} else if(action.equals("listHasNotChild")) {
			mapper.writeValue(resp.getOutputStream(), categoryService.findListHasNotChild());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		if(categoryModel != null) {
			categoryModel.setUserId((Long) SessionUtil.getValue(req, "userId"));
			mapper.writeValue(resp.getOutputStream(),
					          categoryService.save(categoryModel, req.getServletContext().getRealPath("/"))
					          );
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
		if(categoryModel != null) {
			categoryModel.setUserId((Long) SessionUtil.getValue(req, "userId"));
			mapper.writeValue(resp.getOutputStream(), 
							  categoryService.save(categoryModel, req.getServletContext().getRealPath("/"))
							  );
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		List<Long> listId = List.of(new HttpUtil(req.getParameter("listId")).toModel(Long[].class));
		mapper.writeValue(resp.getOutputStream(), categoryService.deleteList(listId));
	}
	
}
