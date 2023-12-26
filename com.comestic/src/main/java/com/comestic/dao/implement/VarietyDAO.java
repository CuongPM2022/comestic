package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.IVarietyAttributeDAO;
import com.comestic.dao.IVarietyDAO;
import com.comestic.dao.IVarietyImageDAO;
import com.comestic.dao.mapper.VarietyMapper;
import com.comestic.model.VarietyAttributeModel;
import com.comestic.model.VarietyModel;

public class VarietyDAO extends AbstractDAO<VarietyModel> implements IVarietyDAO {
	private List<Object> listInput = new ArrayList<Object>();
	
	@Inject
	private IVarietyImageDAO varietyImageDAO;
	
	@Inject
	private IVarietyAttributeDAO varietyAttributeDAO;

	@Override
	public List<VarietyModel> findByProductId(Long productId) {
		StringBuilder sql = new StringBuilder("select v.id, productid, vi.imageid, i.name as image, price, percentdes, number, isavatar");
		sql.append(" from variety v, image i, variety_image vi");
		sql.append(" where v.id = vi.varietyid and vi.imageid = i.id and vi.isimageavatar = 1 and productid = ? order by isavatar desc");
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

	@Override
	public VarietyModel findOne(Long varietyId) {
		StringBuilder sql = new StringBuilder("select v.id, v.productid, v.price, v.percentdes, v.number, v.isavatar, i.name as image, vi.imageid");
		sql.append(" from variety v, variety_image vi, image i");
		sql.append(" where v.id = vi.varietyid and vi.imageid = i.id and isimageavatar = 1 and v.id = ");
		sql.append(varietyId);
		
		List<VarietyModel> listResult = query(sql.toString(), new VarietyMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public void subQuantityWithAvailableTran(Connection conn, Long id, Integer userQuatity) {
		String sql = "update variety set number = number - "+ userQuatity + " where id = " + id;
		try {
			updateWithAvailableTran(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean hasInBill(Long id) {
		StringBuilder sql = new StringBuilder("select count(*) from bill b, bill_detail bd where  b.id = bd.billid");
		sql.append(" and (b.stateid = 1 or b.stateid = 2) and varietyid = ");
		sql.append(id);
		Long count = (Long) getRecord(sql.toString());
		if(count != null && count > 0) {
			return true;
		}
		return false;
	}
	
	private void handleDeleteOneWithAvailableTran(Connection conn, Long id) {
		try {
			// xoa cac attribute of variety
			String sql = "delete from variety_attribute where varietyid = " + id;
			updateWithAvailableTran(conn, sql);
			
			// xoa cac image cua variety
			sql = "delete from variety_image where varietyid = " + id;
			updateWithAvailableTran(conn, sql);
			
			// xoa variety
			sql = "delete from variety where id = " + id;
			updateWithAvailableTran(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean changeAvatarWithAvailableTran(Connection conn, VarietyModel varietyModel) {
		Long newVarietyId = null;
		String sql = "select min(id) from variety where productid = "+ varietyModel.getProductId() +" and id != " + varietyModel.getId();
		newVarietyId = (Long) getRecord(sql);
		if(newVarietyId == null) {
			return false;
		}
		
		sql = "update variety set isavatar = 1 where id = " + newVarietyId;
		try {
			updateWithAvailableTran(conn, sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String deleteOneWithAvailableTran(Connection conn, VarietyModel varietyModel) {
		if(hasInBill(varietyModel.getId())) {
			return null;
		}
		
		if(varietyModel.getIsAvatar() == 0) {
			handleDeleteOneWithAvailableTran(conn, varietyModel.getId());
			return varietyModel.getImage();
		} else {
			boolean result = changeAvatarWithAvailableTran(conn, varietyModel);
			if(result) {
				handleDeleteOneWithAvailableTran(conn, varietyModel.getId());
				return varietyModel.getImage();
			} 
			return null;
		}
	}

	@Override
	public List<String> deleteAllByProductIdExceptAvatarWithTran(Connection conn, Long productId) {
		List<String> listImage = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder("select v.id, v.productid, v.price, v.number, v.isavatar, i.name as image");
		sql.append(" from variety v, variety_image vi, image i");
		sql.append(" where v.id = vi.varietyid and vi.imageid = i.id and isavatar != 1 and v.productid = ");
		sql.append(productId);
		
		List<VarietyModel> listVariety = query(sql.toString(), new VarietyMapper());
		if(listVariety == null) {
			return null;
		}
		for(VarietyModel item:listVariety) {
			handleDeleteOneWithAvailableTran(conn, item.getId());
			listImage.add(item.getImage());
		}
		return listImage;
	}

	@Override
	public String deleteNotInBillWithAvailableTran(Connection conn, Long varietyId) {
		if(hasInBill(varietyId)) {
			return null;
		}
		
		// xoa anh cua variety
		String oldNameImage = varietyImageDAO.deleteByVarietyIdWithAvailableTran(conn, varietyId);
		
		// xoa thuoc tinh cua variety
		varietyAttributeDAO.deleteByVarietyIdWithAvailableTran(conn, varietyId);
		
		// xoa variety
		String sql = "delete from variety where id = " + varietyId;
		try {
			updateWithAvailableTran(conn, sql);
		} catch (SQLException e) {
			oldNameImage = null;
			e.printStackTrace();
		}
		return oldNameImage;
	}

	private Long handleAddNewWithAvailableTran(Connection conn, VarietyModel varietyModel) {
		StringBuilder sql = new StringBuilder("Insert into");
		sql.append(" variety(productid,price,percentdes,number,isavatar)");
		sql.append(" values (?,?,?,?,?)");
		this.listInput.clear();
		this.listInput.add(varietyModel.getProductId());
		this.listInput.add(varietyModel.getPrice());
		this.listInput.add(varietyModel.getPercentDes());
		this.listInput.add(varietyModel.getNumber());
		this.listInput.add(varietyModel.getIsAvatar());
		
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
	public Long addNewWithAvailableTran(Connection conn, VarietyModel varietyModel, Long productId) {
		varietyModel.setProductId(productId);
		
		// luu cac thong tin vao table variety
		Long varietyId = handleAddNewWithAvailableTran(conn, varietyModel);
		if(varietyId == null) {
			return null;
		}
		
		// luu image vao bang variety_image
		varietyImageDAO.saveImageWithAvailableTran(conn, varietyId, varietyModel.getImageId());
		
		// luu cac attribute
		List<VarietyAttributeModel> listAttribute = varietyModel.getListAttribute();
		if(listAttribute != null && listAttribute.size() > 0) {
			varietyAttributeDAO
			    .saveListAttributeForVarietyWithAvailableTran(conn, listAttribute, varietyId);
		}
		
		return varietyId;
	}
	
	private void handleUpdateWithAvailableTran(Connection conn, VarietyModel varietyModel) {
		StringBuilder sql = new StringBuilder("Update variety");
		sql.append(" set productid=?, price=?, percentdes=?, number=?, isavatar=? where id = ?");
		this.listInput.clear();
		this.listInput.add(varietyModel.getProductId());
		this.listInput.add(varietyModel.getPrice());
		this.listInput.add(varietyModel.getPercentDes());
		this.listInput.add(varietyModel.getNumber());
		this.listInput.add(varietyModel.getIsAvatar());
		this.listInput.add(varietyModel.getId());
		
		try {
			updateWithAvailableTran(conn, sql.toString(), this.listInput);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateWithAvailableTran(Connection conn, VarietyModel varietyModel) {
		Long varietyId = varietyModel.getId();
		
		// cap nhat cac thong tin cho bang variety
		handleUpdateWithAvailableTran(conn, varietyModel);
		
		// cap nhat anh trong bang variety_image
		String oldImageName = varietyImageDAO
				       .updateImageWithAvailableTran(conn, varietyId, varietyModel.getImageId());
		
		// xoa het attribute cu, cap nhat lai list attribute
		varietyAttributeDAO.deleteByVarietyIdWithAvailableTran(conn, varietyId);
		List<VarietyAttributeModel> listAttribute = varietyModel.getListAttribute();
		if(listAttribute != null && listAttribute.size() > 0) {
			varietyAttributeDAO
			    .saveListAttributeForVarietyWithAvailableTran(conn, listAttribute, varietyId);
		}
		
		return oldImageName;
	}

	@Override
	public String test(VarietyModel varietyModel) {
		String name = "";
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			name = updateWithAvailableTran(conn, varietyModel);
			if(name != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			name = null;
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public List<String> deleteAllNotInListWithAvailableTran(Connection conn, List<VarietyModel> listVariety,
			Long productId) 
	{
		// lay chuoi danh sach id tu list
		String strListId = "";
		if(listVariety != null && listVariety.size() > 0) {
			strListId = listVariety.stream()
					               .map(variety -> variety.getId().toString())
					               .reduce("", (acc, id) -> acc + id + ",");
			strListId = strListId.substring(0, strListId.length() - 1);
			strListId = " and v.id not in (" + strListId + ")"; 
		}
		
		// lay danh sach cac variety can xoa
		StringBuilder sql = new StringBuilder("select v.id, v.productid, v.price, v.percentdes, v.number,");
		sql.append(" v.isavatar, i.name as image, vi.imageid");
		sql.append(" from variety v, variety_image vi, image i");
		sql.append(" where v.id = vi.varietyid and vi.imageid = i.id and v.productid = ");
		sql.append(productId);
		sql.append(strListId);
		System.out.println(sql.toString());
		List<VarietyModel> listDeleteVariety = query(sql.toString(), new VarietyMapper());
		
		// tien hanh xoa
		boolean check = false;
		List<String> listImage = new ArrayList<>();
		
		if(listDeleteVariety.size() == 0) {
			check = true;
		}
		else {
			check = listDeleteVariety.stream().allMatch(variety -> {
				String nameImage = deleteNotInBillWithAvailableTran(conn, variety.getId());
				if(nameImage != null && !nameImage.isEmpty()) {
					listImage.add(nameImage);
				}
				return nameImage != null;
			});
		}
		
		if(!check) {
			return null;
		}
		return listImage;
	}

}
