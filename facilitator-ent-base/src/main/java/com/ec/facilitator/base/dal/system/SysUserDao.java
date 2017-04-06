package com.ec.facilitator.base.dal.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.Terms;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysRoleUserModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

/**
 * 系统用户及权限数据访问
 * @author 张荣英
 * @date 2017年4月5日 下午5:38:30
 */
@Component
public class SysUserDao extends SpringGuzzBaseDao {
	
	/**
	 * 根据邮箱查询用户
	 * @param userName
	 * @return
	 * @return SysUserModel
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:38:46
	 */
	public SysUserModel getSysUserByUserName(String userName) {
		SearchExpression se = SearchExpression.forClass(SysUserModel.class);
		se.and(Terms.eq("userName", userName));
		se.and(Terms.eq("status", 1));
		return (SysUserModel) this.findObject(se);
	}
	
	/**
	 * 根据用户ID查询角色
	 * @param userId
	 * @return
	 * @return List<SysAuthFuncModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:39:12
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getFuncsByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return (List<SysAuthFuncModel>)this.list("getFuncsByUserId", map);
	}
	
	/**
	 * 根据用户ID查询菜单
	 * @param userId
	 * @return
	 * @return List<SysAuthFuncModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:39:39
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getMenusByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return (List<SysAuthFuncModel>)this.list("getMenusByUserId", map);
	}
	
	/**
	 * 分页查询用户列表
	 * @param requestModel
	 * @param code
	 * @return
	 * @return JQGridResponseModel<SysUserModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:39:50
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<SysUserModel> getUserList(SysUserModel requestModel){
		JQGridResponseModel<SysUserModel> userList = new JQGridResponseModel<SysUserModel>();
		int page = requestModel.getPage()<1?1:requestModel.getPage();
		int rows = requestModel.getRows();
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.hasText(requestModel.getName())){
			map.put("name", requestModel.getName().trim());
		}
		if(StringUtils.hasText(requestModel.getUserName())){
			map.put("userName", requestModel.getUserName().trim());
		}
		if(requestModel.getRoleId() != null){
			map.put("roleId", requestModel.getRoleId());
		}
		userList = (JQGridResponseModel<SysUserModel>) this.findObject("getUserCount", map);
		map.put("start", (page-1)*rows);
		map.put("end", rows);
		userList.setRows(this.list("findUserList", map));
		userList.setTotal((userList.getRecords()-1)/rows+1);
		userList.setRecords(userList.getRecords());
		userList.setPage(page);
		return userList;
	}
	
	/**
	 * 查询用户角色列表
	 * @return
	 * @return List<SysRoleModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:40:08
	 */
	@SuppressWarnings("unchecked")
	public List<SysRoleModel> getRoleList(){
		SearchExpression se = SearchExpression.forLoadAll(SysRoleModel.class);
		se.setCondition(Terms.eq("status", 1)).and(Terms.notNull("status"));
		return 	this.list(se);
	}
	
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年4月5日 下午9:27:09
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public Boolean addUser(SysUserModel user) throws Exception{
			SysRoleUserModel role = new SysRoleUserModel();
			Integer id = (Integer)insert(user);
			role.setUserId(id);
			role.setRoleId(user.getRoleId());
			insert(role);
			return true;
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param map
	 * @return
	 * @return int
	 * @author 张荣英
	 * @date 2017年4月5日 下午9:28:41
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.MANDATORY)
	public int updUser(SysUserModel user,Map<String,Object> map){
		return this.executeUpdate("updateUser", map);
	}
}
