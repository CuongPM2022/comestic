package com.comestic.dao;

import java.util.List;

import com.comestic.model.ManufactureModel;

public interface IManufactureDAO {
	List<ManufactureModel> findAll();
}
