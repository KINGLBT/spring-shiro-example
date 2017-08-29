package com.robote.spring.shiro.chapter5.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * <p>@Title:MyRealm2.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月29日下午1:52:22
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRealm2 extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = "liu"; //用户名及salt1  
	    String password = "2140e2876c0727640e7c5cc8f233f74f"; //加密后的密码  
	    String salt2 = "20b675069b61b0fb8fdd4b012031772c";  
	    SimpleAuthenticationInfo ai =   
	        new SimpleAuthenticationInfo(username, password, getName());
	    ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2)); //盐是用户名+随机数  
	    return ai;  
	}

}
