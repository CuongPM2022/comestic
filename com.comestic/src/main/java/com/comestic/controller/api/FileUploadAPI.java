package com.comestic.controller.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.service.IFileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-file-upload"})
@MultipartConfig
public class FileUploadAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IFileUploadService fileUploadService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String type = req.getParameter("type");
		if(type != null) {
			Map<String, List<Long>> listResult = 
					fileUploadService.save(req.getParts(), req.getServletContext().getRealPath("/"), type);
			mapper.writeValue(resp.getOutputStream(), listResult);
		}
	}
	
}
