package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRowMapper<T> {
	T mapRow(ResultSet resultSet);
	default boolean hasColumn(ResultSet resultSet, String name) {
		try {
			resultSet.getObject(name);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
