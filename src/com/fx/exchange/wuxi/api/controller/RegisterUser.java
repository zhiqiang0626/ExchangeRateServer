package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.PushUserInfo;
import com.fx.exchange.wuxi.common.util.RequestMethodUtil;
import com.fx.exchange.wuxi.common.util.StringConst;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterUser extends ActionSupport {

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
		//用户注册时的设备种别 0：ios 1：android
		private String push_device_type;
		//货币转换的类型 0：上下百分幅度 1：目标值
		private String exchange_type;
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
		//手机端末的使用语言
		private String user_language_code;
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
				if (registrationID == null ||  "".equals(registrationID)
					|| push_device_type == null ||  "".equals(push_device_type)
					||user_language_code == null || "".equals(user_language_code)
					){
					code = -3;
					logger.info(StringConst.ERROR_MSG_05);
					return SUCCESS;
				}
				
				//用户是否已经注册过
				int userCount = (int)sqlMap.queryForObject("GetUsdPushInfoCount",registrationID);
				System.out.println(userCount);
				if(userCount > 0){
					logger.info(StringConst.ERROR_MSG_06);
					return SUCCESS;
				}

				//对象的初期化
				PushUserInfo userInfo = new  PushUserInfo();
				userInfo.setRegistration_ID(registrationID);
				userInfo.setPush_device_type(push_device_type);
				userInfo.setUser_language_code(user_language_code);
				//初期化一些参数值
				InitParam(userInfo);
				//数据的插入处理
				sqlMap.insert("PushUserInsert", userInfo);
				logger.info(StringConst.ERROR_MSG_03);
				
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
		
		//初始化参数
		public void InitParam(PushUserInfo userInfo ){
			//用户是否允许进行push 0：允许 1：不允许
			userInfo.setPush_flag("0");
			//需要转换的元货币 例:JPY
			userInfo.setExchange_from_currency("JPY");
			//转换后的先货币 例如：USD
			userInfo.setExchange_to_currency("JPY");
			//当前货币的基准值
			userInfo.setCurrency_base_value("0");
			//目标货币值，一旦达到，将进行推送消息
			userInfo.setCurrency_target_value("0");
			//目标货币值2
			userInfo.setCurrency_target2_value("0");
			//目标货币值3
			userInfo.setCurrency_target3_value("0");
			//目标货币值4
			userInfo.setCurrency_target4_value("0");
		}
		
		
		//参数的构造
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
		public String getExchange_type() {
			return exchange_type;
		}
		public void setExchange_type(String exchange_type) {
			this.exchange_type = exchange_type;
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
		public String getPush_device_type() {
			return push_device_type;
		}
		public void setPush_device_type(String push_device_type) {
			this.push_device_type = push_device_type;
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

		public String getUser_language_code() {
			return user_language_code;
		}

		public void setUser_language_code(String user_language_code) {
			this.user_language_code = user_language_code;
		}
		
		
}
