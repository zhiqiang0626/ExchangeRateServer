package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.common.util.StringConst;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class GetUsdPushStatus extends ActionSupport {

	//serialVersionUID
	private static final long serialVersionUID = 1L;
	//Logger的初期设定
	private static Logger logger = Logger.getLogger("ScriptMaint");

	//返回json结果状态值 0：正常  -1：异常
	private int code = 0;
	//返回的message
	private String messages;
	//用户ID
	private String registrationID;
	//push flag
	private String pushStatus;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("GetUsdPushStatus START: " );
		//数据库的初期化
		SqlMapClient sqlMap = DBOperation.getSqlMapInstance();

		try {
			//对象的取得
			pushStatus = (String)sqlMap.queryForObject("GetUsdPushStatus",registrationID);
			if (pushStatus == null || "".equals(pushStatus)) {
				code = -1;
				messages = StringConst.ERROR_MSG_01;
				logger.info(messages);
			}
			
		}catch (SQLException e) {
			code = -2;
			logger.info(e.toString());
		}catch (Exception e) {
			//异常的处理
			code = -3;
			logger.info(e.toString());
		}
		logger.debug("GetUsdPushStatus END: " );
		
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

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	
}
