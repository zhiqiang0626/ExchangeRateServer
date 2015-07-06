package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.PushUserInfo;
import com.fx.exchange.wuxi.common.util.RequestMethodUtil;
import com.fx.exchange.wuxi.common.util.StringConst;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUser extends ActionSupport {

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
		//用户是否允许进行push 0：允许 1：不允许
		private String push_flag;
		//需要转换的元货币 例:JPY
		private String exchange_from_currency;
		//转换后的先货币 例如：USD
		private String exchange_to_currency;
		//当前货币的基准值
		private String currency_base_value;
		//目标货币值，一旦达到，将进行推送消息
		private String currency_target_value;
		//目标货币值2
		private String currency_target2_value;
		//目标货币值3
		private String currency_target3_value;
		//目标货币值4
		private String currency_target4_value;
		@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			logger.debug("RegisterUser START: " );
			
			//请求类型的判定处理
			if(!RequestMethodUtil.isPostRequest()){
				code = -2;
				logger.info(StringConst.ERROR_MSG_07);
				return SUCCESS;
			}
			
			//数据库的初期化
			SqlMapClient sqlMap = DBOperation.getSqlMapInstance();

			try {
				//对象的初期化
				PushUserInfo userInfo = new  PushUserInfo();
				userInfo.setPush_status("0");//未发送push
				userInfo.setRegistration_ID(registrationID);
				userInfo.setPush_flag(push_flag);
				userInfo.setExchange_from_currency(exchange_from_currency);
				userInfo.setExchange_to_currency(exchange_to_currency);
				userInfo.setCurrency_base_value(currency_base_value);
				userInfo.setCurrency_target_value(currency_target_value);
				userInfo.setCurrency_target2_value(currency_target2_value);
				userInfo.setCurrency_target3_value(currency_target3_value);
				userInfo.setCurrency_target4_value(currency_target4_value);
				//数据的更新处理
				sqlMap.update("PushUserUpdate", userInfo);
				logger.info(StringConst.ERROR_MSG_04);
				
			}  catch (SQLException e) {
				e.printStackTrace();
				code = -1;
				logger.info(e.toString() );
			} catch (Exception e) {
				e.printStackTrace();
				code = -2;
				logger.info(e.toString() );
			}
			
			logger.debug("RegisterUser END: " );
			
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
		public String getPush_flag() {
			return push_flag;
		}
		public void setPush_flag(String push_flag) {
			this.push_flag = push_flag;
		}

		public String getExchange_from_currency() {
			return exchange_from_currency;
		}
		public void setExchange_from_currency(String exchange_from_currency) {
			this.exchange_from_currency = exchange_from_currency;
		}
		public String getExchange_to_currency() {
			return exchange_to_currency;
		}
		public void setExchange_to_currency(String exchange_to_currency) {
			this.exchange_to_currency = exchange_to_currency;
		}
		public String getCurrency_base_value() {
			return currency_base_value;
		}
		public void setCurrency_base_value(String currency_base_value) {
			this.currency_base_value = currency_base_value;
		}
		public String getCurrency_target_value() {
			return currency_target_value;
		}
		public void setCurrency_target_value(String currency_target_value) {
			this.currency_target_value = currency_target_value;
		}
		public String getCurrency_target2_value() {
			return currency_target2_value;
		}
		public void setCurrency_target2_value(String currency_target2_value) {
			this.currency_target2_value = currency_target2_value;
		}
		public String getCurrency_target3_value() {
			return currency_target3_value;
		}
		public void setCurrency_target3_value(String currency_target3_value) {
			this.currency_target3_value = currency_target3_value;
		}
		public String getCurrency_target4_value() {
			return currency_target4_value;
		}
		public void setCurrency_target4_value(String currency_target4_value) {
			this.currency_target4_value = currency_target4_value;
		}
		
}
