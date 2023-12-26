package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IVarietyAttributeDAO;
import com.comestic.dao.mapper.VarietyAttributeMapper;
import com.comestic.model.VarietyAttributeModel;

public class VarietyAttributeDAO extends AbstractDAO<VarietyAttributeModel> implements IVarietyAttributeDAO {
	
	private List<Object> listInput = new ArrayList<>();
	
	@Override
	public List<VarietyAttributeModel> findByVarietyId(Long varietyId) {
		StringBuilder sql = new StringBuilder("select a.id, name, value");
		sql.append(" from variety_attribute va, attribute a");
		sql.append(" where va.attributeid = a.id and varietyid = ");
		sql.append(varietyId);
		return query(sql.toString(), new VarietyAttributeMapper());
	}

	@Override
	public void deleteByVarietyIdWithAvailableTran(Connection conn, Long varietyId) {
		StringBuilder sql = new StringBuilder("Delete from variety_attribute where varietyid = ");
		sql.append(varietyId);
		try {
			updateWithAvailableTran(conn, sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Long saveOneAttributeWithAvailableTran(Connection conn, 
												   VarietyAttributeModel attr, Long vatietyId) 
	{
		StringBuilder sql = new StringBuilder("Insert into");
		sql.append(" variety_attribute(varietyid, attributeid, value) values(?,?,?)");
		this.listInput.clear();
		this.listInput.add(vatietyId);
		this.listInput.add(attr.getId());
		this.listInput.add(attr.getValue());
		
		Long id = null;
		try {
			id = insertWithAvailableTran(conn, sql.toString(), this.listInput);
		} catch (SQLException e) {
			id = null;
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Long> saveListAttributeForVarietyWithAvailableTran(Connection conn, 
			List<VarietyAttributeModel> listAttribute, Long varietyId) 
	{
		List<Long> listId = new ArrayList<>();
		
		boolean check =
		listAttribute.stream().allMatch(attr -> {
			Long idTemp = saveOneAttributeWithAvailableTran(conn, attr, varietyId);
			if(idTemp != null) {
				listId.add(idTemp);
			}
			return idTemp != null;
		});
		
		if(check) {
			return listId;
		}
		return null;
	}

	@Override
	public List<Long> test(List<VarietyAttributeModel> listAttribute, Long varietyId) {
		Connection conn = getConnection();
		List<Long> l = null;
		try {
			conn.setAutoCommit(false);
			l = saveListAttributeForVarietyWithAvailableTran(conn, listAttribute, varietyId);
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

}
