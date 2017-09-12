package com.robote.spring.shiro.chapter8.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * <p>@Title:FormLoginFilter.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年9月12日下午4:31:16
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class FormLoginFilter extends PathMatchingFilter{
	
	private String loginUrl = "/login.jsp";  
    private String successUrl = "/";
	
	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return true;
		}
		HttpServletRequest req = (HttpServletRequest) request;  
	    HttpServletResponse resp = (HttpServletResponse) response; 
		
	    if(isLoginRequest(req)) {  
            if("post".equalsIgnoreCase(req.getMethod())) {//form表单提交  
                boolean loginSuccess = login(req); //登录  
                if(loginSuccess) {  
                    return redirectToSuccessUrl(req, resp);  
                }  
            }  
            return true;//继续过滤器链  
        } else {//保存当前地址并重定向到登录界面  
            saveRequestAndRedirectToLogin(req, resp);  
            return false;  
        }
	}
	
	private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
        WebUtils.redirectToSavedRequest(req, resp, successUrl);  
        return false;  
    }  
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
        WebUtils.saveRequest(req);  
        WebUtils.issueRedirect(req, resp, loginUrl);  
    }  
  
    private boolean login(HttpServletRequest req) {  
        String username = req.getParameter("username");  
        String password = req.getParameter("password");  
        try {  
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));  
        } catch (Exception e) {  
            req.setAttribute("shiroLoginFailure", e.getClass());  
            return false;  
        }  
        return true;  
    }  
	
	private boolean isLoginRequest(HttpServletRequest req) {  
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));  
    }  
	
}
