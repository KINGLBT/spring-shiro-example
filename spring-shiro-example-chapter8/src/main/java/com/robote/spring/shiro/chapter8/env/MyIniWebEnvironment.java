package com.robote.spring.shiro.chapter8.env;

import javax.servlet.Filter;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * <p>@Title:MyIniWebEnvironment.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月4日下午3:02:55
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyIniWebEnvironment extends IniWebEnvironment{

	@Override
	protected FilterChainResolver createFilterChainResolver() {
		//在此处扩展自己的FilterChainResolver
		//1、创建FilterChainResolver  
		PathMatchingFilterChainResolver filterChainResolver =  
		        new PathMatchingFilterChainResolver();  
		//2、创建FilterChainManager  
		DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();  
		//3、注册Filter  
		for(DefaultFilter filter : DefaultFilter.values()) {  
		    filterChainManager.addFilter(  
		        filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));  
		}  
		//4、注册URL-Filter的映射关系  
		filterChainManager.addToChain("/login.jsp", "authc");  
		filterChainManager.addToChain("/unauthorized.jsp", "anon");  
		filterChainManager.addToChain("/**", "authc");  
		filterChainManager.addToChain("/**", "roles", "admin");  
		  
		//5、设置Filter的属性  
		FormAuthenticationFilter authcFilter =  
		         (FormAuthenticationFilter)filterChainManager.getFilter("authc");  
		authcFilter.setLoginUrl("/login.jsp");  
		RolesAuthorizationFilter rolesFilter =  
		          (RolesAuthorizationFilter)filterChainManager.getFilter("roles");  
		rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");  
		  
		filterChainResolver.setFilterChainManager(filterChainManager);  
		return filterChainResolver; 
	}
	
}
