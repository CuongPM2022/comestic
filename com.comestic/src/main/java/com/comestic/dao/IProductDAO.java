package com.comestic.dao;

import java.sql.Connection;
import java.util.List;

import com.comestic.model.ProductModel;
import com.comestic.utils.Paging;

public interface IProductDAO {
	List<ProductModel> findAll(Paging paging);
	List<ProductModel> findByBestSeller(Paging paging);
	ProductModel findOne(Long id);
	Integer totalItem(Paging paging);
	Integer totalItemBestSeller(Paging paging);
	Integer getPercentSale(Long id);
	List<String> deleteOneWithAvailableTran(Connection conn, Long id);
	List<String> deleteAllProductByCategoryIdWithAvailableTran(Connection conn, Long categoryId);
	Long deleteOne(Long id);
	Long save(ProductModel productModel);
	
	// test

}
