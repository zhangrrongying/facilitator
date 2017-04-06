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
		@RequestMapping(value = "/createInfo.json" , method = RequestMethod.GET)
		public List<Map<String,Object>> createInfo(HttpRequest req){
			int userId = AuthManager.getCurrentAuthData().getId();
			return sysUserBiz.findCreateInfo(userId);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/createSysUser.json", method = RequestMethod.POST)
		public BooleanResultModel createSysUser(@RequestBody SysUserModel user) throws Exception{
			BooleanResultModel br = new BooleanResultModel();
			SysUserModel hasUser =sysUserDao.getSysUserByUserName(user.getUserName());
			if(hasUser!=null){
				br.setMsg("用户名已被占用");
				br.setResult(false);
				return br;
			}
			br.setResult(sysUserBiz.addUser(user));
			return br;
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/updateInfo.json", method = RequestMethod.POST)
		public BooleanResultModel updateInfo(@RequestBody SysUserModel user){
			BooleanResultModel br = new BooleanResultModel();
			try {
				 br = sysUserBiz.updUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				br.setResult(false);
			}
			return br;
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
