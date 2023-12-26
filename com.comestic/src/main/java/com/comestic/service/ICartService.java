package com.comestic.service;

import java.util.List;

import com.comestic.model.BillStateModel;
import com.comestic.model.CartItemModel;
import com.comestic.model.CartModel;
import com.comestic.utils.Paging;

public interface ICartService {
	List<CartModel> findAll(Paging paging);
	CartModel findOne(Long id);
	CartModel findOneByUserCart(CartModel cartModel);
	CartItemModel findOneItemCartByVarietyId(Long varietyId);
	Integer getNumberOfVarietyById(Long varietyId);
	// Long save(CartModel cartModel);
	CartModel save(CartModel cartModel);
	List<BillStateModel> findAllBillState();
	void updateStateForBill(CartModel cartModel);
	void deleteAll(Long[] ids);
}
