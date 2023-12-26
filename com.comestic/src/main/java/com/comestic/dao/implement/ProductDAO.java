package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.comestic.dao.IProductDAO;
import com.comestic.dao.IVarietyDAO;
import com.comestic.dao.mapper.ProductDetailMapper;
import com.comestic.dao.mapper.ProductMapper;
import com.comestic.model.ProductModel;
import com.comestic.model.VarietyModel;
import com.comestic.utils.Paging;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
	
	@Inject
	private IVarietyDAO varietyDAO;
	
	private List<Object> listInput = new ArrayList<>();
	
	@Override
	public List<ProductModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select p.id, categoryid, manufactureid, m.name as manufacturename, typeproductid, p.code, p.name, date, totalcomment,");
		sql.append(" shortdescription, longdescription, view, state, ishot, v.percentdes, numberlimit, star, numbervariety,");
		sql.append(" price, number, i.name as image");
		sql.append(" from product p, manufacture m, variety v, variety_image v_i, image i");
		sql.append(" where p.manufactureid = m.id and p.id = v.productid");
		sql.append(" and v.id = v_i.varietyid and v_i.imageid = i.id and v.isavatar = 1 and p.active = 1");
		setPaging(sql,paging);
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public Integer totalItem(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from product p, variety v");
					  sql.append(" where v.productid = p.id and v.isavatar = 1 and p.active = 1");
		setFilter(sql, paging);
		return count(sql.toString());
	}

	@Override
	public List<ProductModel> findByBestSeller(Paging paging) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id, categoryid, manufactureid, m.name as manufacturename, typeproductid, p.code, p.name, date,");
		sql.append(" totalcomment, shortdescription, longdescription, view, state, ishot, v.percentdes, numberlimit, star, numbervariety, price, number, i.name as image");
		sql.append(" from product p join variety v on v.productid = p.id and v.isavatar = 1 and p.active = 1");
		sql.append(" join manufacture m on p.manufactureid = m.id");
		sql.append(" join variety_image v_i on v_i.varietyid = v.id");
		sql.append(" join image i on v_i.imageid = i.id");
		sql.append(" join (select pd.id as id_best_seller, sum(bd.number) as sold_number");
		sql.append(" from product pd, bill_detail bd");
		sql.append(" where pd.id = bd.productid");
		sql.append(" group by pd.id order by sum(bd.number) desc");
		sql.append(" ) t on p.id = t.id_best_seller");
		sql.append(" order by t.sold_number desc");
		setPaging(sql, paging);
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public Integer totalItemBestSeller(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(distinct productid) from bill_detail bd, product p");
		sql.append(" where bd.productid = p.id and p.active = 1");
		setFilter(sql, paging);
		return count(sql.toString());
	}

	@Override
	public ProductModel findOne(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id, categoryid, manufactureid, typeproductid, state, numberlimit, p.code,");
		sql.append(" c.name as categoryname, v.percentdes, p.name, star, totalpreview, shortdescription, longdescription");		
		sql.append(" from product p, category c, variety v");
		sql.append(" where p.categoryid = c.id and v.productid = p.id and v.isavatar = 1 and p.active = 1 and p.id = ");
		sql.append(id);
		List<ProductModel> listResult = query(sql.toString(), new ProductDetailMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public Integer getPercentSale(Long id) {
		String sql = "select percentdes from variety where isavatar = 1 and productid = " + id;
		Integer percent = (Integer) getRecord(sql);
		return percent == null ? 0 : percent;
	}
	
	private boolean hasInBill(Long id) {
		StringBuilder sql = new StringBuilder("select count(*)  from bill b, bill_detail bd");
		sql.append(" where b.id = bd.billid and b.stateid in (1,2) and bd.productid = ");
		sql.append(id);
		Long count = (Long) getRecord(sql.toString());
		if(count != null && count > 0) {
			return true;
		}
		return false;
	}
	
	private List<String> handleDeleteOneWithAvailableTran(Connection conn, Long id) {
		// xoa tat ca bien the va tra ve image list
		List<String> imageList = varietyDAO.deleteAllByProductIdExceptAvatarWithTran(conn, id);
		if(imageList == null) {
			return null;
		}
		
		// deactive product
		String sql = "update product set active = 0 where id = " + id;
		try {
			updateWithAvailableTran(conn, sql);
			return imageList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> deleteOneWithAvailableTran(Connection conn, Long id) {
		if(hasInBill(id)) {
			return null;
		}
		return handleDeleteOneWithAvailableTran(conn, id);
	}

	@Override
	public List<String> deleteAllProductByCategoryIdWithAvailableTran(Connection conn, Long categoryId) {
		String sql = "select * from product where active = 1 and categoryid = " + categoryId;
		List<ProductModel> listProduct = query(sql, new ProductMapper());
		List<String> images = new ArrayList<>();
		List<String> temp;
		for(ProductModel product:listProduct) {
			temp = handleDeleteOneWithAvailableTran(conn, product.getId());
			if(temp == null) {
				return null;
			}
			if(temp.size() > 0) {
				images.addAll(temp);
			}
		}
		return images;
	}

	@Override
	public Long deleteOne(Long id) {
		Long idResult = null;
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			List<String> images = deleteOneWithAvailableTran(conn, id);
			if(images != null) {
				conn.commit();
				idResult = id;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return idResult;
	}

	private Long handleAddNewWithAvailableTran(Connection conn, ProductModel productModel) {
		StringBuilder sql = new StringBuilder("Insert into");
		sql.append(" product (categoryid,manufactureid,typeproductid,code,");
		sql.append(" name,shortdescription,longdescription,view,");
		sql.append(" state,ishot,numberlimit,star,");
		sql.append(" numbervariety,date,totalcomment,totalpreview,active)");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)");
		this.listInput.clear();
		this.listInput.add(productModel.getCategoryId());
		this.listInput.add(productModel.getManufactureId());
		this.listInput.add(productModel.getTypeProductId());
		this.listInput.add(productModel.getCode());
		this.listInput.add(productModel.getName());
		this.listInput.add(productModel.getShortDescription());
		this.listInput.add(productModel.getLongDescription());
		this.listInput.add(productModel.getView());
		this.listInput.add(productModel.getState());
		this.listInput.add(productModel.getIsHot());
		this.listInput.add(productModel.getNumberLimit());
		this.listInput.add(productModel.getStar());
		this.listInput.add(productModel.getNumberVariety());
		this.listInput.add(new Timestamp(System.currentTimeMillis()));
		this.listInput.add(productModel.getTotalComment());
		this.listInput.add(productModel.getTotalPreview());
		
		Long id = null;
		try {
			id = insertWithAvailableTran(conn, sql.toString(), this.listInput);
		} catch (SQLException e) {
			id = null;
			e.printStackTrace();
		}
		return id;
	}
	
	private Long addNewWithAvailableTran(Connection conn, ProductModel productModel) {
		// luu cac data cho bang product
		Long id = handleAddNewWithAvailableTran(conn, productModel);
		if(id == null) {
			return null;
		}
		
		List<VarietyModel> listVariety = productModel.getListVariety();
		if(listVariety == null || listVariety.size() == 0) {
			return null;
		}
		
		boolean check =
		listVariety.stream().allMatch(variety -> {
			Long idTemp = varietyDAO.addNewWithAvailableTran(conn, variety, id);
			return idTemp != null;
		});	
		if(!check) {
			return null;
		}
		return id;
	}
	
	private void handleUpdateWithAvailableTran(Connection conn, ProductModel productModel) {
		StringBuilder sql = new StringBuilder("Update product");
		sql.append(" set categoryid=?, manufactureid=?, typeproductid=?, code=?,");
		sql.append(" name=?, shortdescription=?, longdescription=?, state=?,");
		sql.append(" numberlimit=?, numbervariety=?");
		sql.append(" where id = ?");
		this.listInput.clear();
		this.listInput.add(productModel.getCategoryId());
		this.listInput.add(productModel.getManufactureId());
		this.listInput.add(productModel.getTypeProductId());
		this.listInput.add(productModel.getCode());
		this.listInput.add(productModel.getName());
		this.listInput.add(productModel.getShortDescription());
		this.listInput.add(productModel.getLongDescription());
		this.listInput.add(productModel.getState());
		this.listInput.add(productModel.getNumberLimit());
		this.listInput.add(productModel.getNumberVariety());
		this.listInput.add(productModel.getId());
		
		try {
			updateWithAvailableTran(conn, sql.toString(), this.listInput);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> updateWithAvailableTran(Connection conn, ProductModel productModel) {
		// update cac thong tin cho bang product
		handleUpdateWithAvailableTran(conn, productModel);
		
		List<VarietyModel> listVariety = productModel.getListVariety();
		
		// lay danh sach cac variety duoc cap nhat
		List<VarietyModel> listUpdateVariety = listVariety
												 .stream()
												 .filter(variety -> variety.getId() != null)
												 .collect(Collectors.toList());
		// xoa bo cac variety khong duoc vap nhat, va khong co trong bill
		List<String> imageList = varietyDAO.deleteAllNotInListWithAvailableTran(conn, 
														listUpdateVariety, 
														productModel.getId());
		if(imageList == null) {
			return null;
		}
		
		// them moi hoac cap nhat cac variety
		boolean check =
		listVariety.stream().allMatch(variety -> {
			// them moi variety
			if(variety.getId() == null) {
				Long idTemp = varietyDAO.addNewWithAvailableTran(conn, variety, productModel.getId());
				return idTemp != null;
			}
			// cap nhat variety
			else {
				String nameImage = varietyDAO.updateWithAvailableTran(conn, variety);
				if(nameImage != null && !nameImage.isEmpty()) {
					imageList.add(nameImage);
				}
				return nameImage != null;
			}
		});
		
		if(!check) {
			return null;
		}
		return imageList;
	}

	@Override
	public Long save(ProductModel productModel) {
		Long id = null;
		List<String> listImage = null;
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			
			// them moi
			if(productModel.getId() == null) {
				id = addNewWithAvailableTran(conn, productModel);
				if(id != null) {
					conn.commit();
				}
			}
			// cap nhat
			else {
				id = productModel.getId();
				listImage = updateWithAvailableTran(conn, productModel);
				if(listImage != null) {
					conn.commit();
					// xoa file
				}
			}
			
		} catch (SQLException e) {
			id = null;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		
		return id;
		
	}

}
