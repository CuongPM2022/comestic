package com.comestic.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.comestic.dao.IUserDAO;
import com.comestic.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
	
	private Long userId;
	private List<Object> listInput = new ArrayList<Object>();
	@Override
	public Long getUserIdByUsernameAndPassword(String username, String password) {
		this.userId = null;
		StringBuilder sql = new StringBuilder("select id from users where username=? and password=?");
		this.listInput.clear();
		this.listInput.add(username);
		this.listInput.add(password);
		this.userId = (Long) getRecord(sql.toString(), this.listInput);
		return this.userId;
	}
	
}
