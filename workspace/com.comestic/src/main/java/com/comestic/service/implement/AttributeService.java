package com.comestic.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IAttributeDAO;
import com.comestic.dao.IAttributeOfVarietyDAO;
import com.comestic.model.AttributeModel;
import com.comestic.model.AttributeOfVarietyModel;
import com.comestic.service.IAttributeService;

public class AttributeService implements IAttributeService {
	
	@Inject
	IAttributeDAO attributeDAO;
	
	@Inject
	IAttributeOfVarietyDAO attributeOfVarietyDAO;
	
	@Override
	public List<AttributeModel> findAll() {
		return attributeDAO.findAll();
	}

	@Override
	public AttributeModel findOne(Long id) {
		return attributeDAO.findOne(id);
	}

	@Override
	public List<AttributeOfVarietyModel> findOneByVarietyId(Long varietyId) {
		return attributeOfVarietyDAO.findByVarietyId(varietyId);
	}

}
