package com.comestic.dao.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.comestic.dao.ICartDAO;
import com.comestic.dao.ICartItemDAO;
import com.comestic.dao.mapper.CartDetailMapper;
import com.comestic.dao.mapper.CartMapper;
import com.comestic.model.CartItemModel;
import com.comestic.model.CartModel;
import com.comestic.utils.Paging;
import com.comestic.utils.StringRandom;

public class CartDAO extends AbstractDAO<CartModel> implements ICartDAO {
	
	@Inject
	private ICartItemDAO cartItemDAO;
	private CartItemModel cartItemModel;
	private Long cartId;
	private Double totalMoney, importMoney;
	private List<Object> listInput = new ArrayList<Object>();

	@Override
	public CartModel findOneByUserCart(CartModel cartModel) {
		CartModel newCartModel = new CartModel();
		List<CartItemModel> listResult = new ArrayList<CartItemModel>();
		for(CartItemModel item:cartModel.getListCartItem()) {
			this.cartItemModel = cartItemDAO.findOneByVarietyId(item.getVarietyId());
			if(this.cartItemModel != null) {
				this.cartItemModel.setUserQuantity(item.getUserQuantity());
				listResult.add(this.cartItemModel);
			}
		}
		newCartModel.setListCartItem(listResult);
		return newCartModel;
	}

	@Override
	public CartItemModel findOneItemCartByVarietyId(Long varietyId) {
		return this.cartItemDAO.findOneByVarietyId(varietyId);
	}

	@Override
	public Integer getNumberOfVarietyById(Long varietyId) {
		return cartItemDAO.getNumberOfVarietyById(varietyId);
	}
	
	@Override
	public CartModel save(CartModel cartModel) {
		this.cartId = null;
		this.totalMoney = 0D;
		this.importMoney = 0D;
		
		StringBuilder sql = new StringBuilder();
		sql.append("Insert into bill(code,totalmoney,importmoney,date,namecustomer,gender,phone,address,email,method,note,stateid)");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?)");
		this.listInput.clear();
		this.listInput.add(StringRandom.nextString(6));
		this.listInput.add(this.totalMoney);
		this.listInput.add(this.importMoney);
		this.listInput.add(new Timestamp(System.currentTimeMillis()));
		this.listInput.add(cartModel.getNameCustomer());
		this.listInput.add(cartModel.getGender().equals("male") ? "Nam":"Nữ");
		this.listInput.add(cartModel.getPhone());
		this.listInput.add(cartModel.getAddress());
		this.listInput.add(cartModel.getEmail());
		this.listInput.add(cartModel.getMethod());
		this.listInput.add(cartModel.getNote());
		this.listInput.add(1L);
		
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			// da co cartId
			this.cartId = insertWithAvailableTran(conn, sql.toString(), this.listInput);
			
			List<CartItemModel> listCartItemModel = new ArrayList<>();
			CartItemModel cartItemTemp = null;
			boolean kt = cartModel.getListCartItem().size() > 0;
			for(CartItemModel item:cartModel.getListCartItem()) {
				item.setCartId(cartId);
				cartItemTemp = cartItemDAO.saveWithAcailableTran(conn, item);
				listCartItemModel.add(cartItemTemp);
				Integer userQuantity = cartItemTemp.getUserQuantity();
				if(cartItemTemp.getUserQuantity() < 0) {
					kt = false;
				} else {
					Double price = cartItemTemp.getPrice();
					this.totalMoney += userQuantity * price;
					this.importMoney += userQuantity * (1 - (double) cartItemTemp.getPercentDes()/100) * price;
				}
			}
			
			cartModel.setListCartItem(listCartItemModel);
			if(kt) {
				// cap nhat lai tong so tien cho bill
				sql = new StringBuilder("Update bill set totalmoney=?, importmoney=? where id=?");
				this.listInput.clear();
				this.listInput.add(this.totalMoney);
				this.listInput.add(this.importMoney);
				this.listInput.add(this.cartId);
				updateWithAvailableTran(conn, sql.toString(), this.listInput);
				
				// commit tat ca
				conn.commit();
			}
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				cartModel = null;
				e1.printStackTrace();
			}
			cartModel = null;
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		
		return cartModel;
	}
	

	@Override
	public List<CartModel> findAll(Paging paging) {
		StringBuilder sql = new StringBuilder("select id, code, importmoney, date, namecustomer, stateid from bill");
		setPaging(sql, paging);
		return query(sql.toString(), new CartMapper());
	}

	@Override
	public CartModel findOne(Long id) {
		StringBuilder sql = new StringBuilder("select b.id, code, totalmoney, importmoney, date, namecustomer, gender, phone,");
		sql.append(" address, email, method, note, s.id as stateid, name as statename");
		sql.append(" from bill b, state s where b.stateid = s.id and b.id = " + id);
		List<CartModel> listResult = query(sql.toString(), new CartDetailMapper());
		if(listResult != null && listResult.size() > 0) {
			CartModel cartModel = listResult.get(0);
			cartModel.setListCartItem(cartItemDAO.findByCartId(cartModel.getId()));
			return cartModel;
		}
		return null;
	}

	@Override
	public void updateStateForBill(CartModel cartModel) {
		if(cartModel.getId() != null && cartModel.getStateId() != null) {
			StringBuilder sql = new StringBuilder("Update bill set stateid=? where id=?");
			this.listInput.clear();
			this.listInput.add(cartModel.getStateId());
			this.listInput.add(cartModel.getId());
			update(sql.toString(), this.listInput);
		}
	}

	@Override
	public Integer getTotalNewBill(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from bill where stateid = 1");
		this.listInput.clear();
		setPagingHasFilterDate(sql, paging, this.listInput);
		return count(sql.toString(), this.listInput);
	}

	@Override
	public Integer getTotalBill(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from bill");
		this.listInput.clear();
		setPagingHasFilterDate(sql, paging, this.listInput);
		return count(sql.toString(), this.listInput);
	}

	@Override
	public Double getTotalMoney(Paging paging) {
		StringBuilder sql = new StringBuilder("select sum(importmoney) from bill where stateid != 4");
		this.listInput.clear();
		setPagingHasFilterDate(sql, paging, this.listInput);
		return (Double) getRecord(sql.toString(), this.listInput);
	}

	@Override
	public Integer getTotalCancelBill(Paging paging) {
		StringBuilder sql = new StringBuilder("select count(*) from bill where stateid = 4");
		this.listInput.clear();
		setPagingHasFilterDate(sql, paging, this.listInput);
		return count(sql.toString(), this.listInput);
	}

	@Override
	public void deleteOne(Long id) {
		cartItemDAO.deleteAllByCartId(id);
		StringBuilder sql = new StringBuilder("delete from bill where id=");
		sql.append(id);
		update(sql.toString());
	}

}
