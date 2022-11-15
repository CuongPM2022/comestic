package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel> {
	private CategoryModel categoryModel;

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		String path = "views/source/image/category/";
		if(resultSet != null) {
			categoryModel = new CategoryModel();
			try {
				categoryModel.setId(resultSet.getLong("id"));
				categoryModel.setName(resultSet.getString("name"));
				categoryModel.setCode(resultSet.getString("code"));
				categoryModel.setParentId(resultSet.getLong("parentid"));
				categoryModel.setImageName(path + resultSet.getString("imagename"));
			} catch (SQLException e) {
				categoryModel = null;
				e.printStackTrace();
			}
		}
		return categoryModel;
	}

}
