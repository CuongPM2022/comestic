package com.comestic.service;

import java.util.List;

import com.comestic.model.VarietyModel;

public interface IVarietyService {
	List<VarietyModel> findByProductId(Long productId);
}
