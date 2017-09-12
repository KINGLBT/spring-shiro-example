package com.robote.spring.shiro.chapter8.filter;

import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * <p>@Title:MyPathMatchingFilter.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月12日下午4:12:39
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class MyPathMatchingFilter extends PathMatchingFilter {

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		System.out.println("url matches,config is "
				+ Arrays.toString((String[]) mappedValue));
		return true;
	}

}
