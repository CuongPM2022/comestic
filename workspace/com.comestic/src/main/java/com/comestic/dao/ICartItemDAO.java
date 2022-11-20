package com.comestic.dao;

import java.util.List;

import com.comestic.model.CartItemModel;

public interface ICartItemDAO {
	List<CartItemModel> findByCartId(Long cartId);
	CartItemModel findOneByVarietyId(Long varietyId);
	Integer getNumberOfVarietyById(Long varietyId);
	Long save(CartItemModel cartItemModel);
	void deleteAllByCartId(Long cartId);
}
