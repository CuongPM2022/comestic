package com.comestic.service;

import java.util.Map;

import com.comestic.utils.Paging;

public interface ICategoryService {
	Map<String, Object> findAll(Paging paging);
	Integer totalItem(Paging paging);
}
