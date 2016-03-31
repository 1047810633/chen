package com.chen.service;

import com.chen.annotation.Tran;
import com.chen.domain.User;

public interface UserService  extends Service{

	/**
	 * 注册用户
	 * @param user 封装了用户数据的userbean
	 */
	@Tran
	void regist(User user);

	/**
	 * 激活用户的方法
	 * @param activecode 激活码
	 */
	void acitveUser(String activecode);

	/**
	 * 根据用户名密码查找用户
	 * @param username
	 * @param password
	 */
	User getUserByNameAndPsw(String username, String password);

}
