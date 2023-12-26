package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.CartItemModel;

public class CartItemDetailMapper extends CartItemMapper {
	public CartItemModel mapRow(ResultSet resultSet) {
		CartItemModel cartItemModel = null;
		if(resultSet != null) {
			cartItemModel = super.mapRow(resultSet);
			try {
				cartItemModel.setId(resultSet.getLong("id"));
				cartItemModel.setProductCode(resultSet.getString("productcode"));
				cartItemModel.setUserQuantity(resultSet.getInt("userquantity"));
			} catch(SQLException e) {
				cartItemModel = null;
				e.printStackTrace();
			}
		}
		return cartItemModel;
	}
}
