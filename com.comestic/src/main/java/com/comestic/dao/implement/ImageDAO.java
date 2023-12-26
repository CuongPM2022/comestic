package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IImageDAO;
import com.comestic.dao.mapper.ImageMapper;
import com.comestic.model.ImageModel;

public class ImageDAO extends AbstractDAO<ImageModel> implements IImageDAO {
	
	private List<Object> listInput = new ArrayList<>();

	@Override
	public ImageModel save(String name) {
		StringBuilder sql = new StringBuilder("Insert into image(name) values(?)");
		listInput.clear();
		listInput.add(name);
		Long imageId  = insert(sql.toString(), listInput);
		return findOne(imageId);
	}

	@Override
	public ImageModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("select id, name from image where id=");
					  sql.append(id);
		List<ImageModel> listResult = query(sql.toString(), new ImageMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		} else {
			return null;
		}
	}

	@Override
	public String deleteWithAvailableTran(Connection conn, Long id) {
		String oldImageName = findOne(id).getName();
		String sql = "delete from image where id = " + id;
		try {
			updateWithAvailableTran(conn, sql);
		} catch (SQLException e) {
			oldImageName = null;
			e.printStackTrace();
		}
		return oldImageName;
	}

}
