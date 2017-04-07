package com.ec.facilitator.site.reset;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.system.SysUserBiz;
import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;

@RestController
@RequestMapping("/system/user")
public class SysUserController {	
		@Resource
		SysUserBiz sysUserBiz;
		@Autowired
		SysUserDao sysUserDao;
		
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/userList.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<SysUserModel> userList(@RequestBody SysUserModel requestModel) throws Exception {
			return sysUserBiz.getUserList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value ="/roleList.json",method = RequestMethod.GET)
		public List<SysRoleModel> roleList(){
			return sysUserBiz.getRoleList();
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/save.json", method = RequestMethod.POST)
		public BooleanResultModel createSysUser(@RequestBody SysUserModel user) throws Exception{
			return sysUserBiz.saveUser(user);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/updatePwd.json",method = RequestMethod.POST)
		public @ResponseBody BooleanResultModel updatePwd(@RequestBody SysUserModel user) throws Exception{
			BooleanResultModel br = new BooleanResultModel();
			br.setResult(sysUserBiz.updUserPwd(user)>0?true:false);
			return br;
		}

}
