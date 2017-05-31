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
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.fac.SupplierBiz;
import com.ec.facilitator.base.dal.fac.SupplierDao;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;
import com.ec.facilitator.site.ThymeleafHelper;

@RestController
@RequestMapping("/pages")
public class SupplierController {
	
	@Resource
	SupplierBiz supplierBiz;
	
	@Autowired
	SupplierDao supplierDao;

	@Autowired
	ThymeleafHelper thymeleafHelper;
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/supplier/list", method = RequestMethod.GET)
	public String userListPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		List<String> codes;
		if(AuthManager.getCurrentAuthData().getPermissionCode() != null){
			codes = AuthManager.getCurrentAuthData().getPermissionCode();
		}else{
			codes = new ArrayList<String>();
		}
		variables.put("hasAddBtn", codes.contains("P12"));
		variables.put("hasEditBtn", codes.contains("P13"));
		return thymeleafHelper.processHtml(variables,"pages/supplier_list", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/supplier/obj", method = RequestMethod.GET)
	public String userPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		int id = Integer.valueOf(request.getParameter("id"));		 
		FacSupplierModel obj = null;
		if(id > 0)
			obj =supplierBiz.getModelById(id);
		variables.put("obj", obj);
		return thymeleafHelper.processHtml(variables,"pages/supplier", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/project/list", method = RequestMethod.GET)
	public String projectListPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		List<String> codes;
		if(AuthManager.getCurrentAuthData().getPermissionCode() != null){
			codes = AuthManager.getCurrentAuthData().getPermissionCode();
		}else{
			codes = new ArrayList<String>();
		}
		variables.put("hasAddBtn", codes.contains("P15"));
		variables.put("hasEditBtn", codes.contains("P16"));
		return thymeleafHelper.processHtml(variables,"pages/project_list", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/project/obj", method = RequestMethod.GET)
	public String projectPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		int id = Integer.valueOf(request.getParameter("id"));		 
		FacProjectModel obj = null;
		if(id > 0)
			obj =supplierBiz.getProjectById(id);
		variables.put("obj", obj);
		return thymeleafHelper.processHtml(variables,"pages/project", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/project/bid/list", method = RequestMethod.GET)
	public String projectBidListPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		List<String> codes;
		if(AuthManager.getCurrentAuthData().getPermissionCode() != null){
			codes = AuthManager.getCurrentAuthData().getPermissionCode();
		}else{
			codes = new ArrayList<String>();
		}
		variables.put("hasAddBtn", codes.contains("P19"));
		variables.put("hasEditBtn", codes.contains("P20"));
		return thymeleafHelper.processHtml(variables,"pages/project_bid_list", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/project/bid", method = RequestMethod.GET)
	public String projectBidPage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		return thymeleafHelper.processHtml("pages/project_bid", request, response, servletContext);
	} 
	
	@SSLTag
	@AuthTag
	@RequestMapping(value = "/project/score", method = RequestMethod.GET)
	public String projectScorePage(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		int id = Integer.valueOf(request.getParameter("id"));
		FacProjectModel project = supplierDao.getProjectById(id);
		variables.put("project", project);
		return thymeleafHelper.processHtml(variables,"pages/project_score", request, response, servletContext);
	} 
}
