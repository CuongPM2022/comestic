package com.comestic.dao;

public interface IUserDAO {
	Long  getUserIdByUsernameAndPassword(String username, String password);
}
