package com.startdima.service;

import java.sql.SQLException;

import com.startdima.dao.LoginDaoImpl;
import com.startdima.pojo.User;

public class LoginService {
	
	LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
	
	/**
	 * 用户登陆
	 * @param user
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean login(User user) throws SQLException {
		return loginDaoImpl.login(user);
	}
}
