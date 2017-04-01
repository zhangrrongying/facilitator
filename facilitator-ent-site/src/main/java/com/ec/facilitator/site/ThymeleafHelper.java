package com.ec.facilitator.site;

import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Thymeleaf的html转换类
 * @author ryan
 * @date 2016年6月29日 下午4:57:24
 */
@Component
public class ThymeleafHelper {
	
	@Autowired
	TemplateEngine templateEngine;
	
	public String processHtml(String fileName, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		StringWriter stringWriter = new StringWriter();
		templateEngine.process(fileName, ctx, stringWriter);
		return stringWriter.toString();
	}
	
	public String processHtml(HashMap<String, Object> variables, String fileName, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariables(variables);
		StringWriter stringWriter = new StringWriter();
		templateEngine.process(fileName, ctx, stringWriter);
		return stringWriter.toString();
	}
	
}
