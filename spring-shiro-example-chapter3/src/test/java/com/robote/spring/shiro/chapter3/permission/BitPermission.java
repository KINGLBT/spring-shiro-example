package com.robote.spring.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;

import com.alibaba.druid.util.StringUtils;

/**
 * <p>@Title:BitPermission.java</p>
 * <p>@Description: </p>
 * <p>@Copyright: Copyright (c) 2016</p>
 * <p>@Company: 玖富时代</p>
 * @Created on 2017年8月28日下午1:38:16
 * @author luomeng@9fbank.com.cn
 * @version 1.0
 */
public class BitPermission implements Permission{
	
	private String resourceIdentify;  
	private int permissionBit;  
	private String instanceId;  
	
	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");  
        if(array.length > 1) {  
            resourceIdentify = array[1];  
        }  
        if(StringUtils.isEmpty(resourceIdentify)) {  
            resourceIdentify = "*";  
        }  
        if(array.length > 2) {  
            permissionBit = Integer.valueOf(array[2]);  
        }  
        if(array.length > 3) {  
            instanceId = array[3];  
        }  
        if(StringUtils.isEmpty(instanceId)) {  
            instanceId = "*";  
        }  
	}
	
	//Permission接口提供了boolean implies(Permission p)方法用于判断权限匹配的；
	public boolean implies(Permission p) {
		if(!(p instanceof BitPermission)) {  
            return false;  
        }  
        BitPermission other = (BitPermission) p;  
        if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {  
            return false;  
        }  
        if(!(this.permissionBit ==0 || (this.permissionBit & other.permissionBit) != 0)) {  
            return false;  
        }  
        if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {  
            return false;  
        }  
        return true;  
	}

	public String getResourceIdentify() {
		return resourceIdentify;
	}

	public void setResourceIdentify(String resourceIdentify) {
		this.resourceIdentify = resourceIdentify;
	}

	public int getPermissionBit() {
		return permissionBit;
	}

	public void setPermissionBit(int permissionBit) {
		this.permissionBit = permissionBit;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
}
