package com.sun.baisc.dao;

import com.sun.baisc.model.User;

public interface UserDao {

	public User getByUserName(String userName);
}
