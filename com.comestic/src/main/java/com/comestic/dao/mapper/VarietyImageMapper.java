package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.VarietyImageModel;

public class VarietyImageMapper implements IRowMapper<VarietyImageModel> {

	@Override
	public VarietyImageModel mapRow(ResultSet resultSet) {
		VarietyImageModel model = null;
		if(resultSet != null) {
			model = new VarietyImageModel();
			try {
				model.setId(resultSet.getLong("id"));
				model.setVarietyId(resultSet.getLong("varietyId"));
				model.setImageId(resultSet.getLong("imageId"));
				model.setIsImageAvatar(resultSet.getInt("isimageavatar"));
			} catch (SQLException e) {
				model = null;
				e.printStackTrace();
			}
		}
		return model;
	}
	
}
