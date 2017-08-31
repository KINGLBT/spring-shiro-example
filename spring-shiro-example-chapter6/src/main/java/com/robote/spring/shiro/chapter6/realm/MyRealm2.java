package com.robote.spring.shiro.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * <p>@Title:MyRealm2.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月30日上午10:24:35
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRealm2 implements Realm{

	public String getName() {
		// TODO Auto-generated method stub
		return "b";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return new SimpleAuthenticationInfo(  
                "zhang", //身份 字符串类型  
                "123",   //凭据  
                getName() //Realm Name  
        );  
	}

}
