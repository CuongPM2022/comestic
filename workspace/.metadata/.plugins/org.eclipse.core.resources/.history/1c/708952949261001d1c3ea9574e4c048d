package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.CartModel;

public class CartDetailMapper extends CartMapper {
	public CartModel mapRow(ResultSet resultSet) {
		CartModel cartModel = null;
		if(resultSet != null) {
			cartModel = super.mapRow(resultSet);
			try {
				cartModel.setTotalMoney(resultSet.getDouble("totalMoney"));
				cartModel.setGender(resultSet.getString("gender"));
				cartModel.setPhone(resultSet.getString("phone"));
				cartModel.setAddress(resultSet.getString("address"));
				cartModel.setEmail(resultSet.getString("email"));
				cartModel.setMethod(resultSet.getString("method"));
				cartModel.setNote(resultSet.getString("note"));
				cartModel.setStateId(resultSet.getLong("stateid"));
				cartModel.setStateName(resultSet.getString("statename"));
			} catch(SQLException e) {
				cartModel = null;
				e.printStackTrace();
			}
		}
		return cartModel;
	}
}
