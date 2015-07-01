package com.fx.exchange.wuxi.api.model;

public class FeedBackInfo {
	private String registration_ID;
	private String feedback_title;
	private String feedback_content;
	private String reply_title;
	private String reply_content;
	public String getRegistration_ID() {
		return registration_ID;
	}
	public void setRegistration_ID(String registration_ID) {
		this.registration_ID = registration_ID;
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
	public String getReply_title() {
		return reply_title;
	}
	public void setReply_title(String reply_title) {
		this.reply_title = reply_title;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

}
