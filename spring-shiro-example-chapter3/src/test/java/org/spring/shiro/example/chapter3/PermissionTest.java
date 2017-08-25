package org.spring.shiro.example.chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@Title:PermissionTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月25日下午3:18:28
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class PermissionTest extends BaseTest{
	private static final transient Logger log = LoggerFactory.getLogger(PermissionTest.class);
	@Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }
	
	@Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }
	
	@Test
    public void testWildcardPermission1() {
        login("classpath:shiro-permission.ini", "li", "123");

        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }
	
	@Test
    public void testWildcardPermission2() {
        login("classpath:shiro-permission.ini", "zhao", "123");
        // 通过“system:user:update,delete”验证"system:user:update, system:user:delete"是没问题的
        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }
	
	@Test
    public void testWildcardPermission3() {
        login("classpath:shiro-permission.ini", "zhao1", "123");
        // 通过"system:user:update, system:user:delete"验证“system:user:update,delete”是不行的
        subject().checkPermissions("system:user:update,delete");
    }
	
	@Test
    public void testWildcardPermission4() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("system:user:create,delete,update,view");
        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }
	
	@Test
    public void testWildcardPermission5() {
        login("classpath:shiro-permission.ini", "li1", "123");
        subject().checkPermissions("user:view");
        subject().checkPermissions("system:view");
        // 异常 用户拥有所有资源的“view”所有权限。假设判断的权限是“"system:user:view”，那么需要“role5=*:*:view”这样写才行。
        subject().checkPermissions("system:user:view");
        // 异常
        subject().checkPermissions("user:update");
    }
	
	@Test
    public void testWildcardPermission6() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view:1");
        
        // 验证 资源user的实例1 是否拥有delete 以及 update权限
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        
        subject().checkPermissions("user:auth:1", "user:auth:2");

    }
	
	@Test
    public void testWildcardPermission7() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view:1");
        
        // 验证 资源user的实例1 是否拥有delete 以及 update权限
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        
        subject().checkPermissions("user:auth:1", "user:auth:2");

    }
	
	/**
	 * 如“user:view”等价于“user:view:*”；
	 * 而“organization”等价于“organization:*”或者“organization:*:*”。
	 * 可以这么理解，这种方式实现了前缀匹配。
		另外如“user:*”可以匹配如“user:delete”、“user:delete”可以匹配如“user:delete:1”、“user:*:1”可以匹配如“user:view:1”、“user”可以匹配“user:view”或“user:view:1”等。即*可以匹配所有，不加*可以进行前缀匹配；但是如“*:view”不能匹配“system:user:view”，需要使用“*:*:view”，即后缀匹配必须指定前缀（多个冒号就需要多个*来匹配）。 
	 */
	@Test
    public void testWildcardPermission8() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("menu:view:1");

        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

    }

    @Test
    public void testWildcardPermission9() {
        login("classpath:shiro-permission.ini", "li", "123");
        
        //如下两种方式是等价的：
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));

    }
	
}
