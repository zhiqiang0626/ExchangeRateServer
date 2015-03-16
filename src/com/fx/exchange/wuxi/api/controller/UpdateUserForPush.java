package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.PushUserInfo;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserForPush extends ActionSupport {

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
		//push发送的状态 0：未发送 1 已经发送
		private String push_status;

		@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			logger.debug("UpdateUserForPush START: " );
			//数据库的初期化
			SqlMapClient sqlMap = DBOperation.getSqlMapInstance();

			try {
				//对象的初期化
				PushUserInfo userInfo = new  PushUserInfo();
				userInfo.setPush_status(push_status);
				userInfo.setRegistration_ID(registrationID);
				//数据的插入处理
				sqlMap.update("PushUserUpdateForPush", userInfo);
				messages = "更新用户数据成功！";
				
			}  catch (SQLException e) {
				e.printStackTrace();
				code = -1;
				messages = "db服务器异常";
				logger.info(e.toString() );
			} catch (Exception e) {
				e.printStackTrace();
				code = -1;
				messages = "服务器异常";
				logger.info(e.toString() );
			}
			
			logger.debug("UpdateUserForPush END: " );
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
		public String getRegistrationID() {
			return registrationID;
		}
		public void setRegistrationID(String registrationID) {
			this.registrationID = registrationID;
		}

		public String getPush_status() {
			return push_status;
		}
		public void setPush_status(String push_status) {
			this.push_status = push_status;
		}
}
