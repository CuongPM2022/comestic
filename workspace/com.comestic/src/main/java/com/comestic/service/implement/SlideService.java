package com.comestic.service.implement;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.comestic.dao.ISlideDAO;
import com.comestic.service.ISlideService;
import com.comestic.utils.Paging;

public class SlideService implements ISlideService {
	@Inject
	private ISlideDAO slideDAO;
	private Map<String, Object> map;

	@Override
	public Map<String, Object> findAll(Paging paging) {
		this.map = new HashMap<String, Object>();
		this.map.put("listData", slideDAO.findAll(paging));
		this.map.put("totalItem", slideDAO.totalItem(paging));
		return this.map;
	}

	@Override
	public Integer totalItem(Paging paging) {
		return slideDAO.totalItem(paging);
	}

}
