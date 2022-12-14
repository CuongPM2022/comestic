package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.ICategoryDAO;
import com.comestic.dao.mapper.CategoryMapper;
import com.comestic.model.CategoryModel;
import com.comestic.utils.Paging;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	@Override
	public List<CategoryModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder("select c.id, c.name, code, parentid, i.name as imagename ");
					  sql.append(" from category c, image i where c.imageid = i.id");
		setPaging(sql, paging);
		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public Integer totalItem(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from category");
		setFilter(sql, paging);
		return count(sql.toString());
	}

}
