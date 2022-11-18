package com.comestic.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.utils.Check;

@WebServlet(urlPatterns = {"/admin-bill"})
public class BillController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String path = "";
		if(action != null) {
			if(action.equals("billDetail")) {
				String id = req.getParameter("billId");
				if(id != null && Check.isLong(id)) {
					path = "/views/admin/jsp/billDetail.jsp";
					req.setAttribute("billId", id);
				}
			}
			else if(action.equals("listBill")) {
				path = "/views/admin/jsp/listBill.jsp";
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
	}

}
