package com.robote.spring.shiro.chapter6.dao;

import com.robote.spring.shiro.chapter6.entity.Role;

/**
 * <p>@Title:RoleDao.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月29日下午5:21:15
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public interface RoleDao {
	public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
