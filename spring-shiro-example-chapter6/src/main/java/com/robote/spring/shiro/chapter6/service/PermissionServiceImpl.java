package com.robote.spring.shiro.chapter6.service;

import com.robote.spring.shiro.chapter6.dao.PermissionDao;
import com.robote.spring.shiro.chapter6.dao.PermissionDaoImpl;
import com.robote.spring.shiro.chapter6.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }

	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}
}
