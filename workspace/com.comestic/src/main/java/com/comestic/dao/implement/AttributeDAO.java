package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.IAttributeDAO;
import com.comestic.dao.mapper.AttributeMapper;
import com.comestic.model.AttributeModel;

public class AttributeDAO extends AbstractDAO<AttributeModel> implements IAttributeDAO {
	@Override
	public List<AttributeModel> findAll() {
		StringBuilder sql = new StringBuilder("select * from attribute");
		return query(sql.toString(), new AttributeMapper());
	}

	@Override
	public AttributeModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("select * from attribute where id=");
		sql.append(id);
		List<AttributeModel> listResult = query(sql.toString(), new AttributeMapper());
		if(listResult != null) {
			return listResult.get(0);
		}
		return null;
	}

}
