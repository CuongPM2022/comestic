package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.ImageModel;

public class ImageMapper implements IRowMapper<ImageModel> {

	@Override
	public ImageModel mapRow(ResultSet resultSet) {
		ImageModel imageModel = null;
		try {
			if(resultSet != null) {
				imageModel = new ImageModel();
				imageModel.setId(resultSet.getLong("id"));
				imageModel.setName(resultSet.getString("name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			imageModel = null;
		}
		return imageModel;
	}

}
