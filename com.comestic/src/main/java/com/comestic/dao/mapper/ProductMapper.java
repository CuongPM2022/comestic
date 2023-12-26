package com.comestic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comestic.model.ProductModel;
import com.comestic.model.VarietyModel;

public class ProductMapper implements IRowMapper<ProductModel> {
	private ProductModel productModel;
	private VarietyModel  varietyModel;
	private List<VarietyModel> listVariety;
	private String path;
	
	@Override
	public ProductModel mapRow(ResultSet resultSet) {
		path = "views/source/image/product/";
		productModel = new ProductModel();
		varietyModel = new VarietyModel();
		listVariety = new ArrayList<VarietyModel>();
		
		try {
			productModel.setId(resultSet.getLong("id"));
			productModel.setCategoryId(resultSet.getLong("categoryid"));
			productModel.setManufactureId(resultSet.getLong("manufactureid"));
			if(hasColumn(resultSet, "manufacturename")) {
				productModel.setManufactureName(resultSet.getString("manufacturename"));
			}
			productModel.setTypeProductId(resultSet.getLong("typeproductid"));
			productModel.setCode(resultSet.getString("code"));
			productModel.setName(resultSet.getString("name"));
			if(hasColumn(resultSet, "image")) {
				productModel.setImage(path + resultSet.getString("image"));
			}
			productModel.setDate(resultSet.getTimestamp("date"));
			productModel.setTotalComment(resultSet.getInt("totalcomment"));
			productModel.setShortDescription(resultSet.getString("shortdescription"));
			productModel.setLongDescription(resultSet.getString("longdescription"));
			productModel.setView(resultSet.getInt("view"));
			productModel.setState(resultSet.getInt("state"));
			productModel.setIsHot(resultSet.getInt("ishot"));
			productModel.setNumberLimit(resultSet.getInt("numberlimit"));
			productModel.setStar(resultSet.getDouble("star"));
			productModel.setNumberVariety(resultSet.getInt("numbervariety"));
			if(hasColumn(resultSet, "price")) {
				productModel.setPrice(resultSet.getDouble("price"));
				varietyModel.setNumber(resultSet.getInt("number"));
				varietyModel.setPrice(resultSet.getDouble("price"));
				varietyModel.setPercentDes(resultSet.getInt("percentdes"));
				varietyModel.setImage(resultSet.getString("image"));
				listVariety.add(varietyModel);
				productModel.setListVariety(listVariety);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			productModel = null;
		}
		return productModel;
	}

}
