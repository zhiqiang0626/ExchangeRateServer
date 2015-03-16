package com.fx.exchange.wuxi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCheck {

	private final static String check_in_start_time = PropertiesUtil.properties.getProperty("check_in_start_time");
	private final static String check_in_end_time = PropertiesUtil.properties.getProperty("check_in_end_time");
	private final static String vote_start_time = PropertiesUtil.properties.getProperty("vote_start_time");
	private final static String vote_end_time = PropertiesUtil.properties.getProperty("vote_end_time");
	private final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/*
	 * checkresult : -1:投票时间已过 0:提前可以投两票 1:投票中
	 * 
	 */
	public static int VoteTimeCheck(){
		int checkresult = -1;
		try {
			Date voteStartTime = df.parse(vote_start_time);
			Date voteEndTime = df.parse(vote_end_time);
			Date now = new Date();
			if(now.before(voteStartTime)){
				checkresult = 0;
			}else if(voteEndTime.before(now)){
				checkresult = -1;
			}else{
				checkresult = 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkresult;
	}
	
	/*
	 * checkresult : -1:签到时间已过 0:签到未开放 1:签到有效
	 * 
	 */
	public static int CheckInTimeCheck(){
		int checkresult = -1;
		try {
			Date checkInStartTime = df.parse(check_in_start_time);
			Date checkInEndTime = df.parse(check_in_end_time);
			Date now = new Date();
			if(now.before(checkInStartTime)){
				checkresult = 0;
			}else if(checkInEndTime.before(now)){
				checkresult = -1;
			}else{
				checkresult = 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkresult;

	}
}
