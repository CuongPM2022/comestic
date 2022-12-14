package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.IAttributeOfVarietyDAO;
import com.comestic.dao.mapper.AttributeOfVatietyMapper;
import com.comestic.model.AttributeOfVarietyModel;

public class AttributeOfVarietyDAO extends AbstractDAO<AttributeOfVarietyModel> implements IAttributeOfVarietyDAO {
	@Override
	public List<AttributeOfVarietyModel> findByVarietyId(Long varietyId) {
		StringBuilder sql = new StringBuilder("select a.id, name, value");
		sql.append(" from variety_attribute va, attribute a");
		sql.append(" where va.attributeid = a.id and varietyid = ");
		sql.append(varietyId);
		return query(sql.toString(), new AttributeOfVatietyMapper());
	}

}
