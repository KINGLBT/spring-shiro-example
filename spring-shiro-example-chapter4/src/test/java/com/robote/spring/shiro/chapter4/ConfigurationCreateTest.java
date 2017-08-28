package com.robote.spring.shiro.chapter4;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>@Title:ConfigurationCreateTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午3:46:13
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class ConfigurationCreateTest {
	@Test
	public void test(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory =  
		         new IniSecurityManagerFactory("classpath:shiro-config.ini");  
		  
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
		  
		//将SecurityManager设置到SecurityUtils 方便全局使用  
		SecurityUtils.setSecurityManager(securityManager);  
		Subject subject = SecurityUtils.getSubject();  
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
		subject.login(token);  
		  
		Assert.assertTrue(subject.isAuthenticated());
	}
}
