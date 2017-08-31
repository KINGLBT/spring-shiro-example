package com.robote.spring.shiro.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import com.robote.spring.shiro.chapter6.entity.User;

/**
 * <p>@Title:MyRealm3.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月30日上午10:25:41
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyRealm3 implements Realm{

	public String getName() {
		return "c";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		User user = new User("zhang", "123");  
        return new SimpleAuthenticationInfo(  
                user, //身份 User类型  
                "123",   //凭据  
                getName() //Realm Name  
        );  
	}

}
