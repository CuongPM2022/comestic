package com.comestic.service;

import java.util.List;
import java.util.Map;

import com.comestic.model.CategoryModel;
import com.comestic.utils.Paging;

public interface ICategoryService {
	Map<String, Object> findAll(Paging paging);
	List<CategoryModel> findListParent();
	List<CategoryModel> findListHasNotChild();
	Integer totalItem(Paging paging);
	CategoryModel save(CategoryModel categoryModel, String rootPath);
	List<Long> deleteList(List<Long> listId);
}
