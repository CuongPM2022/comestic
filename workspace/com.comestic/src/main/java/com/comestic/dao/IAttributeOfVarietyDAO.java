package com.comestic.dao;

import java.util.List;

import com.comestic.model.AttributeOfVarietyModel;

public interface IAttributeOfVarietyDAO {
	List<AttributeOfVarietyModel> findByVarietyId(Long varietyId);
}
