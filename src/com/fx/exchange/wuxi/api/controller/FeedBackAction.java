package com.fx.exchange.wuxi.api.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fx.exchange.wuxi.api.db.DBOperation;
import com.fx.exchange.wuxi.api.model.FeedBackInfo;
import com.fx.exchange.wuxi.api.model.PushUserInfo;
import com.fx.exchange.wuxi.common.util.RequestMethodUtil;
import com.fx.exchange.wuxi.common.util.StringConst;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

public class FeedBackAction extends ActionSupport {

	//serialVersionUID
		private static final long serialVersionUID = 1L;
		//Logger的初期设定
		private static Logger logger = Logger.getLogger("ScriptMaint");

		//返回json结果状态值 0：正常  -1：异常
		private int code = 0;

		//用户ID
		private String registrationID;
		//反馈信息的内容
		private String feedback_title;
		//反馈信息的内容
		private String feedback_content;
		
		@Override
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			logger.debug("FeedBackAction START: " );
			
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
					|| feedback_content == null ||  "".equals(feedback_content)
					){
					code = -3;
					logger.info(StringConst.ERROR_MSG_05);
					return SUCCESS;
				}else{
					//对象的初期化
					FeedBackInfo feedBackInfo = InitParam();
					
					//数据的插入处理
					sqlMap.insert("FeedBackInsert", feedBackInfo);
					logger.info(StringConst.ERROR_MSG_03);
					
				}
				
			}  catch (SQLException e) {
				e.printStackTrace();
				code = -1;
				logger.info(e.toString() );
			} catch (Exception e) {
				e.printStackTrace();
				code = -2;
				logger.info(e.toString() );
			}
			
			logger.debug("FeedBackAction END: " );
			return SUCCESS;
		}
		
		//初始化参数
		public FeedBackInfo InitParam( ){
			FeedBackInfo feedBackInfo = new  FeedBackInfo();
			feedBackInfo.setRegistration_ID(registrationID);
			feedBackInfo.setFeedback_content(feedback_content);
			if(feedback_title == null){
				feedBackInfo.setFeedback_title("");
			}
			return feedBackInfo;
		}
		
		
		//参数的构造
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}

		public String getRegistrationID() {
			return registrationID;
		}
		public void setRegistrationID(String registrationID) {
			this.registrationID = registrationID;
		}
		
		public String getFeedback_title() {
			return feedback_title;
		}

		public void setFeedback_title(String feedback_title) {
			this.feedback_title = feedback_title;
		}

		public String getFeedback_content() {
			return feedback_content;
		}

		public void setFeedback_content(String feedback_content) {
			this.feedback_content = feedback_content;
		}
}
