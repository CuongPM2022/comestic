package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.AttributeOfVarietyModel;

public class AttributeOfVatietyMapper implements IRowMapper<AttributeOfVarietyModel> {
	
	
	public AttributeOfVarietyModel mapRow(ResultSet resultSet) {
		AttributeOfVarietyModel attribueOfVarietyModel = null;
		if(resultSet != null) {
			attribueOfVarietyModel = new AttributeOfVarietyModel();
			try {
				attribueOfVarietyModel.setId(resultSet.getLong("id"));
				attribueOfVarietyModel.setName(resultSet.getString("name"));
				attribueOfVarietyModel.setValue(resultSet.getString("value"));
			} catch(SQLException e) {
				attribueOfVarietyModel = null;
				e.printStackTrace();
			}
		}
		return attribueOfVarietyModel;
	}
}
