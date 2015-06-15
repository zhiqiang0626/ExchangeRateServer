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

import org.json.JSONException;
import org.json.JSONObject;



/**
 * RequestHeaderFilter
 * 
 * @author lzq
 * 
 */
public class RequestHeaderFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        // init the request data
        String AuthTokenRequest = request.getHeader("Fx-Ex-Auth-Token");
        String AuthTokenServer = "11223344";
        if (AuthTokenServer.equals(AuthTokenRequest)) {
            chain.doFilter(request, response);
        } else {
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
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
