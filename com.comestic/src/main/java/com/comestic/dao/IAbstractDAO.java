package com.comestic.dao;

import java.util.List;

import com.comestic.dao.mapper.IRowMapper;

public interface IAbstractDAO<T> {
	List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
	Integer count(String sql, Object... paremeters);
	Object getRecord(String sql, Object... paremeters);
	Long insert(String sql, Object... paremeters);
	void update(String sql, Object... paremeters); //Update and Delete
}
