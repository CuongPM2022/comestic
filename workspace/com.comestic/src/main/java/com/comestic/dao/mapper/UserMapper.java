package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.UserModel;

public class UserMapper implements IRowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel userModel = null;
		if(resultSet != null) {
			userModel = new UserModel();
			try { 
				userModel.setId(resultSet.getLong("id"));
				userModel.setUsername(resultSet.getString("username"));
				userModel.setName(resultSet.getString("name"));
				userModel.setImage(resultSet.getString("image"));
			} catch(SQLException e) {
				userModel = null;
				e.printStackTrace();
			}
		}
		return userModel;
	}

}
