package com.comestic.dao;

import java.util.List;

import com.comestic.model.CartItemModel;
import com.comestic.model.CartModel;
import com.comestic.utils.Paging;

public interface ICartDAO {
	List<CartModel> findAll(Paging paging);
	CartModel findOne(Long id);
	CartModel findOneByUserCart(CartModel cartModel);
	CartItemModel findOneItemCartByVarietyId(Long varietyId); 
	Integer getNumberOfVarietyById(Long varietyId);
	// Long save(CartModel cartModel);
	CartModel save(CartModel cartModel);
	void updateStateForBill(CartModel cartModel);
	Integer getTotalNewBill(Paging paging);
	Integer getTotalBill(Paging paging);
	Integer getTotalCancelBill(Paging paging);
	Double getTotalMoney(Paging paging);
	void deleteOne(Long id);
}
