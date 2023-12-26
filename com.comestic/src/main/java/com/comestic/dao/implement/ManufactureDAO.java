package com.comestic.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IManufactureDAO;
import com.comestic.dao.mapper.ManufactureMapper;
import com.comestic.model.ManufactureModel;

public class ManufactureDAO extends AbstractDAO<ManufactureModel> implements IManufactureDAO {

	private List<Object> listInput = new ArrayList<>();
	
	@Override
	public List<ManufactureModel> findAll() {
		StringBuilder sql = new StringBuilder("select * from manufacture");
		return query(sql.toString(), new ManufactureMapper());
	}

	@Override
	public ManufactureModel create(ManufactureModel manufactureModel) {
		Long id = null;
		String sql = "Insert into manufacture(code, name) values(?,?)";
		this.listInput.clear();
		this.listInput.add(manufactureModel.getCode());
		this.listInput.add(manufactureModel.getName());
		id = insert(sql, this.listInput);
		if(id == null) {
			return null;
		}
		return findOne(id);
	}

	@Override
	public ManufactureModel findOne(Long id) {
		String sql = "select * from manufacture where id = " + id;
		List<ManufactureModel> listResult = query(sql, new ManufactureMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

}
