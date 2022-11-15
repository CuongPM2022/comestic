package com.comestic.dao;

import java.util.List;

import com.comestic.model.SlideModel;
import com.comestic.utils.Paging;

public interface ISlideDAO {
	List<SlideModel> findAll(Paging paging);
	Integer totalItem(Paging paging);
}
