package com.ec.facilitator.site.reset;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.system.SysRoleBiz;
import com.ec.facilitator.base.bal.system.SysUserBiz;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.model.system.loginLogModel;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;

@RestController
@RequestMapping("/system/user")
public class SysUserController {	
	
		@Resource
		SysRoleBiz sysRoleBiz;
	
		@Resource
		SysUserBiz sysUserBiz;
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/userList.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<SysUserModel> userList(@RequestBody SysUserModel requestModel) throws Exception {
			return sysUserBiz.getUserList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value ="/roleList.json",method = RequestMethod.POST)
		public JQGridResponseModel<SysRoleModel> roleList(@RequestBody SysRoleModel role){
			return sysRoleBiz.roleList(role);
			
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/powerTree.json",method = RequestMethod.GET)
		public List<SysAuthFuncModel> powerTree(){
			return sysRoleBiz.getPower();
			
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/rolePowerTree.json/{id}",method = RequestMethod.GET)
		public List<SysAuthFuncModel> rolePowerTree(@PathVariable("id") int id){
			return sysRoleBiz.RolePower(id);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/createRole.json",method = RequestMethod.POST)
		public BooleanResultModel createRole(@RequestBody SysRoleModel role){
			BooleanResultModel br = new BooleanResultModel();
			br.setResult(sysRoleBiz.createRole(role));
			return br;
			
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/updateRole.json", method = RequestMethod.POST)
		public BooleanResultModel updateRole(@RequestBody SysRoleModel role){
			BooleanResultModel br = new BooleanResultModel();
			br.setResult(sysRoleBiz.updateRole(role));
			return br;
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/editPowerTree.json/{id}",method = RequestMethod.POST)
		public BooleanResultModel editPowerTree(@PathVariable("id") int id,@RequestBody int[] model) throws Exception{
			BooleanResultModel br = new BooleanResultModel();
			br.setResult(sysRoleBiz.editRolePower(id, model));
			return br;
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
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/login/log.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<loginLogModel> userloginList(@RequestBody loginLogModel requestModel) throws Exception {
			return sysUserBiz.getUserLoginLogList(requestModel);
		}
}
