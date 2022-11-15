package com.comestic.dao;

import java.util.List;

import com.comestic.model.AttributeModel;

public interface IAttributeDAO {
	List<AttributeModel> findAll();
	AttributeModel findOne(Long id);
}
