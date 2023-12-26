package com.comestic.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comestic.model.CartModel;
import com.comestic.service.ICartService;
import com.comestic.utils.Check;
import com.comestic.utils.HttpUtil;
import com.comestic.utils.Paging;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-cart"})
public class CartAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	ICartService cartService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		String action = req.getParameter("action");
		String idVariety = req.getParameter("id");
		if(action != null) {
			if(action.equals("cartItem")) {
				if(idVariety != null && Check.isLong(idVariety)) {
					mapper.writeValue(resp.getOutputStream(), cartService.findOneItemCartByVarietyId(Long.parseLong(idVariety)));
				}
			}
			else if(action.equals("numberItem")) {
				if(idVariety != null && Check.isLong(idVariety)) {
					mapper.writeValue(resp.getOutputStream(), cartService.getNumberOfVarietyById(Long.parseLong(idVariety)));
				}
			}
			else if(action.equals("allItem")) {
				String userCart = req.getParameter("cart");
				if(userCart != null) {
					CartModel cartModel = (new HttpUtil(userCart)).toModel(CartModel.class);
					mapper.writeValue(resp.getOutputStream(), cartService.findOneByUserCart(cartModel));
				}
			}
			else if(action.equals("listCart")) {
				Paging paging = (new HttpUtil(req.getParameter("paging"))).toModel(Paging.class);
				mapper.writeValue(resp.getOutputStream(), cartService.findAll(paging));
			}
			else if(action.equals("billDetail")) {
				String id = req.getParameter("billId");
				if(id != null && Check.isLong(id)) {
					mapper.writeValue(resp.getOutputStream(), cartService.findOne(Long.parseLong(id)));
				}
			}
			else if(action.equals("listBillState")) {
				mapper.writeValue(resp.getOutputStream(), cartService.findAllBillState());
			}
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		
		String action = req.getParameter("action");
		if(action.equals("addNew")) {
			CartModel cartModel = HttpUtil.of(req.getReader()).toModel(CartModel.class);
			if(cartModel != null) {
				cartModel = cartService.save(cartModel);
				mapper.writeValue(resp.getOutputStream(), cartModel);
			}
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		ObjectMapper mapper = new ObjectMapper();
		if(action != null && Check.isLogin(req)) {
			if(action.equals("updateBillState")) {
				CartModel cartModel = HttpUtil.of(req.getReader()).toModel(CartModel.class);
				if(cartModel != null) {
					cartService.updateStateForBill(cartModel);
					mapper.writeValue(resp.getOutputStream(), true);
				}
			}
		}
		else {
			mapper.writeValue(resp.getOutputStream(), null);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		ObjectMapper mapper = new ObjectMapper();
		if(action != null && Check.isLogin(req)) {
			if(action.equals("deleteAll")) {
				Long[] ids = (new HttpUtil(req.getParameter("ids"))).toModel(Long[].class);
				if(ids != null) {
					cartService.deleteAll(ids);
					mapper.writeValue(resp.getOutputStream(), true);
				}
			}
		}
		else {
			mapper.writeValue(resp.getOutputStream(), null);
		}
	}
	

}
