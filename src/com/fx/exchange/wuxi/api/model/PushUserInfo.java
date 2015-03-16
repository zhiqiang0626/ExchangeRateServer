package com.fx.exchange.wuxi.api.model;

import java.util.Date;

public class PushUserInfo {
	private String registration_ID;
	private String push_flag;
	private String push_status;
	private Date push_time;
	private String push_device_type;
	private String user_time_type;
	private String exchange_type;
	private String exchange_from_currency;
	private String exchange_to_currency;
	private String currency_base_value;
	private String currency_target_value;
	private String del_flag;
	public String getRegistration_ID() {
		return registration_ID;
	}
	public void setRegistration_ID(String registration_ID) {
		this.registration_ID = registration_ID;
	}
	public String getPush_flag() {
		return push_flag;
	}
	public void setPush_flag(String push_flag) {
		this.push_flag = push_flag;
	}
	public String getPush_status() {
		return push_status;
	}
	public void setPush_status(String push_status) {
		this.push_status = push_status;
	}
	public Date getPush_time() {
		return push_time;
	}
	public void setPush_time(Date push_time) {
		this.push_time = push_time;
	}
	public String getUser_time_type() {
		return user_time_type;
	}
	public void setUser_time_type(String user_time_type) {
		this.user_time_type = user_time_type;
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
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getPush_device_type() {
		return push_device_type;
	}
	public void setPush_device_type(String push_device_type) {
		this.push_device_type = push_device_type;
	}
	
	
}
