package com.comestic.dao;

import java.util.List;

import com.comestic.model.VarietyModel;

public interface IVarietyDAO {
	List<VarietyModel> findByProductId(Long productId);
	void updateAmount(Long varietyId, Integer newAmount);
}
