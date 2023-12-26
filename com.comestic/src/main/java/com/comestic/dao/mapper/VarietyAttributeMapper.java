package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.VarietyAttributeModel;

public class VarietyAttributeMapper implements IRowMapper<VarietyAttributeModel> {
	
	
	public VarietyAttributeModel mapRow(ResultSet resultSet) {
		VarietyAttributeModel attribueOfVarietyModel = null;
		if(resultSet != null) {
			attribueOfVarietyModel = new VarietyAttributeModel();
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
