package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IVarietyImageDAO;
import com.comestic.dao.mapper.VarietyImageMapper;
import com.comestic.model.VarietyImageModel;


public class VarietyImageDAO extends AbstractDAO<VarietyImageModel> implements IVarietyImageDAO {
	
	//@Inject
	//private IImageDAO imageDAO;
	
	private List<Object> listInput = new ArrayList<>();
	
	public VarietyImageModel findByVarietyId(Long varietyId) {
		StringBuilder sql = new StringBuilder("select * from variety_image where varietyid = ");
		sql.append(varietyId);
		
		List<VarietyImageModel> listResult = query(sql.toString(), new VarietyImageMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public String updateImageWithAvailableTran(Connection conn, Long varietyId, Long newImageId) {
		VarietyImageModel model = findByVarietyId(varietyId);
		if(model == null) {
			return null;
		}
		
		Long oldImageId = model.getImageId();
		
		// co cap nhat anh
		if(oldImageId != newImageId) {
			String oldImageName = "";
			// cap nhat lai anh moi cho variety
			StringBuilder sql = new StringBuilder("Update variety_image set imageid = ");
			sql.append(newImageId);
			sql.append(" where varietyid = ");
			sql.append(varietyId);
			try {
				updateWithAvailableTran(conn, sql.toString());
				
				// xoa va tra ve ten anh cu
				// oldImageName = imageDAO.deleteWithAvailableTran(conn, oldImageId);
			} catch (SQLException e) {
				oldImageName = null;
				e.printStackTrace();
			}
			return oldImageName;
		}
		
		// khong cap nhat anh
		return "";
	}

	@Override
	public String test(Long varietyId, Long newImageId) {
		String name = "";
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			name = updateImageWithAvailableTran(conn, varietyId, newImageId);
			conn.commit();
		} catch (SQLException e) {
			name = null;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public String deleteByVarietyIdWithAvailableTran(Connection conn, Long varietyId) {
		String oldImageName = "";
		//VarietyImageModel varietyImageModel = findByVarietyId(varietyId);
		
		String sql = "delete from variety_image where varietyid = " + varietyId;
		try {
			updateWithAvailableTran(conn, sql);
			
			// xoa anh trong table image
			//oldImageName = imageDAO.deleteWithAvailableTran(conn, varietyImageModel.getImageId());
		} catch (SQLException e) {
			oldImageName = null;
			e.printStackTrace();
		}
	
		return oldImageName;
	}

	@Override
	public Long saveImageWithAvailableTran(Connection conn, Long varietyId, Long imageId) {
		StringBuilder sql = new StringBuilder("Insert into");
		sql.append(" variety_image(varietyid,imageid,isimageavatar) values (?,?,1)");
		this.listInput.clear();
		this.listInput.add(varietyId);
		this.listInput.add(imageId);
		
		Long id = null;
		try {
			id = insertWithAvailableTran(conn, sql.toString(), this.listInput);
		} catch (SQLException e) {
			id = null;
			e.printStackTrace();
		}
		return id;
	}

}
