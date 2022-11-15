package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.dao.implement.AttributeOfVarietyDAO;
import com.comestic.model.VarietyModel;

public class VarietyMapper implements IRowMapper<VarietyModel> {
	
	@Override
	public VarietyModel mapRow(ResultSet resultSet) {
		VarietyModel varietyModel = null;
		if(resultSet != null) {
			varietyModel = new VarietyModel();
			AttributeOfVarietyDAO attributeOfVatietyDAO = new AttributeOfVarietyDAO();
			String path = "views/source/image/product/";
			try {
				varietyModel.setId(resultSet.getLong("id"));
				varietyModel.setProductId(resultSet.getLong("productid"));
				varietyModel.setImage(path + resultSet.getString("image"));
				varietyModel.setPrice(resultSet.getDouble("price"));
				varietyModel.setNumber(resultSet.getInt("number"));
				varietyModel.setIsAvatar(resultSet.getInt("isavatar"));
				varietyModel.setListAttribute(attributeOfVatietyDAO.findByVarietyId(varietyModel.getId()));
			} catch(SQLException e) {
				varietyModel = null;
				e.printStackTrace();
			}
			
		}
		return varietyModel;
	}

}
