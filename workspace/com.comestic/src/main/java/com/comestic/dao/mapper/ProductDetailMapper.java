package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comestic.dao.implement.VarietyDAO;
import com.comestic.model.ProductModel;

public class ProductDetailMapper implements IRowMapper<ProductModel> {

	@Override
	public ProductModel mapRow(ResultSet resultSet) {
		ProductModel productModel = null;
		if(resultSet != null) {
			productModel = new ProductModel();
			VarietyDAO varietyDAO = new VarietyDAO();
			try {
				productModel.setId(resultSet.getLong("id"));
				productModel.setCategoryId(resultSet.getLong("categoryid"));
				productModel.setCategoryName(resultSet.getString("categoryname"));
				productModel.setName(resultSet.getString("name"));
				productModel.setPercentDes(resultSet.getInt("percentdes"));
				productModel.setStar(resultSet.getDouble("star"));
				productModel.setTotalPreview(resultSet.getInt("totalpreview"));
				productModel.setShortDescription(resultSet.getString("shortdescription"));
				productModel.setLongDescription(resultSet.getString("longdescription"));
				productModel.setListVariety(varietyDAO.findByProductId(productModel.getId()));
			} catch(SQLException e) {
				productModel = null;
				e.printStackTrace();
			}
		}
		return productModel;
	}

}
