package com.comestic.dao;

import java.sql.Connection;
import java.util.List;

import com.comestic.model.VarietyModel;

public interface IVarietyDAO {
	List<VarietyModel> findByProductId(Long productId);
	VarietyModel findOne(Long varietyId);
	void updateAmount(Long varietyId, Integer newAmount);
	void subQuantityWithAvailableTran(Connection conn, Long id, Integer userQuatity);
	String deleteOneWithAvailableTran(Connection conn, VarietyModel varietyModel);
	String deleteNotInBillWithAvailableTran(Connection conn, Long varietyId);
	List<String> deleteAllByProductIdExceptAvatarWithTran(Connection conn, Long productId);
	Long addNewWithAvailableTran(Connection conn, VarietyModel varietyModel, Long productId);
	String updateWithAvailableTran(Connection conn, VarietyModel varietyModel);
	List<String> deleteAllNotInListWithAvailableTran(Connection conn, 
													 List<VarietyModel> listVariety,
													 Long productId);
	
	// test
	String test(VarietyModel varietyModel);
}
