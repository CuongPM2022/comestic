package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.IManufactureDAO;
import com.comestic.dao.mapper.ManufactureMapper;
import com.comestic.model.ManufactureModel;

public class ManufactureDAO extends AbstractDAO<ManufactureModel> implements IManufactureDAO {

	@Override
	public List<ManufactureModel> findAll() {
		StringBuilder sql = new StringBuilder("select * from manufacture");
		return query(sql.toString(), new ManufactureMapper());
	}

}
