package com.comestic.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IVarietyDAO;
import com.comestic.model.VarietyModel;
import com.comestic.service.IVarietyService;

public class VarietyService implements IVarietyService {
	
	@Inject
	IVarietyDAO varietyDAO;
	
	@Override
	public List<VarietyModel> findByProductId(Long productId) {
		return varietyDAO.findByProductId(productId);
	}

}
