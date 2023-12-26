package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.ManufactureModel;

public class ManufactureMapper implements IRowMapper<ManufactureModel> {

	@Override
	public ManufactureModel mapRow(ResultSet resultSet) {
		ManufactureModel manufactureModel = null;
		if(resultSet != null) {
			manufactureModel = new ManufactureModel();
			try {
				manufactureModel.setId(resultSet.getLong("id"));
				manufactureModel.setCode(resultSet.getString("code"));
				manufactureModel.setName(resultSet.getString("name"));
			} catch(SQLException e) {
				manufactureModel = null;
				e.printStackTrace();
			}
		}
		return manufactureModel;
	}

}
