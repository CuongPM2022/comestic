package com.comestic.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.utils.Check;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String id = req.getParameter("id");
		String path = "";
		if(action != null) {
			if(action.equals("productDetail") && id!= null && Check.isLong(id)) {
				path = "/views/web/jsp/productDetail.jsp";
				req.setAttribute("id", id);
			}
			if(action.equals("allProduct")) {
				path = "/views/web/jsp/allProduct.jsp";
				req.setAttribute("categoryId", req.getParameter("categoryId"));
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
		
	}

}
