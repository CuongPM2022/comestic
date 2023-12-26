package com.comestic.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IBillStateDAO;
import com.comestic.dao.ICartDAO;
import com.comestic.model.BillStateModel;
import com.comestic.model.CartItemModel;
import com.comestic.model.CartModel;
import com.comestic.service.ICartService;
import com.comestic.utils.Paging;

public class CartService implements ICartService {
	
	@Inject
	ICartDAO cartDAO;
	
	@Inject
	IBillStateDAO billStateDAO;

	@Override
	public CartModel findOneByUserCart(CartModel cartModel) {
		return cartDAO.findOneByUserCart(cartModel);
	}

	@Override
	public CartItemModel findOneItemCartByVarietyId(Long varietyId) {
		return cartDAO.findOneItemCartByVarietyId(varietyId); 
	}

	@Override
	public Integer getNumberOfVarietyById(Long varietyId) {
		return cartDAO.getNumberOfVarietyById(varietyId);
	}

	@Override
	public CartModel save(CartModel cartModel) {
		return cartDAO.save(cartModel);
	}

	@Override
	public List<CartModel> findAll(Paging paging) {
		return cartDAO.findAll(paging);
	}

	@Override
	public CartModel findOne(Long id) {
		return cartDAO.findOne(id);
	}

	@Override
	public List<BillStateModel> findAllBillState() {
		return billStateDAO.findAll();
	}

	@Override
	public void updateStateForBill(CartModel cartModel) {
		cartDAO.updateStateForBill(cartModel);
	}

	@Override
	public void deleteAll(Long[] ids) {
		for(Long id:ids) {
			cartDAO.deleteOne(id);
		}
	}

}
