package com.rebote.spring.shiro.chapter2.strategy;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

/**
 * <p>@Title:AtLeastTwoAuthenticatorStrategy.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月25日下午1:42:22
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy{
	
	//在所有Realm验证之前调用  
	@Override
	public AuthenticationInfo beforeAllAttempts(
			Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		return super.beforeAllAttempts(realms, token);
	}
	//在每个Realm之前调用
	@Override
	public AuthenticationInfo beforeAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		return aggregate;//返回之前合并的
	}
	//在每个Realm之后调用
	@Override
	public AuthenticationInfo afterAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t)
			throws AuthenticationException {
		AuthenticationInfo info;
		 if (singleRealmInfo == null) {
	            info = aggregateInfo;
	        } else {
	            if (aggregateInfo == null) {
	                info = singleRealmInfo;
	            } else {
	                info = merge(singleRealmInfo, aggregateInfo);
	            }
	        }

	        return info;
	}
	
	@Override
	protected AuthenticationInfo merge(AuthenticationInfo info,
			AuthenticationInfo aggregate) {
		// TODO Auto-generated method stub
		return super.merge(info, aggregate);
	}
	//在所有Realm之后调用
	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token,
			AuthenticationInfo aggregate) throws AuthenticationException {
		if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals()) || aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that at least two realm can " +
                    "authenticate these tokens.");
        }

        return aggregate;
	}
	
}
