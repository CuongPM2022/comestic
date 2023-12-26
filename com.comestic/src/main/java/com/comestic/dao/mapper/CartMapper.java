package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.comestic.model.CartModel;

public class CartMapper implements IRowMapper<CartModel> {
	
	private String date;
	
	@Override
	public CartModel mapRow(ResultSet resultSet) {
		CartModel cartModel = null;
		if(resultSet != null) {
			cartModel = new CartModel();
			try {
				cartModel.setId(resultSet.getLong("id"));
				cartModel.setCode(resultSet.getString("code"));
				cartModel.setImportMoney(resultSet.getDouble("importmoney"));
				cartModel.setNameCustomer(resultSet.getString("namecustomer"));
				cartModel.setDate(resultSet.getTimestamp("date"));
				this.date = (new SimpleDateFormat("dd/MM/YYYY HH:mm")).format(resultSet.getTimestamp("date"));
				cartModel.setStrDate(this.date);
				cartModel.setStateId(resultSet.getLong("stateid"));
			} catch(SQLException e) {
				cartModel = null;
				e.printStackTrace();
			}
		}
		return cartModel;
	}

}
