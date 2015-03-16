package com.fx.exchange.wuxi.commom.Listener;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fx.exchange.wuxi.common.util.PropertiesUtil;

public class WebConfigListener implements ServletContextListener {

	//@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		PropertiesUtil.properties = new Properties();

		try{
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream fis = new FileInputStream(path + "/config.properties");
			PropertiesUtil.properties.load(fis);
			
		}catch (Exception e) {
			e.printStackTrace();
			}
	}

}
