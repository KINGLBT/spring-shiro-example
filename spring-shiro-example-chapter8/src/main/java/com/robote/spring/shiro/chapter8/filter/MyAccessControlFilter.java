package com.robote.spring.shiro.chapter8.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

/**
 * <p>@Title:MyAccessControlFilter.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月12日下午4:22:03
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyAccessControlFilter extends AccessControlFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		System.out.println("no access allowed");
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		System.out.println("访问拒绝也不自己处理，继续拦截器链的执行");  
        return true; 
	}


}
