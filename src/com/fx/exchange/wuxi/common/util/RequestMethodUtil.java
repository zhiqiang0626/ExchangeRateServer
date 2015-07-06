package com.fx.exchange.wuxi.common.util;

import org.apache.struts2.ServletActionContext;

public class RequestMethodUtil {
	public static boolean isPostRequest(){
		String method = ServletActionContext.getRequest() .getMethod();
		System.out.println(method);
		//请求类型的判定处理
		if(!"POST".equals(method.toUpperCase())){
			return false;
		}
		return true;
	}
}
