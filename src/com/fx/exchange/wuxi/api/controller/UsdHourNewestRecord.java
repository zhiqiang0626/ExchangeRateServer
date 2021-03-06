package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.UsdHourInfo;
import com.fx.exchange.wuxi.api.model.UsdRateCache;
import com.fx.exchange.wuxi.common.util.StringConst;
import com.fx.exchange.wuxi.common.util.TimeUtil;
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
		//本地是否满足最新一小时数据的判断
		if(UsdRateCache.usdHourInfo != null && !TimeUtil.checkIsLasterHourTime(UsdRateCache.usdTime)){
			usdHourInfo = UsdRateCache.usdHourInfo;
			logger.info(StringConst.ERROR_MSG_08);
			return SUCCESS;
		}
		//数据库的初期化
		SqlMapClient sqlMap = DBOperation.getSqlMapInstance();

		try {
			//对象的取得
			usdHourInfo = (UsdHourInfo)sqlMap.queryForObject("GetUsdHourObject");
			if (usdHourInfo == null) {
				code = -1;
				logger.info(StringConst.ERROR_MSG_01);
			}else{
				//cache中进行设值
				UsdRateCache.usdHourInfo = usdHourInfo;
				UsdRateCache.usdTime = Long.parseLong(usdHourInfo.getUpdateTime());
				logger.info("最新记录服务器时间："+UsdRateCache.usdTime);
			}
			
		}catch (SQLException e) {
			code = -2;
			logger.info(e.toString());
		}catch (Exception e) {
			//异常的处理
			code = -3;
			logger.info(e.toString());
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
