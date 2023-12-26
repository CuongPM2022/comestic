package com.comestic.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IManufactureDAO;
import com.comestic.model.ManufactureModel;
import com.comestic.service.IManufactureService;

public class ManufactureService implements IManufactureService {
	
	@Inject
	private IManufactureDAO manufactureDAO;

	@Override
	public List<ManufactureModel> findAll() {
		return manufactureDAO.findAll();
	}

	@Override
	public ManufactureModel save(ManufactureModel manufactureModel) {
		if(manufactureModel.getId() == null) {
			return manufactureDAO.create(manufactureModel);
		}
		// cap nhat
		return null;
	}

}
