package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.dao.implement.VarietyAttributeDAO;
import com.comestic.model.VarietyModel;

public class VarietyMapper implements IRowMapper<VarietyModel> {
	
	@Override
	public VarietyModel mapRow(ResultSet resultSet) {
		VarietyModel varietyModel = null;
		if(resultSet != null) {
			varietyModel = new VarietyModel();
			VarietyAttributeDAO attributeOfVatietyDAO = new VarietyAttributeDAO();
			String path = "views/source/image/product/";
			try {
				varietyModel.setId(resultSet.getLong("id"));
				varietyModel.setProductId(resultSet.getLong("productid"));
				try {
					String imagePath = resultSet.getString("image");
					varietyModel.setImage(path + imagePath);
					varietyModel.setImageId(resultSet.getLong("imageid"));
				} catch (Exception e) {
					varietyModel.setImage(null);
				}
				varietyModel.setPrice(resultSet.getDouble("price"));
				varietyModel.setPercentDes(resultSet.getInt("percentdes"));
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
