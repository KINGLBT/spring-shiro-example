package com.robote.spring.shiro.chapter8.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

/**
 * <p>@Title:MyAdviceFilter.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月11日下午6:27:16
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyAdviceFilter extends AdviceFilter{

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("====预处理/前置处理");  
		return true;
	}

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("====后处理/后置返回处理");  
	}

	@Override
	public void afterCompletion(ServletRequest request,
			ServletResponse response, Exception exception) throws Exception {
		System.out.println("====完成处理/后置最终处理");
	}
	
}
