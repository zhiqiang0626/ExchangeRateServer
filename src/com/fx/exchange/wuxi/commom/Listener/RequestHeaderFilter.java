package com.fx.exchange.wuxi.commom.Listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.fx.exchange.wuxi.common.util.StringConst;


/**
 * RequestHeaderFilter
 * 
 * @author lzq
 * 
 */
public class RequestHeaderFilter implements Filter {

	//Logger的初期设定
	private static Logger logger = Logger.getLogger("ScriptMaint");
	
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

    	logger.debug("doFilter START: " );
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        //头部数据验证数据的取得
        String authTokenRequest = request.getHeader(StringConst.FX_EX_AUTH_TOKEN);
        //版本号额取得
        String nowVersion = request.getHeader(StringConst.AUTH_VERSION);
        logger.info("当前的请求app版本号："+nowVersion);
        if ( StringConst.AUTH_TOKEN_SERVER.equals(authTokenRequest)) {
            chain.doFilter(request, response);
        } else {
        	logger.info("非法请求！");
        	response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(201);
            try {
                JSONObject tempJsonObj = new JSONObject();
                tempJsonObj.put("code", -9);
                String returnJsonStr = tempJsonObj.toString();
                
                PrintWriter pw = response.getWriter();
                pw.write(returnJsonStr);
                pw.flush();
                pw.close();
            } catch (IOException | JSONException e) {
               // logger.error(e.getMessage());
            }
        }
        logger.debug("doFilter END: " );
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
