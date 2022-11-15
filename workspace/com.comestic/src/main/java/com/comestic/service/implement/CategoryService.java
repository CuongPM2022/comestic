package com.comestic.service.implement;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.comestic.dao.ICategoryDAO;
import com.comestic.service.ICategoryService;
import com.comestic.utils.Paging;

public class CategoryService implements ICategoryService {
	@Inject
	ICategoryDAO categoryDAO;
	Map<String, Object> map;

	@Override
	public Map<String, Object> findAll(Paging paging) {
		map = new HashMap<String, Object>();
		map.put("listData", categoryDAO.findAll(paging));
		map.put("totalItem", categoryDAO.totalItem(paging));
		return map;
	}

	@Override
	public Integer totalItem(Paging paging) {
		return categoryDAO.totalItem(paging);
	}

}
