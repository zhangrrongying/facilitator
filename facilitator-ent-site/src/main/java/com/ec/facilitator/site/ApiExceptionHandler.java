package com.ec.facilitator.site;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.SLogger;

@Component
public class ApiExceptionHandler implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		//ClientAbortException属于客户端TCP超市自动断开链接，该异常不用记录。
		if (ex instanceof ClientAbortException) {
			return new ModelAndView();
		}
		
		StringBuilder sb = new StringBuilder();
		
		try{
			sb.append("\r\n" + getFullURL(request) + "\r\n\r\n");
		}catch(Throwable ex1) {
			//DO NOTHING.
		}
		
		try{
			sb.append(getReqHeader(request) + "\r\n");
		}catch(Throwable ex1) {
			//DO NOTHING.
		}
		
		if (AuthManager.getCurrentAuthData() != null) {
			sb.append(String.format("User-ID: %s\r\n", AuthManager.getCurrentAuthData().getId()));
			sb.append(String.format("User-NAME: %s\r\n", AuthManager.getCurrentAuthData().getUserName()));
		}
		
		try {
			//如果是ajax请求，就不在forward到500页面
			if(request.getRequestURI().contains(".json") == false) {
				response.sendRedirect("/forward/500");
			}
			SLogger.error(sb.toString(), ex, true);
		} catch (IOException e) {
			SLogger.error(sb.toString(), ex, true);
		}
		return new ModelAndView();
	}

	//获取完整的请求url
	private String getFullURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
	
	//获取请求头文本
	private String getReqHeader(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			sb.append(String.format("%s:%s\r\n",key, request.getHeader(key)));
		}
		return sb.toString();
	}
}

