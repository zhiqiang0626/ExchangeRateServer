package com.fx.exchange.wuxi.api.controller;

import org.apache.log4j.Logger;
import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.UsdHourInfo;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class UsdHourNewestRecord extends ActionSupport {

	//serialVersionUID
	private static final long serialVersionUID = 1L;
	//Logger的初期设定
	private static Logger logger = Logger.getLogger("ScriptMaint");

	//返回json结果状态值 0：正常  -1：异常
	private int code = 0;
	//返回的message
	private String messages;
	//最新一条汇率的信息
	private UsdHourInfo usdHourInfo;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("UsdHourNewestRecord START: " );
		//数据库的初期化
		SqlMapClient sqlMap = DBOperation.getSqlMapInstance();

		try {
			//对象的取得
			usdHourInfo = (UsdHourInfo)sqlMap.queryForObject("GetUsdHourObject");
			if (usdHourInfo == null) {
				code = -1;
				messages = "数据取得异常";
			}else{
				code = 0;
			}
			
		} catch (Exception e) {
			//异常的处理
			code = -1;
			messages = "数据取得异常";
			logger.info(e.toString() );
		}
		
		logger.debug("UsdHourNewestRecord END: " );
		return SUCCESS;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public UsdHourInfo getUsdHourInfo() {
		return usdHourInfo;
	}

	public void setUsdHourInfo(UsdHourInfo usdHourInfo) {
		this.usdHourInfo = usdHourInfo;
	}
	
}
