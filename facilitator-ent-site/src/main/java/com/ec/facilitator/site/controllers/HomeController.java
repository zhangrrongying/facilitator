package com.ec.facilitator.site.controllers;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ec.facilitator.base.bal.common.BizErrorException;
import com.ec.facilitator.base.bal.system.SysUserBiz;
import com.ec.facilitator.base.model.common.AuthMenuModel;
import com.ec.facilitator.base.util.AuthData;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;
import com.ec.facilitator.site.ThymeleafHelper;

/**
 * 首页登陆controller
 * @author 张荣英
 * @date 2016年6月23日 下午4:41:54
 */
@Controller
public class HomeController {
	
	@Autowired
	SysUserBiz systemUserBiz;
	
	@Autowired
	ThymeleafHelper thymeleafHelper;
	
	@Autowired
	ApplicationContext springContext;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String site() {
		return "login";
	}
	
	@RequestMapping(value = "/forbidden", method = RequestMethod.GET)
	public String forbidden() {
		return "forbidden";
	}

	@SSLTag
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestParam("loginName") String loginName,@RequestParam("password") String password,Model model) throws Throwable {
		model.addAttribute("loginName", loginName);
		model.addAttribute("password", password);
		try {
			AuthData authData = systemUserBiz.login(StringUtils.trim(loginName) , password);
			if (authData != null) {
				AuthManager.setAuthToken(authData);
			}
		} catch(BizErrorException bizError) {
			model.addAttribute("result",springContext.getMessage(bizError.getReasonKey() + "_" + bizError.getErrorCode(), null, Locale.getDefault()));
		}
	}
	
	@AuthTag
	@SSLTag
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(Model model) {
		AuthData authData = AuthManager.getCurrentAuthData();

		AuthMenuModel rootMenu = systemUserBiz.getMenusByPCodes(authData.getId());
		model.addAttribute("menus", rootMenu);
		model.addAttribute("userName", authData.getUserName());

		model.addAttribute("authData", authData);
		if (authData != null) {
			model.addAttribute("userId", authData.getId());
		}
		return "index";
	}
	
	@SSLTag
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {
		AuthManager.logout();
	}
	
	@AuthTag
	@SSLTag
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getFarmCertificateList(Model model) throws Exception {
		return "pages/home_page";
	}
	
	@SSLTag
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String helpPage(Model model) throws Exception {
		return "help";
	}
}
