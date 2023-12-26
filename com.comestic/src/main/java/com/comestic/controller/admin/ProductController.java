package com.comestic.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-product"})
public class ProductController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String path = "";
		RequestDispatcher rd;
		if(action != null) {
			if(action.equals("allProduct")) {
				path = "/views/admin/jsp/listProduct.jsp";
			}
			else if(action.equals("createProduct")) {
				String id_str = req.getParameter("id");
				if(id_str != null) {
					req.setAttribute("id", id_str);
				}
				path = "/views/admin/jsp/createProduct.jsp";
			}
			rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
	}

}
