package com.comestic.dao;

import java.util.List;

import com.comestic.model.CategoryModel;
import com.comestic.utils.Paging;

public interface ICategoryDAO {
	List<CategoryModel> findAll(Paging paging);
	List<CategoryModel> findListParent();
	List<CategoryModel> findListHasNotChild();
	CategoryModel findOne(Long id);
	Integer totalItem(Paging paging);
	CategoryModel create(CategoryModel categoryModel);
	CategoryModel update(CategoryModel categoryModel);
	Long deleteOne(Long id);
}
