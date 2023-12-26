package com.comestic.service.implement;

import javax.inject.Inject;

import com.comestic.dao.IUserDAO;
import com.comestic.model.UserModel;
import com.comestic.service.IUserService;

public class UserService implements IUserService {
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public Long getUserIdByUserModel(UserModel userModel) {
		return userDAO.getUserIdByUsernameAndPassword(userModel.getUsername(), userModel.getPassword());
	}

}
