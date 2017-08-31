package com.robote.spring.shiro.chapter6.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * <p>@Title:FormFilterLoginServlet.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月31日上午11:54:34
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String errorClassName = (String)req.getAttribute("shiroLoginFailure");  
        if(UnknownAccountException.class.getName().equals(errorClassName)) {  
            req.setAttribute("error", "用户名/密码错误");  
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {  
            req.setAttribute("error", "用户名/密码错误");  
        } else if(errorClassName != null) {  
            req.setAttribute("error", "未知错误：" + errorClassName);  
        }  
        req.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(req, resp);  
	}
	
	
	
}
