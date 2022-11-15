package com.comestic.service.implement;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.comestic.dao.IProductDAO;
import com.comestic.model.ProductModel;
import com.comestic.service.IProductService;
import com.comestic.utils.Paging;

public class ProductService implements IProductService {
	@Inject
	private IProductDAO productDAO;
	private Map<String, Object> map;
	
	@Override
	public Map<String, Object> findAll(Paging paging) {
		map = new HashMap<String, Object>();
		map.put("listData", productDAO.findAll(paging));
		map.put("totalItem", productDAO.totalItem(paging));
		return map;
	}

	@Override
	public Integer totalItem(Paging paging) {
		return productDAO.totalItem(paging);
	}

	@Override
	public Map<String, Object> finByBestSeller(Paging paging) {
		this.map = new HashMap<String, Object>();
		this.map.put("listData",productDAO.findByBestSeller(paging));
		this.map.put("totalItem", productDAO.totalItemBestSeller(paging));
		return this.map;
	}

	@Override
	public Integer totalItemBestSeller(Paging paging) {
		return productDAO.totalItemBestSeller(paging);
	}

	@Override
	public ProductModel findOne(Long id) {
		return productDAO.findOne(id);
	}

	

}
