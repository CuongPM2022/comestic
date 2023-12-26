package com.comestic.service;

import java.util.List;

import com.comestic.model.AttributeModel;
import com.comestic.model.VarietyAttributeModel;

public interface IAttributeService {
	List<AttributeModel> findAll();
	List<AttributeModel> saveAll(List<AttributeModel> listAtteibute);
	List<VarietyAttributeModel> findOneByVarietyId(Long varietyId);
	AttributeModel findOne(Long id);
}
