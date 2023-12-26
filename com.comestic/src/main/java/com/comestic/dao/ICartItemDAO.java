package com.comestic.dao;

import java.sql.Connection;
import java.util.List;

import com.comestic.model.CartItemModel;

public interface ICartItemDAO {
	List<CartItemModel> findByCartId(Long cartId);
	CartItemModel findOneByVarietyId(Long varietyId);
	Integer getNumberOfVarietyById(Long varietyId);
	//Long save(CartItemModel cartItemModel);
	CartItemModel saveWithAcailableTran(Connection conn, CartItemModel cartItemModel);
	void deleteAllByCartId(Long cartId);
}
