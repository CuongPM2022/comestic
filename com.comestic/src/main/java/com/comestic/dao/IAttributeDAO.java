package com.comestic.dao;

import java.util.List;

import com.comestic.model.AttributeModel;

public interface IAttributeDAO {
	List<AttributeModel> findAll();
	List<AttributeModel> saveAll(List<AttributeModel> listAttribute); 
	AttributeModel findOne(Long id);
}
