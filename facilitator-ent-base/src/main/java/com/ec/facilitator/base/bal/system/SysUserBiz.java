package com.ec.facilitator.base.bal.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.guzz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ec.facilitator.base.bal.common.BizErrorException;
import com.ec.facilitator.base.bal.common.BizErrorItemModel;
import com.ec.facilitator.base.bal.common.BizErrorModel;
import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.mail.ContentFormat;
import com.ec.facilitator.base.mail.IMailSendable;
import com.ec.facilitator.base.model.common.AuthMenuModel;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysRoleUserModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.model.system.loginLogModel;
import com.ec.facilitator.base.util.AuthData;
import com.ec.facilitator.base.util.BizHelper;
import com.ec.facilitator.base.util.WebConfig;

/**
 * 系统用户业务处理
 * @author 张荣英
 * @date 2017年4月5日 下午5:23:17
 */
@Component
public class SysUserBiz {
	
	@Autowired
	SysUserDao sysUserDao;
	
	@Autowired
	private ApplicationContext springContext;
	
	@Autowired
	private IMailSendable mailSender;
	
	@Autowired
	private WebConfig webConfig;
	
	/**
	 * 后台登陆授权
	 * @param loginName
	 * @param password
	 * @return
	 * @throws Exception
	 * @return AuthData
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:23:54
	 */
	public AuthData login(String loginName, String password) throws Exception {
		if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
			throw new BizErrorException(BizErrorModel.LOGIN_ERROR, BizErrorItemModel.LOGIN_ERROR_100002, null);
		}
		SysUserModel sysUserModel = sysUserDao.getSysUserByUserName(loginName);
		String encodedPwd = BizHelper.encodeStr(BizHelper.ENCODING_MD5, password);
		//用户是否存在
		if (sysUserModel == null) {
			throw new BizErrorException(BizErrorModel.LOGIN_ERROR, BizErrorItemModel.LOGIN_ERROR_100001, null);
		}
		//用户密码不正确
		if (encodedPwd.equals(sysUserModel.getPassword()) == false) {
			throw new BizErrorException(BizErrorModel.LOGIN_ERROR, BizErrorItemModel.LOGIN_ERROR_100004, null);
		}
		
