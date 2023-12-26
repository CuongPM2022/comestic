package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.ICategoryDAO;
import com.comestic.dao.IProductDAO;
import com.comestic.dao.mapper.CategoryMapper;
import com.comestic.model.CategoryModel;
import com.comestic.utils.Paging;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	
	private List<CategoryModel> listResult;
	private List<Object> listInput = new ArrayList<>();
	
	@Inject
	private IProductDAO productDAO;
	
	@Override
	public List<CategoryModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder();
		sql.append("select C.id,code,C.name,parentid, level, I.id as imageid, I.name as imagename,createdate,U.name as username");
					  sql.append(" from category C, users U, image I");
					  sql.append(" where C.createby = U.id and C.imageid = I.id and C.active = 1 order by C.id desc");
		setPaging(sql, paging);
		List<CategoryModel> listCategory = query(sql.toString(), new CategoryMapper());
		
		Long parentId;
		for(CategoryModel item:listCategory) {
			parentId = item.getParentId();
			if(parentId != 0) {
				item.setParentCategoryName(findOne(parentId).getName());
			}
		}
		
		return listCategory;
	}

	@Override
	public Integer totalItem(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from category where active = 1");
		setFilter(sql, paging);
		return count(sql.toString());
	}

	@Override
	public CategoryModel findOne(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select C.id,code,C.name, parentid, level, I.id as imageid, I.name as imagename,createdate,U.name as username");
		sql.append(" from category C, users U, image I");
		sql.append(" where C.createby = U.id and C.imageid = I.id and C.active = 1 and C.id=");
		sql.append(id);
		
		listResult = query(sql.toString(), new CategoryMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public CategoryModel create(CategoryModel categoryModel) {
		Long parentId;
		StringBuilder sql = new StringBuilder("Insert into category(name,code,parentid,imageid,level,createby,createdate)");
					  sql.append(" values(?,?,?,?,?,?,?)");
		listInput.clear();
		listInput.add(categoryModel.getName());
		listInput.add(categoryModel.getCode());
		listInput.add(categoryModel.getParentId());
		listInput.add(categoryModel.getImageId());
		parentId = categoryModel.getParentId();
		if(parentId == 0) {
			listInput.add(0);
		} else {
			listInput.add(findOne(parentId).getLevel() + 1);
		}
		listInput.add(categoryModel.getUserId());
		listInput.add(new Timestamp(System.currentTimeMillis()));
		Long id = insert(sql.toString(), listInput);
		return findOne(id);
	}

	@Override
	public CategoryModel update(CategoryModel categoryModel) {
		StringBuilder sql = new StringBuilder("Update category");
		sql.append(" set name = ?, code = ?, parentid = ?, imageid = ?, level = ?, createby = ?, createdate = ?");
		sql.append(" where id = ?");
		listInput.clear();
		listInput.add(categoryModel.getName());
		listInput.add(categoryModel.getCode());
		listInput.add(categoryModel.getParentId());
		listInput.add(categoryModel.getImageId());
		listInput.add(categoryModel.getLevel());
		listInput.add(categoryModel.getUserId());
		listInput.add(new Timestamp(System.currentTimeMillis()));
		listInput.add(categoryModel.getId());
		update(sql.toString(), listInput);
		return findOne(categoryModel.getId());
	}
	
	private List<CategoryModel> getListChild(Long id) {
		String sql = "select * from category where active = 1 and parentid = " + id;
		return query(sql, new CategoryMapper());
	}
	
	private boolean checkFinalCategoryHasInBill(Long id) {
		StringBuilder sql = new StringBuilder("select count(*) from product p, bill_detail bd, bill b");
		sql.append(" where p.id = bd.productid and bd.billid = b.id and p.active = 1 and b.stateid in (1,2) and p.categoryid = ");
		sql.append(id);
		Long count = (Long) getRecord(sql.toString());
		if(count != null && count > 0) {
			return true;
		}
		return false;
	}
	
	public void handleDeleteWithAvailableTran(Connection conn, Long id) {
		String sql = "update category set active = 0 where id = " + id;
		try {
			updateWithAvailableTran(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkHasProductInBill(Long id) {
		List<CategoryModel> listCategory = getListChild(id);
		if(listCategory.size() > 0) {
			for(CategoryModel item:listCategory) {
				return false || checkHasProductInBill(item.getId());
			}
		}
		return checkFinalCategoryHasInBill(id);
	}

	
	private List<String> deleteOneWithAvailableTran(Connection conn, Long id) {
		List<CategoryModel> listCategory = getListChild(id);
		handleDeleteWithAvailableTran(conn, id);
		if(listCategory.size() > 0) {
			List<String> temp = new ArrayList<>();
			for(CategoryModel item:listCategory) {
				temp.addAll(deleteOneWithAvailableTran(conn, item.getId()));
			}
			return temp;
		}
		return productDAO.deleteAllProductByCategoryIdWithAvailableTran(conn, id);
	}

	@Override
	public Long deleteOne(Long id) {
		Long idResult = null;
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			if(!checkHasProductInBill(id)) {
				List<String> images = deleteOneWithAvailableTran(conn, id);
				if(images != null) {
					conn.commit();
					idResult = id;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeConnection(conn);
		}
		return idResult;
	}

	@Override
	public List<CategoryModel> findListParent() {
		StringBuilder sql = new StringBuilder("select * from category c");
		sql.append(" where c.active = 1 and not exists (select * from product p where p.categoryid = c.id and p.active = 1)");
		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public List<CategoryModel> findListHasNotChild() {
		StringBuilder sql = new StringBuilder("select * from category c");
		sql.append(" where not exists (select * from category c1 where c1.parentid = c.id and c1.active =1)");
		return query(sql.toString(), new CategoryMapper());
	}

	
}
