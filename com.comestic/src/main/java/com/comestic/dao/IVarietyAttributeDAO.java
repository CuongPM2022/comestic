package com.comestic.dao;

import java.sql.Connection;
import java.util.List;

import com.comestic.model.VarietyAttributeModel;

public interface IVarietyAttributeDAO {
	List<VarietyAttributeModel> findByVarietyId(Long varietyId);
	void deleteByVarietyIdWithAvailableTran(Connection conn, Long varietyId);
	List<Long> saveListAttributeForVarietyWithAvailableTran(Connection conn, 
													  List<VarietyAttributeModel> listAttribute,
													  Long varietyId);
	
	
	// test
	List<Long> test(List<VarietyAttributeModel> listAttribute, Long varietyId);
}