		//build授权信息
		AuthData authData = new AuthData();
		authData.setId(sysUserModel.getId());
		authData.setUserName(sysUserModel.getName());
		List<SysAuthFuncModel> funcs = sysUserDao.getFuncsByUserId(sysUserModel.getId());
		if(funcs != null && funcs.size() > 0){
			List<String> permissionCodes = new ArrayList<String>();
			for(SysAuthFuncModel func : funcs){
				permissionCodes.add(func.getKey());
			}
			authData.setPermissionCode(permissionCodes);
		}
		sysUserModel.setLoginNum(sysUserModel.getLoginNum()+1);
		sysUserDao.update(sysUserModel);
		loginLogModel log = new loginLogModel();
		log.setUserId(sysUserModel.getId());
		log.setLoginTime(new Date());
		sysUserDao.insert(log);
		return authData;
	}
	
	/**
	 * 根据功能点Key值来获取导航菜单树
	 * @param userId
	 * @return
	 * @return AuthMenuModel
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:24:46
	 */
	public AuthMenuModel getMenusByPCodes(int userId) {
		List<SysAuthFuncModel> sysMenus = sysUserDao.getMenusByUserId(userId);
		AuthMenuModel rootMenu = buildAuthMenus(sysMenus);
		return rootMenu;
	}

	/**
	 * 构建授权菜单树
	 * @param sysMenus
	 * @return
	 * @return AuthMenuModel
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:25:07
	 */
	private AuthMenuModel buildAuthMenus(List<SysAuthFuncModel> sysMenus) {
		AuthMenuModel rootMenu = new AuthMenuModel();
		if (sysMenus != null && sysMenus.size() > 0) {
			loadMenuTree(rootMenu, sysMenus);	
		}
		return rootMenu;
	}

	/**
	 * 将平板的系统菜单列表转换撑UI需要的树形列表
	 * @param rootMenu
	 * @param sysMenus
	 * @return void
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:25:25
	 */
	private void loadMenuTree(AuthMenuModel rootMenu, List<SysAuthFuncModel> sysMenus) {
		rootMenu.setChildMenu(new ArrayList<AuthMenuModel>());
		for(int i = sysMenus.size() - 1; i >= 0; i--) {
			SysAuthFuncModel sysMenu = sysMenus.get(i);
			if (rootMenu.getId() == sysMenu.getParentKeyId()) {
				AuthMenuModel authMenu = new AuthMenuModel();
				authMenu.setFuncKey(sysMenu.getUrl());
				authMenu.setId(sysMenu.getId());
				authMenu.setParentId(sysMenu.getParentKeyId());
				authMenu.setName(sysMenu.getName());
				authMenu.setIcon(sysMenu.getIcon());
				rootMenu.getChildMenu().add(authMenu);
				sysMenus.remove(i);
			}
		}
		
		if (sysMenus.size() > 0) {
			for(AuthMenuModel authMenu : rootMenu.getChildMenu()) {
				loadMenuTree(authMenu, sysMenus);
			}
		}
	}
	
	/**
	 * 获取用户列表
	 * @param requestModel
	 * @return
	 * @throws Exception
	 * @return JQGridResponseModel<SysUserModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:25:39
	 */
	public JQGridResponseModel<SysUserModel> getUserList(SysUserModel requestModel) throws Exception{
		JQGridResponseModel<SysUserModel> jQModel = sysUserDao.getUserList(requestModel);
		return jQModel;
	}
	
	/*
	 * 获取角色列表
	 * @return
	 * @return List<SysRoleModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:29:47
	 */
	public List<SysRoleModel> getRoleList(){
		return sysUserDao.getRoleList();
	}
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @throws Exception 
	 * @date 2017年4月5日 下午5:32:30
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public BooleanResultModel saveUser(SysUserModel user) throws Exception{
		BooleanResultModel br = new BooleanResultModel();
		SysUserModel hasUser =sysUserDao.getSysUserByUserName(user.getUserName());
		if(hasUser!=null && hasUser.getId() != user.getId()){
			br.setMsg("用户名已被占用");
			br.setResult(false);
			return br;
		}
		if(user.getId()  > 0){
			sysUserDao.updUser(user);
		}else{
			user.setPassword(BizHelper.encodeStr(BizHelper.ENCODING_MD5, user.getPassword().trim()));
			if(user.getStatus() == null){
				user.setStatus((short) 0);	
			}
			user.setLoginNum(0);
			sysUserDao.insert(user);
			SysRoleUserModel role = new SysRoleUserModel();
			role.setUserId(user.getId());
			role.setRoleId(user.getRoleId());
			sysUserDao.insert(role);
			//发送邮件
			Map<String,Object> velocityModel = new HashMap<>();
//			velocityModel.put("createUser", user.getName());
			velocityModel.put("createUser", "张荣英");
			velocityModel.put("href", webConfig.getSecureHost()+"/index");
			List<String> mails = new ArrayList<>();
			mails.add(user.getEmail());
			mailSender.sendMailAsync(mails, "注册成功",velocityModel, "createUserNotice.vm", ContentFormat.Html);
		}
		br.setResult(true);
		return br;
	}
	
	public SysUserModel getModelById(int id){
		return sysUserDao.getSysUserById(id);
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public int updUserPwd(SysUserModel user) throws Exception{
		if(user.getId() != null && StringUtils.hasText(user.getPassword())){
			user.setPassword(BizHelper.encodeStr(BizHelper.ENCODING_MD5, user.getPassword().trim()));
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("password", user.getPassword());
			map.put("id", user.getId());
			return sysUserDao.executeUpdate("updatePwd", map);
		}
		return 0;
	}
	
	/**
	 * 查询登录信息
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<loginLogModel>
	 * @author 张荣英
	 * @date 2017年5月17日 下午9:55:45
	 */
	public JQGridResponseModel<loginLogModel> getUserLoginLogList(loginLogModel requestModel) throws Exception{
		JQGridResponseModel<loginLogModel> jQModel = sysUserDao.getUserLoginLogList(requestModel);
		return jQModel;
	}
	
	/**
	 * 根据用户ID查询角色
	 * @param userId
	 * @return
	 * @return SysRoleModel
	 * @author 张荣英
	 * @date 2017年5月18日 下午8:52:18
	 */
	public SysRoleModel getRoleByUserId(int userId){
		return sysUserDao.getRoleByUserId(userId);
	}
	
	public List<FacProjectModel> getScoreProjectList(int status){
		return sysUserDao.getScoreProjectList(status);
	}
	
	public List<FacProjectModel> getBidProjectList(int userId){
		return sysUserDao.getBidProjectList(userId);
	}
}
