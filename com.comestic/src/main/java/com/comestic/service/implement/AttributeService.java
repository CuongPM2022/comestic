package com.comestic.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IAttributeDAO;
import com.comestic.dao.IVarietyAttributeDAO;
import com.comestic.model.AttributeModel;
import com.comestic.model.VarietyAttributeModel;
import com.comestic.service.IAttributeService;

public class AttributeService implements IAttributeService {
	
	@Inject
	private IAttributeDAO attributeDAO;
	
	@Inject
	private IVarietyAttributeDAO attributeOfVarietyDAO;
	
	@Override
	public List<AttributeModel> findAll() {
		return attributeDAO.findAll();
	}

	@Override
	public AttributeModel findOne(Long id) {
		return attributeDAO.findOne(id);
	}

	@Override
	public List<VarietyAttributeModel> findOneByVarietyId(Long varietyId) {
		return attributeOfVarietyDAO.findByVarietyId(varietyId);
	}

	@Override
	public List<AttributeModel> saveAll(List<AttributeModel> listAtteibute) {
		return attributeDAO.saveAll(listAtteibute);
	}

}
