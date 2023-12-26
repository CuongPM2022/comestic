package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.ISlideDAO;
import com.comestic.dao.mapper.SlideMapper;
import com.comestic.model.SlideModel;
import com.comestic.utils.Paging;

public class SlideDAO extends AbstractDAO<SlideModel> implements ISlideDAO {

	@Override
	public List<SlideModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder("select s.id, productid, i.name");
					  sql.append(" from slide s, image i where s.imageid = i.id");
		setPaging(sql, paging);
		return query(sql.toString(), new SlideMapper());
	}

	@Override
	public Integer totalItem(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

}
