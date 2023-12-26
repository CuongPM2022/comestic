package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.IBillStateDAO;
import com.comestic.dao.mapper.BillStateMapper;
import com.comestic.model.BillStateModel;

public class BillStateDAO extends AbstractDAO<BillStateModel> implements IBillStateDAO {

	@Override
	public List<BillStateModel> findAll() {
		StringBuilder sql = new StringBuilder("select * from state");
		return query(sql.toString(), new BillStateMapper());
	}

}
