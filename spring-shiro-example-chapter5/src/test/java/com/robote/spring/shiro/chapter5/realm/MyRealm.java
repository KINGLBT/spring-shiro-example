package com.robote.spring.shiro.chapter5.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <p>@Title:MyRealm.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午5:53:49
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRealm extends AuthorizingRealm{
	
	private PasswordService passwordService;  
    public void setPasswordService(PasswordService passwordService) {  
        this.passwordService = passwordService;  
    } 
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
	 return new SimpleAuthenticationInfo(  
                "wu",  
                passwordService.encryptPassword("123"),  
                getName());  
	}

}
