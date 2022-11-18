package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.model.UserModel;
import com.comestic.service.IUserService;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	IUserService userService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		Long userId = null;
		ObjectMapper mapper = new ObjectMapper();
		if(action != null) {
			if(action.equals("login")) {
				UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
				userId = userService.getUserIdByUserModel(userModel);
				if(userId != null) {
					SessionUtil.putValue(req, "userId", userId);
				}
			}
		}
		mapper.writeValue(resp.getOutputStream(), userId);
	}

}
