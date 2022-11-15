package com.comestic.dao;

import java.util.List;

import com.comestic.model.CategoryModel;
import com.comestic.utils.Paging;

public interface ICategoryDAO {
	List<CategoryModel> findAll(Paging paging);
	Integer totalItem(Paging paging);
}
