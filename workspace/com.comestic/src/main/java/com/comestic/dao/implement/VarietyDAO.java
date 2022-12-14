package com.comestic.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IVarietyDAO;
import com.comestic.dao.mapper.VarietyMapper;
import com.comestic.model.VarietyModel;

public class VarietyDAO extends AbstractDAO<VarietyModel> implements IVarietyDAO {
	private List<Object> listInput = new ArrayList<Object>();

	@Override
	public List<VarietyModel> findByProductId(Long productId) {
		StringBuilder sql = new StringBuilder("select v.id, productid, i.name as image, price, number, isavatar");
		sql.append(" from variety v, image i, variety_image vi");
		sql.append(" where v.id = vi.varietyid and vi.imageid = i.id and productid = ? order by isavatar desc");
		this.listInput.clear();
		this.listInput.add(productId);
		return query(sql.toString(), new VarietyMapper(), this.listInput);
	}

	@Override
	public void updateAmount(Long varietyId, Integer newAmount) {
		StringBuilder sql = new StringBuilder("Update variety set number=? where id=?");
		this.listInput.clear();
		this.listInput.add(newAmount);
		this.listInput.add(varietyId);
		update(sql.toString(), this.listInput);
	}

}
