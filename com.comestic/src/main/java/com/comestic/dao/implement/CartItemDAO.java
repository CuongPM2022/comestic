package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.ICartItemDAO;
import com.comestic.dao.IProductDAO;
import com.comestic.dao.IVarietyDAO;
import com.comestic.dao.mapper.CartItemDetailMapper;
import com.comestic.dao.mapper.CartItemMapper;
import com.comestic.model.CartItemModel;
import com.comestic.model.VarietyModel;

public class CartItemDAO extends AbstractDAO<CartItemModel> implements ICartItemDAO {

	@Inject
	private IVarietyDAO varietyDAO;
	
	@Inject
	private IProductDAO productDAO;
	
	private List<Object> listInput = new ArrayList<Object>();
	
	@Override
	public CartItemModel findOneByVarietyId(Long varietyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id as productid, p.name as productname, v.id as varietyid, price, v.percentdes, number, i.name as image");
		sql.append(" from product p, variety v, variety_image vi, image i");
		sql.append(" where p.id = v.productid and vi.varietyid = v.id");
		sql.append(" and vi.imageid = i.id and v.id = " + varietyId);
		List<CartItemModel> listResult = query(sql.toString(), new CartItemMapper());
		if(listResult != null && listResult.size() > 0) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public Integer getNumberOfVarietyById(Long varietyId) {
		StringBuilder sql = new StringBuilder("select number from variety where id=" + varietyId);
		return count(sql.toString());
	}
	
	@Override
	public CartItemModel saveWithAcailableTran(Connection conn, CartItemModel cartItemModel) {
		
		VarietyModel varietyModel = varietyDAO.findOne(cartItemModel.getVarietyId());
		varietyModel.setNumber(varietyModel.getNumber() - cartItemModel.getUserQuantity());
		
		if(varietyModel.getNumber() < 0) {
			cartItemModel.setUserQuantity(varietyModel.getNumber());
		} else {
			// cap nhat so luong cho variety
			varietyDAO.subQuantityWithAvailableTran(conn, cartItemModel.getVarietyId(), cartItemModel.getUserQuantity());
			
			// them du lieu cho bill_detail
			Integer percentSale = productDAO.getPercentSale(varietyModel.getProductId());
			StringBuilder sql = new StringBuilder();
			sql.append("Insert into bill_detail(billid,productid,varietyid,number,priceproduct,pricesold,varietydetail)");
			sql.append(" values (?,?,?,?,?,?,?)");
			this.listInput.clear();
			this.listInput.add(cartItemModel.getCartId());
			this.listInput.add(varietyModel.getProductId());
			this.listInput.add(cartItemModel.getVarietyId());
			this.listInput.add(cartItemModel.getUserQuantity());
			
			Double temp = varietyModel.getPrice();
			this.listInput.add(temp);
			temp = temp * (1 - (double) percentSale/100);
			this.listInput.add(temp);
			this.listInput.add(varietyModel.findStrListAttribute());
			
			try {
				Long id = insertWithAvailableTran(conn, sql.substring(0), this.listInput);
				cartItemModel.setId(id);
				cartItemModel.setPrice(varietyModel.getPrice());
				cartItemModel.setPercentDes(percentSale);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cartItemModel;
	}

	@Override
	public List<CartItemModel> findByCartId(Long cartId) {
		StringBuilder sql = new StringBuilder("select bd.id, p.id as productid, p.code as productcode, ");
		sql.append(" concat(p.name, if(strcmp(bd.varietydetail,'') = 0, '', concat(' (', bd.varietydetail, ')'))) as productname,");
		sql.append("  bd.priceproduct as price,");
		sql.append(" (bd.priceproduct - bd.pricesold)/bd.priceproduct * 100 as percentdes, bd.number as userquantity, i.name as image");
		sql.append(" from product p, bill_detail bd,variety v, variety_image vi, image i");
		sql.append(" where bd.productid = p.id and v.productid = p.id and v.id = Vi.varietyid and vi.imageid = i.id");
		sql.append(" and v.isavatar = 1 and vi.isimageavatar = 1 and billid = ");
		sql.append(cartId);
		return query(sql.toString(), new CartItemDetailMapper());
	}

	@Override
	public void deleteAllByCartId(Long cartId) {
		StringBuilder sql = new StringBuilder("delete from bill_detail where billid=");
		sql.append(cartId);
		update(sql.toString());
	}

}
