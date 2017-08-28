package com.robote.spring.shiro.chapter3.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * <p>@Title:MyRolePermissionResolver.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午2:01:24
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRolePermissionResolver implements RolePermissionResolver{

	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)) {  
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));  
        }  
        return null;  
	}

}
