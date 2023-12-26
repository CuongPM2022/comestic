package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.CartItemModel;

public class CartItemMapper implements IRowMapper<CartItemModel> {

	@Override
	public CartItemModel mapRow(ResultSet resultSet) {
		String path;
		CartItemModel cartItemModel = null;
		if(resultSet != null) {
			path = "views/source/image/product/";
			cartItemModel = new CartItemModel();
			try {
				cartItemModel.setProductId(resultSet.getLong("productid"));
				cartItemModel.setProductName(resultSet.getString("productname"));
				try {
					cartItemModel.setVarietyId(resultSet.getLong("varietyid"));
				} catch(SQLException e) {
					cartItemModel.setVarietyId(null);
				}
				
				cartItemModel.setPrice(resultSet.getDouble("price"));
				cartItemModel.setPercentDes(resultSet.getInt("percentdes"));
				
				try {
					cartItemModel.setNumber(resultSet.getInt("number"));
				} catch(SQLException e) {
					cartItemModel.setNumber(null);
				}
				
				cartItemModel.setImage(path + resultSet.getString("image"));
			} catch(SQLException e) {
				cartItemModel = null;
				e.printStackTrace();
			}
		}
		return cartItemModel;
	}

}
