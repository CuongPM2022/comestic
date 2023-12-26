package com.comestic.controller.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.service.IStatisticService;
import com.comestic.utils.SessionUtil;

public class Authorization implements Filter {

	@Inject
	IStatisticService statisticService;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		while(url.contains("//")) {
			url = url.replace("//", "/");
		}
		
		if(!url.startsWith("/api-")) {
			statisticService.updateTotalAccess();
		}
		
		if(url.startsWith("/admin")) {
			Long userId = (Long) SessionUtil.getValue(request, "userId");
			if(userId != null) {
				chain.doFilter(servletRequest, servletResponse);
			}
			else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
		else if(url.startsWith("/login")) {
			Long userId = (Long) SessionUtil.getValue(request, "userId");
			if(userId != null) {
				response.sendRedirect(request.getContextPath() + "/admin-home");
			}
			else {
				chain.doFilter(servletRequest, servletResponse);
			}
		}
		else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

}
