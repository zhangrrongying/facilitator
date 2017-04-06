package com.ec.facilitator.site.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.system.SysUserBiz;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;
import com.ec.facilitator.site.ThymeleafHelper;

/**
 * 
 * @author 张荣英
 * @date 2016年6月28日 上午10:09:31
 */
@RestController
@RequestMapping("/system")
public class SystemController {
	
	@Resource
	SysUserBiz sysUserBiz;

	@Autowired
	ThymeleafHelper thymeleafHelper;
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userListPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		List<String> codes;
		if(AuthManager.getCurrentAuthData().getPermissionCode() != null){
			codes = AuthManager.getCurrentAuthData().getPermissionCode();
		}else{
			codes = new ArrayList<String>();
		}
		variables.put("hasAddBtn", codes.contains("P4"));
		variables.put("hasEditBtn", codes.contains("P5"));
		variables.put("hasPwdeBtn", codes.contains("P6"));
		return thymeleafHelper.processHtml(variables,"pages/user_list", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/role/list" , method = RequestMethod.GET)
	public String roleListPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext){
		HashMap<String, Object> variables = new HashMap<String, Object>();
		List<String> codes;
		if(AuthManager.getCurrentAuthData().getPermissionCode() != null){
			codes = AuthManager.getCurrentAuthData().getPermissionCode();
		}else{
			codes = new ArrayList<String>();
		}
		variables.put("hasAddBtn", codes.contains("P7"));
		variables.put("hasEditBtn", codes.contains("P8"));
		variables.put("hasPowerBtn", codes.contains("P9"));
		return thymeleafHelper.processHtml(variables,"pages/role_list", request, response, servletContext);
	}

	
	@SSLTag
	@RequestMapping(value = "/pwd", method = RequestMethod.GET)
	public String updatePwd(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, @RequestParam("id") int id) throws Exception {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		 
		variables.put("id", id);
		return thymeleafHelper.processHtml(variables,"pwd", request, response, servletContext);
	}
}
