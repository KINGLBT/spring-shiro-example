package com.robote.spring.shiro.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>@Title:AuthorizerTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日上午11:53:56
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class AuthorizerTest extends BaseTest{
	@Test
	public void testIsPermitted(){
		login("classpath:shiro-authorizer.ini", "zhang", "123");  
        //判断拥有权限：user:create  
        Assert.assertTrue(subject().isPermitted("user1:update"));  
        Assert.assertTrue(subject().isPermitted("user2:update"));  
        //通过二进制位的方式表示权限  
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限  
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限  
        Assert.assertTrue(subject().isPermitted("+user2+10"));//修改及查看  
  
        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限  
  
        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限  
	}
	
	@Test
    public void testIsPermitted2() {
        login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//修改及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
