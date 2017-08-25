package org.spring.shiro.example.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * <p>@Title:BaseTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月25日下午3:19:37
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public abstract class BaseTest {
	/**
	 * 登录校验
	 * @param path
	 * @param userName
	 * @param password
	 */
	public void login(String path,String userName,String password){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory(path);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		subject.login(token);
	}
	
	/**
	 * 获取主题
	 * @return
	 */
	public Subject subject(){
		return SecurityUtils.getSubject();
	}
	
	 @After
	 public void tearDown() throws Exception {
		 ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
	 }
	
}
