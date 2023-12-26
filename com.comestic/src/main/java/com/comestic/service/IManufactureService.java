package com.comestic.service;

import java.util.List;

import com.comestic.model.ManufactureModel;

public interface IManufactureService {
	List<ManufactureModel> findAll();
	ManufactureModel save(ManufactureModel manufactureModel);
}
