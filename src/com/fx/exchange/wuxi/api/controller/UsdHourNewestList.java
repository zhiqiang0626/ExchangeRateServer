package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.UsdHourInfo;
import com.fx.exchange.wuxi.common.util.StringConst;
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
		//货币转换先
		private String exchange_from_currency;
		//货币转换元
		private String exchange_to_currency;
		//最新一条汇率的信息
		private List<UsdHourInfo> usdHourInfoList;
		//最新数据的时间
		private String latestTime;
		
		@SuppressWarnings("unchecked")
		@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			logger.debug("UsdHourNewestList START: " );
			//数据库的初期化
			SqlMapClient sqlMap = DBOperation.getSqlMapInstance();
			try {
				//用户货币的选择名称
				String currencyName = exchange_from_currency + "," + exchange_to_currency;
				logger.info("currencyName:"+currencyName);
				HashMap<String, String> input = new HashMap<String, String>();
				input.put("column", currencyName);
				//对象的取得
				usdHourInfoList =sqlMap.queryForList("GetUsdHour24Record",input);

				if(usdHourInfoList == null || usdHourInfoList.size()==0 ){
	     			code = -1;
	     			logger.info(StringConst.ERROR_MSG_01);
	     		}
				//取到数据库中最新一条数据的最新时间
				latestTime =(String) sqlMap.queryForObject("GetUsdHourTime");
				if(latestTime == null || latestTime == "" ){
	     			code = -1;
	     			logger.info(StringConst.ERROR_MSG_01);
	     		}
	        		
			} catch (SQLException e) {
				code = -2;
				logger.info(e.toString());
			}catch (Exception e) {
				//异常的处理
				code = -3;
				logger.info(e.toString());
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

		public String getLatestTime() {
			return latestTime;
		}

		public void setLatestTime(String latestTime) {
			this.latestTime = latestTime;
		}
		
}
