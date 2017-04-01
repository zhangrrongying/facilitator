package com.ec.facilitator.base.dal.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.guzz.jdbc.SQLBatcher;
import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.Terms;
import org.guzz.orm.sql.CompiledSQL;
import org.guzz.transaction.WriteTranSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysOrgModel;
import com.ec.facilitator.base.model.system.SysOrgUserModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysRoleUserModel;
import com.ec.facilitator.base.model.system.SysUserModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

/**
 * 系统用户及权限数据访问
 * @author 张荣英
 * @date 2016年6月29日 上午10:52:23
 */
@Component
public class SysUserDao extends SpringGuzzBaseDao {
	
	/**
	 * 根据邮箱查询用户
	 * @param mail
	 * @return
	 * @return SystemUserModel
	 * @author 张荣英
	 * @date 2016年6月27日 上午10:36:41
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
	 * @return List<SystemRoleModel>
	 * @author 张荣英
	 * @date 2016年6月27日 上午10:37:01
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getFuncsByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return (List<SysAuthFuncModel>)this.list("getFuncsByUserId", map);
	}
	
	/**
	 * 根据用户ID查询组织结构
	 * @param userId
	 * @return
	 * @return List<SysOrgModel>
	 * @author 张荣英
	 * @date 2016年7月5日 下午3:44:26
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrgModel> getOrgsByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return (List<SysOrgModel>)this.list("getOrgListByUserId", map);
	}
	
	/**
	 * 根据用户ID查询菜单
	 * @param userId
	 * @return
	 * @return List<SysAuthFuncModel>
	 * @author 张荣英
	 * @date 2016年6月27日 下午5:15:59
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getMenusByUserId(int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return (List<SysAuthFuncModel>)this.list("getMenusByUserId", map);
	}
	/**
	 * 分页查询用户列表
	 * @param page
	 * @param rows
	 * @return
	 * @return ListResultModel<SysUserModel>
	 * @author Tang
	 * @date 2016年6月30日 上午11:53:12
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<SysUserModel> getUserList(SysUserModel requestModel,String code){
		JQGridResponseModel<SysUserModel> userList = new JQGridResponseModel<SysUserModel>();
		int page = requestModel.getPage()<1?1:requestModel.getPage();
		int rows = requestModel.getRows();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		if(StringUtils.hasText(requestModel.getName())){
			map.put("name", requestModel.getName().trim());
		}
		if(StringUtils.hasText(requestModel.getUserName())){
			map.put("userName", requestModel.getUserName().trim());
		}
		if(StringUtils.hasText(requestModel.getOrgName())){
			map.put("orgName", requestModel.getOrgName().trim());
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
	 * @author Tang
	 * @date 2016年7月1日 下午3:58:20
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
	 * @param org
	 * @param role
	 * @return
	 * @return Boolean
	 * @author Tang
	 * @date 2016年7月7日 上午11:07:35
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public Boolean addUser(SysUserModel user) throws Exception{
			SysRoleUserModel role = new SysRoleUserModel();
			Integer id = (Integer)insert(user);
			role.setUserId(id);
			role.setRoleId(user.getRoleId());
			if(id != null && insert(role) !=null){
				SysOrgUserModel org = new SysOrgUserModel();
				
				if(user.getOrgIdArr().length>1){
					WriteTranSession session = this.getTransactionManager().openRWTran(false);
					String sql = "INSERT INTO sys_org_user(Org_Id,User_Id) VALUES(:orgId,:userId) ";
					CompiledSQL cs = this.getTransactionManager().getCompiledSQLBuilder().buildCompiledSQL(SysOrgUserModel.class,sql);
					SQLBatcher batcher = session.createCompiledSQLBatcher(cs);
					for (int orgId : user.getOrgIdArr()) {
						Map<String,Object> map  = new LinkedCaseInsensitiveMap<Object>();
						map.put("orgId", orgId);
						map.put("userId", id);
						batcher.addNewBatchParams(map);
					}
					try {
						batcher.executeBatch();
						session.commit();
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException();
					}finally{
						session.close();
					}
				}else{
					org.setUserId(id);
					org.setOrgId(user.getOrgIdArr()[0]);
					if( insert(org) != null){
						return true;
					}
				}
			}
			return false;
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param map
	 * @return
	 * @return int
	 * @author Tang
	 * @date 2016年7月19日 上午11:35:41
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.MANDATORY)
	public int updUser(SysUserModel user,Map<String,Object> map){
		
		return this.executeUpdate("updateUser", map);
		
	}
	
	/**
	 * 根据公司code查询角色列表
	 * @param CompanyCode
	 * @return
	 * @return List<SysRoleModel>
	 * @author Tang
	 * @date 2016年7月4日 下午3:30:41
	 */
	@SuppressWarnings("unchecked")
	public List<SysRoleModel> getRoleListByCompanyCode(String CompanyCode){
		SearchExpression se = SearchExpression.forClass(SysRoleModel.class);
		se.setCondition(Terms.eq("status", 1)).and(Terms.notNull("status"));
		return this.list(se);
		
	}
	
	/**
	 * 根据userId查询用户
	 * @param userId
	 * @return
	 * @return SysUserModel
	 * @author Tang
	 * @date 2016年7月4日 下午3:34:18
	 */
	public SysUserModel getUserByUserId(int userId){
//		SearchExpression se = SearchExpression.forClass(SysUserModel.class);
		SysUserModel user =  (SysUserModel) findObjectByPK(SysUserModel.class, userId);
//		se.and(Terms.eq("id", userId));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userId);
		map.put("companyCode", user.getCompanyCode());
		return (SysUserModel) findObject("findUserById",map);
		
	}
	
	/**
	 * 根据公司Code查询部门列表
	 * @param companyCode
	 * @return
	 * @return List<SysOrgModel>
	 * @author Tang
	 * @date 2016年7月4日 下午4:31:02
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrgModel> getOrgListByCompanyCode(List<String> companyCode){
		SearchExpression se = SearchExpression.forLoadAll(SysOrgModel.class);
		se.setCondition(Terms.in("companyCode", companyCode)).and(Terms.eq("status", 0));
		se.setOrderBy("rank asc");
		return list(se);
	}
	
}
