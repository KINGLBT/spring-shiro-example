package com.robote.spring.shiro.chapter5;

import org.junit.Test;

/**
 * <p>@Title:PasswordTest.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午5:43:12
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class PasswordTest extends BaseTest{
	
	@Test
    public void testPasswordServiceWithMyRealm() {
		
        login("classpath:shiro-passwordservice.ini", "wu", "123");
    }
}
