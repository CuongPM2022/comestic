package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IAttributeDAO;
import com.comestic.dao.mapper.AttributeMapper;
import com.comestic.model.AttributeModel;

public class AttributeDAO extends AbstractDAO<AttributeModel> implements IAttributeDAO {
	
	private List<Object> listResult = new ArrayList<>();
	
	@Override
	public List<AttributeModel> findAll() {
		StringBuilder sql = new StringBuilder("select * from attribute");
		return query(sql.toString(), new AttributeMapper());
	}

	@Override
	public AttributeModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("select * from attribute where id=");
		sql.append(id);
		List<AttributeModel> listResult = query(sql.toString(), new AttributeMapper());
		if(listResult != null) {
			return listResult.get(0);
		}
		return null;
	}
	
	private Long createWithAvailableTran(Connection conn, AttributeModel attributeModel) {
		Long id = null;
		String sql = "Insert into attribute(name) values(?)";
		this.listResult.clear();
		this.listResult.add(attributeModel.getName());
		try {
			id = insertWithAvailableTran(conn, sql, this.listResult);
			return id != null ? id : null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AttributeModel> saveAll(List<AttributeModel> listAttribute) {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			listAttribute.forEach(attr -> {
				Long id = createWithAvailableTran(conn, attr);
				attr.setId(id);
			});
			conn.commit();
			return listAttribute;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn);
		}
	}

}
