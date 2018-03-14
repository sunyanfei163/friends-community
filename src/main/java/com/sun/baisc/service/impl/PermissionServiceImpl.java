package com.sun.baisc.service.impl;

import java.util.List;

import com.sun.baisc.dao.PermissionDao;
import com.sun.baisc.model.Permission;
import com.sun.baisc.service.PermissionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao;
	
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
	public JSONArray getMenuByUserName(String userName) {
		List<Permission> permissions = permissionDao.getPermissionsByUserName(userName);
		JSONArray perArr = getMenuByParentId(0, permissions);
		return perArr;
	}
	
	private JSONArray getMenuByParentId(int parentId, List<Permission> permissions) {
		JSONArray trees = new JSONArray();
		for (Permission permission : permissions) {
			if(parentId == permission.getParentId()) {
				JSONObject obj = new JSONObject();
				obj.put(permission.getName(), permission.getUrl());
				JSONArray childArr = getMenuByParentId(permission.getId(), permissions);
				if(childArr.size() > 0) {
					obj.put("child", childArr);
				}
				trees.add(obj);
			}
		}
		return trees;
	}

}
