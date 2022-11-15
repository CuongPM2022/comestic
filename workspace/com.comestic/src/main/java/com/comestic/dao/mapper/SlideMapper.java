package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.SlideModel;

public class SlideMapper implements IRowMapper<SlideModel> {

	@Override
	public SlideModel mapRow(ResultSet resultSet) {
		String path = "views/source/image/slide/";
		SlideModel slideModel = null;
		if(resultSet != null) {
			slideModel = new SlideModel();
			try {
				slideModel.setId(resultSet.getLong("id"));
				slideModel.setProductId(resultSet.getLong("productid"));
				slideModel.setImageName(path + resultSet.getString("name"));
			} catch (SQLException e) {
				slideModel = null;
				e.printStackTrace();
			}
		}
		return slideModel;
	}

}
