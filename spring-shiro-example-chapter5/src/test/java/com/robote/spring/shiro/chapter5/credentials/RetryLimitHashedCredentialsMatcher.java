package com.robote.spring.shiro.chapter5.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * <p>@Title:RetryLimitHashedCredentialsMatcher.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月29日下午4:42:19
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
	
	private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		
		String username = (String)token.getPrincipal();  
        //retry count + 1  
        Element element = passwordRetryCache.get(username);  
        if(element == null) {  
            element = new Element(username , new AtomicInteger(0));  
            passwordRetryCache.put(element);  
        }  
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();  
        if(retryCount.incrementAndGet() > 5) {  
            //if retry count > 5 throw  
            throw new ExcessiveAttemptsException();  
        }  
        
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {  
            passwordRetryCache.remove(username);  
        }  
        return matches;  
	}
	
}
