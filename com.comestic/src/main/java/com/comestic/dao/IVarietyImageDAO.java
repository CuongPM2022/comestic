package com.comestic.dao;

import java.sql.Connection;

import com.comestic.model.VarietyImageModel;

public interface IVarietyImageDAO {
	VarietyImageModel findByVarietyId(Long varietyId);
    String updateImageWithAvailableTran(Connection conn, Long varietyId, Long newImageId);
    String deleteByVarietyIdWithAvailableTran(Connection conn, Long varietyId);
    Long saveImageWithAvailableTran(Connection conn, Long varietyId, Long imageId);
    
    // test
    String test(Long varietyId, Long newImageId);
}
