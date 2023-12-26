package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
				categoryModel.setLevel(resultSet.getInt("level"));
				categoryModel.setImageId(resultSet.getLong("imageid"));
				if(hasColumn(resultSet, "imagename")) {
					categoryModel.setImageName(path + resultSet.getString("imagename"));
				}
				if(hasColumn(resultSet, "username")) {
					categoryModel.setCreateBy(resultSet.getString("username"));
				}
				categoryModel.setStrCreateDate((new SimpleDateFormat("dd/MM/YYYY HH:mm")).format(resultSet.getTimestamp("createdate")));
			} catch (SQLException e) {
				categoryModel = null;
				e.printStackTrace();
			}
		}
		return categoryModel;
	}

}
