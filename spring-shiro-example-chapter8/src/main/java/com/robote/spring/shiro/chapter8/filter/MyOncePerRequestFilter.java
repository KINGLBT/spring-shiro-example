package com.robote.spring.shiro.chapter8.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

/**
 * <p>@Title:MyOncePerRequestFilter.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月11日下午6:23:37
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("=========once per request filter");
        chain.doFilter(request, response);
	}

}
