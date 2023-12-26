package com.comestic.dao;

import java.sql.Connection;

import com.comestic.model.ImageModel;

public interface IImageDAO {
	ImageModel save(String name);
	ImageModel findOne(Long id);
	String deleteWithAvailableTran(Connection conn, Long id);
}
