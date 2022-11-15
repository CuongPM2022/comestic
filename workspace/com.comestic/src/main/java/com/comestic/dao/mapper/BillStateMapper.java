package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.BillStateModel;

public class BillStateMapper implements IRowMapper<BillStateModel> {

	@Override
	public BillStateModel mapRow(ResultSet resultSet) {
		BillStateModel billStateModel = null;
		if(resultSet != null) {
			billStateModel = new BillStateModel();
			try {
				billStateModel.setId(resultSet.getLong("id"));
				billStateModel.setName(resultSet.getString("name"));
			} catch(SQLException e) {
				billStateModel = null;
				e.printStackTrace();
			}
		}
		return billStateModel;
	}

}
