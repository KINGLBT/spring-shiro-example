package com.robote.spring.shiro.chapter6.dao;

import com.robote.spring.shiro.chapter6.entity.Permission;

/**
 * <p>@Title:PermissionDao.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月29日下午5:16:41
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public interface PermissionDao {
	
	public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
