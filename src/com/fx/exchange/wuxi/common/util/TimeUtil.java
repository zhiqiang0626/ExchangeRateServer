package com.fx.exchange.wuxi.common.util;

public class TimeUtil {

	public static boolean checkIsLasterHourTime(long fromTime){
		//当前时间取得
		long nowTime = System.currentTimeMillis()/1000;
		System.out.println(nowTime);
		if((nowTime - fromTime) >= 3600){
			return true;
		}
		return false;
	}
}
