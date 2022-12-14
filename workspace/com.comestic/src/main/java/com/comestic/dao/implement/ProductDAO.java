package com.comestic.dao.implement;

import java.util.List;

import com.comestic.dao.IProductDAO;
import com.comestic.dao.mapper.ProductDetailMapper;
import com.comestic.dao.mapper.ProductMapper;
import com.comestic.model.ProductModel;
import com.comestic.utils.Paging;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
	
	@Override
	public List<ProductModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select p.id, categoryid, manufactureid, m.name as manufacturename, typeproductid, p.code, p.name, date, totalcomment,");
		sql.append(" shortdescription, longdescription, view, state, ishot, percentdes, numberlimit, star, numbervariety,");
		sql.append(" price, number, i.name as image");
		sql.append(" from product p, manufacture m, variety v, variety_image v_i, image i");
		sql.append(" where p.manufactureid = m.id and p.id = v.productid");
		sql.append(" and v.id = v_i.varietyid and v_i.imageid = i.id and v.isavatar = 1");
		setPaging(sql,paging);
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public Integer totalItem(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from product p, variety v");
					  sql.append(" where v.productid = p.id and v.isavatar = 1");
		setFilter(sql, paging);
		return count(sql.toString());
	}

	@Override
	public List<ProductModel> findByBestSeller(Paging paging) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id, categoryid, manufactureid, m.name as manufacturename, typeproductid, p.code, p.name, date, totalcomment,");
		sql.append(" shortdescription, longdescription, view, state, ishot, percentdes, numberlimit, star, numbervariety,");
		sql.append(" price, number, i.name as image");
		sql.append(" from product p, manufacture m, variety v, variety_image v_i, image i");
		sql.append(" where p.manufactureid = m.id");
		sql.append(" and p.id = v.productid and v.id = v_i.varietyid and v_i.imageid = i.id");
		sql.append(" and v.id in (select distinct varietyid from bill_detail group by(varietyid) order by sum(number) desc)");
		setPaging(sql, paging);
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public Integer totalItemBestSeller(Paging paging) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from product p, variety v");
		sql.append(" where v.productid = p.id");
		sql.append(" and v.id in (select distinct varietyid from bill_detail group by(varietyid) order by sum(number) desc)");
		setFilter(sql, paging);
		return count(sql.toString());
	}

	@Override
	public ProductModel findOne(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id, categoryid, c.name as categoryname, p.percentdes, p.name, star, totalpreview, shortdescription, longdescription");
		sql.append(" from product p, category c");
		sql.append(" where p.categoryid = c.id and p.id = ");
		sql.append(id);
		List<ProductModel> listResult = query(sql.toString(), new ProductDetailMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

}
