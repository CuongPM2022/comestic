package com.comestic.service;

import java.util.List;

import com.comestic.model.AttributeModel;
import com.comestic.model.AttributeOfVarietyModel;

public interface IAttributeService {
	List<AttributeModel> findAll();
	List<AttributeOfVarietyModel> findOneByVarietyId(Long varietyId);
	AttributeModel findOne(Long id);
}
