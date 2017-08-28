package com.robote.spring.shiro.chapter4;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <p>@Title:NonConfigurationCreateTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午3:39:56
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class NonConfigurationCreateTest {
	
	@Test
	public void test(){
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//设置authenticator  
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();  
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());  
		defaultSecurityManager.setAuthenticator(authenticator); 
		
		//设置Realm
		DruidDataSource ds = new DruidDataSource();  
		ds.setDriverClassName("com.mysql.jdbc.Driver");  
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");  
		ds.setUsername("root");  
		ds.setPassword("root");  
		
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		//defaultSecurityManager.setRealm(jdbcRealm);
		defaultSecurityManager.setRealms(Arrays.asList((Realm) jdbcRealm));
		
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		subject.login(token);
		Assert.assertTrue(subject.isAuthenticated()); 
	}
	
}
