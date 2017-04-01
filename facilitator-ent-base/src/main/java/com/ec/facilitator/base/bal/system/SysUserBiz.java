package com.ec.facilitator.base.bal.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.Terms;
import org.guzz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

import com.ec.facilitator.base.bal.common.BizErrorException;
import com.ec.facilitator.base.bal.common.BizErrorItemModel;
import com.ec.facilitator.base.bal.common.BizErrorModel;
import com.ec.facilitator.base.bal.common.CommonBiz;
import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.common.AuthMenuModel;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.common.LoginModel;
import com.ec.facilitator.base.model.common.TreeModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysOrgModel;
import com.ec.facilitator.base.model.system.SysOrgUserModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.util.AuthData;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.BizHelper;
import com.ec.facilitator.base.util.WebConfig;

/**
 * 系统用户业务处理
 * @author ryan
 * @date 2016年1月25日 下午4:18:00
 */
@Component
public class SysUserBiz {
	
	@Autowired
	SysUserDao sysUserDao;
	
	@Autowired
	private ApplicationContext springContext;
	
	@Autowired
	private WebConfig webConfig;
	
	@Autowired
	private CommonBiz commonBiz;
	
	/**
	 * 后台登陆授权
	 * @param loginName
	 * @param password
	 * @return
	 * @return AuthData
	 * @author 张荣英
	 * @throws Exception 
	 * @date 2016年6月28日 下午3:41:34
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
		return authData;
	}
	
	/**
	 * 根据功能点Key值来获取导航菜单树
	 * @param funcs
	 * @return
	 * @return AuthMenuModel
	 * @author ryan 
	 * @date 2016年1月25日 下午4:26:36
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
	 * @author ryan 
	 * @date 2016年1月25日 下午6:11:45
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
	 * @author ryan 
	 * @date 2016年1月26日 上午11:48:59
	 */
	private void loadMenuTree(AuthMenuModel rootMenu, List<SysAuthFuncModel> sysMenus) {
		rootMenu.setChildMenu(new ArrayList<AuthMenuModel>());
		for(int i = sysMenus.size() - 1; i >= 0; i--) {
			SysAuthFuncModel sysMenu = sysMenus.get(i);
			if (rootMenu.getId() == sysMenu.getParentKeyId()) {
				AuthMenuModel authMenu = new AuthMenuModel();
//				authMenu.setFuncKey(AUTH_KEY_M + sysMenu.getId() + AUTH_KEY_Q);
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
	 * @param page
	 * @param rows
	 * @return
	 * @return ListResultModel<SysUserModel>
	 * @author Tang
	 * @throws Exception 
	 * @throws BizErrorException 
	 * @date 2016年6月30日 下午12:16:03
	 */
	public JQGridResponseModel<SysUserModel> getUserList(SysUserModel requestModel) throws Exception{
		int id = AuthManager.getCurrentAuthData().getId();
		SysUserModel user = (SysUserModel)sysUserDao.findObjectByPK(SysUserModel.class, id);
		String code = "";
		if(user.getPower() == SysUserModel.POWER_ORG){
			code =  commonBiz.getOrgCodeByUserId(id);
		}else{
			code = "'"+user.getCompanyCode()+"'";
		}
		JQGridResponseModel<SysUserModel> jQModel = sysUserDao.getUserList(requestModel,code);
		return jQModel;
	}
	
	/**
	 * 获取角色列表
	 * @return
	 * @return List<SysRoleModel>
	 * @author Tang
	 * @date 2016年7月4日 下午3:16:17
	 */
	public List<SysRoleModel> getRoleList(){
		int id = AuthManager.getCurrentAuthData().getId();
		SysUserModel user = sysUserDao.getUserByUserId(id);
		return sysUserDao.getRoleListByCompanyCode(user.getCompanyCode());
	}
	
	/**
	 * 获取树形组织结构
	 * @param userId
	 * @return
	 * @return List<TreeModel>
	 * @author Tang
	 * @date 2016年7月5日 上午11:42:46
	 */
	public List<TreeModel> getOrgInfoTree(int userId){
		SysUserModel user = sysUserDao.getUserByUserId(userId);
		 List<String> companyCodeList = new ArrayList<>();
		 String[] codes = user.getCompanyCode().split(",");
		 for (String string : codes) {
			companyCodeList.add(string.replace("'", ""));
		}
		List<SysOrgModel> orgList = sysUserDao.getOrgListByCompanyCode(companyCodeList);
		List<TreeModel> list = new ArrayList<TreeModel>();
		if(!orgList.isEmpty()){
			for (SysOrgModel org : orgList) {
				if(org.getParentOrgId() == 0){
					TreeModel tree = new TreeModel();
					tree.setId(org.getId());
					tree.setText(org.getName());
					changeOrgData(orgList, tree);
					list.add(tree);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 获取树形组织列表
	 * @param userId
	 * @return
	 * @return List<SysOrgModel>
	 * @author Tang
	 * @date 2016年7月12日 下午6:07:01
	 */
	public List<SysOrgModel> getOrgList(int userId){
		SysUserModel user = sysUserDao.getUserByUserId(userId);
		 List<String> companyCodeList = new ArrayList<>();
		 String[] codes = user.getCompanyCode().split(",");
		 for (String string : codes) {
			companyCodeList.add(string.replace("'", ""));
		}
		List<SysOrgModel> orgList = sysUserDao.getOrgListByCompanyCode(companyCodeList);
		return orgList;
	}
	
	/**
	 * 转化org列表数据为树形结构
	 * @param list
	 * @param tree
	 * @return void
	 * @author Tang
	 * @date 2016年7月5日 下午2:09:47
	 */
	public void changeOrgData(List<SysOrgModel> orgList,TreeModel tree){
		tree.setNodes(new ArrayList<TreeModel>());
		
		List<SysOrgModel> list = new ArrayList<SysOrgModel>();
		for (int i = 0;i< orgList.size();i++) {
			list.add(orgList.get(i));	
		}
		for (int i = list.size()-1; i >=0; i--) {
			SysOrgModel org = list.get(i);
			if(tree.getId() == org.getParentOrgId()){
					TreeModel childTree = new TreeModel();
					childTree.setText(org.getName());
					childTree.setId(org.getId());
					tree.getNodes().add(childTree);
					list.remove(i);
				
			}
		}
		if(list.size()>0){
			for (TreeModel treeModel : tree.getNodes()) {
				changeOrgData(list, treeModel);
			}
		}
	}
	
	
	/**
	 * 获取创建用户必要信息
	 * @param userId
	 * @return
	 * @return List<Map<String,Object>>
	 * @author Tang
	 * @date 2016年7月4日 下午3:22:19
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findCreateInfo(int userId){
		 SysUserModel user = (SysUserModel) sysUserDao.findObjectByPK(SysUserModel.class, userId);
		 if(user != null){
			 String companyCode = user.getCompanyCode();
			 Map<String, Object> map = new HashMap<String, Object>();
			 List<String> companyCodeList = new ArrayList<>();
			 String[] codes = companyCode.split(",");
			 for (String string : codes) {
				companyCodeList.add(string.replace("'", ""));
			 }
			 List<SysRoleModel> roleList = sysUserDao.getRoleListByCompanyCode(companyCode);
			 map.put("roleList", roleList);
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 list.add(map);
			 return list;
		 }
		return null;
	}
	
	/**
	 * 获取组织机构树
	 * @param companyCode
	 * @return
	 * @return List<SysOrgModel>
	 * @author Tang
	 * @date 2016年10月31日 下午2:52:34
	 */
	public List<SysOrgModel> getOrgTree(String companyCode){
		if(StringUtils.hasText(companyCode)){
			List<String> list = new ArrayList<>();
			list.add(companyCode);
			return sysUserDao.getOrgListByCompanyCode(list);
		}
		return null;
		
	}
	
	
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @return Boolean
	 * @author Tang
	 * @throws Exception 
	 * @date 2016年7月4日 下午2:13:12
	 */
	public Boolean addUser(SysUserModel user) throws Exception{
		if(StringUtils.hasText(user.getUserName()) && StringUtils.hasText(user.getName()) && StringUtils.hasText(user.getEmail()) && StringUtils.hasText(user.getPassword()) && StringUtils.hasText(user.getCompanyCode())  && user.getRoleId() != null && (user.getOrgIdArr() != null || user.getOrgId() != null)){
			user.setPassword(BizHelper.encodeStr("MD5", user.getPassword().trim()));
			if(user.getStatus() == null){
				user.setStatus((short) 0);
			}
			try {
				return sysUserDao.addUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public BooleanResultModel updUser(SysUserModel user){
		BooleanResultModel br = new BooleanResultModel();
		if(user.getId() != null || (StringUtils.hasText(user.getName()) && StringUtils.hasText(user.getEmail())  && StringUtils.hasText(user.getCompanyCode())  && (user.getOrgIdArr() != null) || user.getId() != null)){
			SysUserModel hasUser =sysUserDao.getSysUserByUserName(user.getUserName());
			if(hasUser!=null && hasUser.getId() != user.getId()){
				br.setMsg("用户名已被占用");
				br.setResult(false);
				return br;
			}
//			SearchExpression se = SearchExpression.forClass(SysRoleModel.class);
//			se.and(Terms.eq("id", user.getRoleId()));
//			SysRoleModel role = (SysRoleModel) sysUserDao.findObject(se);
			if(user.getRoleId()==2 || user.getRoleId()==8){
				for(int orgId : user.getOrgIdArr()){
					SearchExpression se = SearchExpression.forClass(SysOrgModel.class);
					se.and(Terms.eq("id", orgId));
					SysOrgModel org = (SysOrgModel) sysUserDao.findObject(se);
					if(org.getFarmId() == null || org.getFarmId() == 0){
						br.setResult(false);
						br.setMsg("场长或农场技术员的组织机构必须勾选一个农场");
						return br;
					}
				}
				
			}
			Map<String,Object> map = new LinkedCaseInsensitiveMap<Object>();
			map.put("companyCode", user.getCompanyCode());
			map.put("name", user.getName());
			map.put("userName", user.getUserName());
			map.put("email", user.getEmail());
			map.put("phone", user.getPhone());
			map.put("roleId", user.getRoleId());
			map.put("id", user.getId());
			map.put("power", user.getPower());
			Map<String,Object> param = new LinkedCaseInsensitiveMap<Object>();
			param.put("id", user.getId());
			sysUserDao.executeUpdate("delUserOrg", param);
			if(user.getStatus() == null){
				map.put("status", 0);
			}else{
				map.put("status", user.getStatus());
			}
			if(user.getOrgIdArr().length>1){
				for(int orgId : user.getOrgIdArr()){
					SysOrgUserModel org = new SysOrgUserModel();
					org.setOrgId(orgId);
					org.setUserId(user.getId());
					sysUserDao.insert(org);
				}
				br.setResult(sysUserDao.executeUpdate("updateUser", map)>0?true:false);
				return br;
//				WriteTranSession session = sysUserDao.getTransactionManager().openRWTran(false);
//				String sql = "INSERT INTO sys_org_user(Org_Id,User_Id) VALUES(:orgId,:userId)";
//				CompiledSQL cs = sysUserDao.getTransactionManager().getCompiledSQLBuilder().buildCompiledSQL(SysOrgUserModel.class,sql);
//				SQLBatcher batcher = session.createCompiledSQLBatcher(cs);
//				for (int orgId : user.getOrgIdArr()) {
//					Map<String,Object> params  = new LinkedCaseInsensitiveMap<Object>();
//					params.put("orgId", orgId);
//					params.put("userId", user.getId());
//					batcher.addNewBatchParams(params);
//				}
//				try {
//					batcher.executeBatch();
//					session.commit();
//					return count;
//				} catch (Exception e) {
//					e.printStackTrace();
//					throw new RuntimeException();
//				}finally{
//					session.close();
//				}
			}else{
				SysOrgUserModel org = new SysOrgUserModel();
				org.setOrgId(user.getOrgIdArr()[0]);
				org.setUserId(user.getId());
				sysUserDao.insert(org);
				br.setResult(sysUserDao.executeUpdate("updateUser", map)>0?true:false);
				return br;
			}
		}
		br.setResult(false);
		br.setMsg("用户信息不全，更新失败");
		return br;
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public int updUserPwd(SysUserModel user) throws Exception{
		if(user.getId() != null && StringUtils.hasText(user.getPassword())){
			user.setPassword(BizHelper.encodeStr("MD5", user.getPassword().trim()));
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("password", user.getPassword());
			map.put("id", user.getId());
			return sysUserDao.executeUpdate("updatePwd", map);
		}
		return 0;
	}
}
