package com.robote.spring.shiro.chapter6.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.robote.spring.shiro.chapter6.BaseTest;

/**
 * <p>
 * @Title:ServiceTest.java
 * </p>
 * <p>
 * @Description:
 * </p>
 * <p>
 * @Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * @Company: 玖富时代
 * </p>
 * 
 * @Created on 2017年8月29日下午5:45:52
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class ServiceTest extends BaseTest {
	@Test
	public void testUserRolePermissionRelation() {

		Set<String> roles = userService.findRoles(u1.getUsername());
		Assert.assertEquals(1, roles.size());
		Assert.assertTrue(roles.contains(r1.getRole()));

		Set<String> permissions = userService.findPermissions(u1.getUsername());
		Assert.assertEquals(3, permissions.size());
		Assert.assertTrue(permissions.contains(p3.getPermission()));

		// li
		roles = userService.findRoles(u2.getUsername());
		Assert.assertEquals(0, roles.size());
		permissions = userService.findPermissions(u2.getUsername());
		Assert.assertEquals(0, permissions.size());

		// 解除 admin-menu:update关联
		roleService.uncorrelationPermissions(r1.getId(), p3.getId());
		permissions = userService.findPermissions(u1.getUsername());
		Assert.assertEquals(2, permissions.size());
		Assert.assertFalse(permissions.contains(p3.getPermission()));

		// 删除一个permission
		permissionService.deletePermission(p2.getId());
		permissions = userService.findPermissions(u1.getUsername());
		Assert.assertEquals(1, permissions.size());

		// 解除 zhang-admin关联
		userService.uncorrelationRoles(u1.getId(), r1.getId());
		roles = userService.findRoles(u1.getUsername());
		Assert.assertEquals(0, roles.size());

	}
}
