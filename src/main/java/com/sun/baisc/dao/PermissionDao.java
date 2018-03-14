package com.sun.baisc.dao;

import java.util.List;

import com.sun.baisc.model.Permission;

public interface PermissionDao {

	public List<Permission> getPermissionsByUserName(String userName);
}
