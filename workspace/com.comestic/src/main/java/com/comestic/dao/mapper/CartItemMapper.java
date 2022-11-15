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
				cartItemModel.setVarietyId(resultSet.getLong("varietyid"));
				cartItemModel.setPrice(resultSet.getDouble("price"));
				cartItemModel.setPercentDes(resultSet.getInt("percentdes"));
				cartItemModel.setNumber(resultSet.getInt("number"));
				cartItemModel.setImage(path + resultSet.getString("image"));
			} catch(SQLException e) {
				cartItemModel = null;
				e.printStackTrace();
			}
		}
		return cartItemModel;
	}

}
