package com.comestic.utils;

import javax.servlet.http.HttpServletRequest;

public class Check {
	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		boolean isLogin = false;
		Long userId = (Long) SessionUtil.getValue(request, "userId");
		if(userId != null) {
			isLogin = true;
		}
		return isLogin;
	}
}
