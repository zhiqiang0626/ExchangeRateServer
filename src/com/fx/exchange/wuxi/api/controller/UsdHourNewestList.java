package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.UsdHourInfo;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class UsdHourNewestList extends ActionSupport {

	//serialVersionUID
		private static final long serialVersionUID = 1L;
		//Logger的初期设定
		private static Logger logger = Logger.getLogger("ScriptMaint");

		//返回json结果状态值 0：正常  -1：异常
		private int code = 0;
		//返回的message
		private String messages;
		//
		//用户ID
		private String registrationID;
		//最新一条汇率的信息
		private List<UsdHourInfo> usdHourInfoList;
		
		@SuppressWarnings("unchecked")
		@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			logger.debug("UsdHourNewestList START: " );
			//数据库的初期化
			SqlMapClient sqlMap = DBOperation.getSqlMapInstance();
			try {

				//usdHourInfoList =sqlMap.queryForList("GetUsdHour24List",null);
				//用户货币的选择名称
				String currencyName =(String) sqlMap.queryForObject("GetPushUserRecord",registrationID);
				logger.info("currencyName:"+currencyName);
				HashMap<String, String> input = new HashMap<String, String>();
				input.put("column", currencyName);
				//对象的取得
				usdHourInfoList =sqlMap.queryForList("GetUsdHour24Record",input);
				logger.info("currencyName:"+usdHourInfoList);
				
				if(usdHourInfoList == null || usdHourInfoList.size()==0 ){
	     			code = 1;
	        		messages = "没有找到对应的数据。";
	     		}else{
	     			code = 0;
	            	messages = "数据查询处理完了。";
	     		}
	        		
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = -1;
				messages = "SQL取得异常。";
				logger.info(e.toString() );
			}
			
			logger.debug("UsdHourNewestList END: " );
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

		public List<UsdHourInfo> getUsdHourInfoList() {
			return usdHourInfoList;
		}

		public void setUsdHourInfoList(List<UsdHourInfo> usdHourInfoList) {
			this.usdHourInfoList = usdHourInfoList;
		}

		public String getRegistrationID() {
			return registrationID;
		}

		public void setRegistrationID(String registrationID) {
			this.registrationID = registrationID;
		}
		
		
}
