package com.comestic.dao;

import java.util.List;

import com.comestic.model.BillStateModel;

public interface IBillStateDAO {
	List<BillStateModel> findAll();
}
