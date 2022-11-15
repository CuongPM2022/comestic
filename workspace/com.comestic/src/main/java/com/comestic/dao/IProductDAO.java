package com.comestic.dao;

import java.util.List;

import com.comestic.model.ProductModel;
import com.comestic.utils.Paging;

public interface IProductDAO {
	List<ProductModel> findAll(Paging paging);
	List<ProductModel> findByBestSeller(Paging paging);
	ProductModel findOne(Long id);
	Integer totalItem(Paging paging);
	Integer totalItemBestSeller(Paging paging);
}
