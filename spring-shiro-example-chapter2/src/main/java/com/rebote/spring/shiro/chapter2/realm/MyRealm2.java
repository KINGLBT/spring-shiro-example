package com.rebote.spring.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * <p>@Title:MyRealm1.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月25日上午10:40:57
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRealm2 implements Realm{

	public String getName() {
		return "myRealm2";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String pwd = (String) token.getCredentials();
		
		if(!"zhang".equals(userName)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(pwd)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(userName, pwd, getName());
	}

}
