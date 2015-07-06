package com.fx.exchange.wuxi.api.model;


public class UsdRateCache {
	public static long usdTime= System.currentTimeMillis();
	//最新一条记录对象
	public static  UsdHourInfo usdHourInfo;//年会中奖名单
	
	public static  UsdHourInfo getUsdHourInfo() {
		return usdHourInfo;
	}
	public static synchronized void setUsdHourInfo(UsdHourInfo usdHourInfo) {
		UsdRateCache.usdHourInfo = usdHourInfo;
	}
	
	
}
