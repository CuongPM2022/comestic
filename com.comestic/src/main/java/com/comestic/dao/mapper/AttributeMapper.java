package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.AttributeModel;

public class AttributeMapper implements IRowMapper<AttributeModel> {

	@Override
	public AttributeModel mapRow(ResultSet resultSet) {
		AttributeModel attributeModel = null;
		if(resultSet != null) {
			attributeModel = new AttributeModel();
			try {
				attributeModel.setId(resultSet.getLong("id"));
				attributeModel.setName(resultSet.getString("name"));
			} catch (SQLException e) {
				attributeModel = null;
				e.printStackTrace();
			}
		}
		return attributeModel;
	}

}
