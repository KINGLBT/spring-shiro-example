package com.robote.spring.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * <p>@Title:BitAndWildPermissionResolver.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日上午11:55:57
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class BitAndWildPermissionResolver implements PermissionResolver{

	public Permission resolvePermission(String permissionString) {
		if(permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
	}

}
