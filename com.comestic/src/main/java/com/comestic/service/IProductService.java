package com.comestic.service;

import java.util.List;
import java.util.Map;

import com.comestic.model.ProductModel;
import com.comestic.utils.Paging;

public interface IProductService {
	Map<String,Object> findAll(Paging paging);
	Map<String, Object> finByBestSeller(Paging paging);
	ProductModel findOne(Long id);
	Integer totalItem(Paging paging);
	Integer totalItemBestSeller(Paging paging);
	List<Long> deleteList(List<Long> listId);
	Long save(ProductModel productModel);
}
