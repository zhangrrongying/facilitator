package com.ec.facilitator.base.bal.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.guzz.jdbc.SQLBatcher;
import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.sql.CompiledSQL;
import org.guzz.transaction.WriteTranSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

import com.ec.facilitator.base.dal.system.SysRoleDao;
import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.model.system.SysRoleFuncModel;
import com.ec.facilitator.base.model.system.SysRoleModel;
import com.ec.facilitator.base.model.system.SysUserModel;

/**
 * 角色业务处理
 * @author 张荣英
 * @date 2017年4月5日 下午5:37:49
 */
@Component
public class SysRoleBiz {
		
	@Resource
	SysRoleDao sysRoleDao;
	@Resource
	SysUserDao sysUserDao;
	
	/**
	 * 获取角色列表
	 * @param role
	 * @return
	 * @return JQGridResponseModel<SysRoleModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:35:09
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<SysRoleModel> roleList(SysRoleModel role){
		Map<String,Object> map = new LinkedCaseInsensitiveMap<Object>();
		int page = role.getPage()<1?1:role.getPage();
		int rows = role.getRows();
		if(StringUtils.hasText(role.getName())){
			map.put("roleName", role.getName().trim());
		}
		map.put("start", (page-1)*rows);
		map.put("end", rows);
		JQGridResponseModel<SysRoleModel> rep = new JQGridResponseModel<SysRoleModel>();
		rep = (JQGridResponseModel<SysRoleModel>) sysRoleDao.findObject("getRoleCount", map);
		rep.setRows(sysRoleDao.list("getRoleList", map));
		rep.setTotal((rep.getRecords()-1)/rows+1);
		rep.setPage(page);
		return rep;
	}
	
	/**
	 * 创建角色
	 * @param role
	 * @param userId
	 * @return
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:35:21
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public Boolean createRole(SysRoleModel role,int userId){
		if(role != null && StringUtils.hasText(role.getName())){
			SysUserModel user = sysUserDao.getUserByUserId(userId);
			role.setCompanyCode(user.getCompanyCode());
			role.setCreateDate(new Date());
			if(role.getStatus() == null){
				role.setStatus((short) 0);
			}
			if(sysRoleDao.insert(role) != null){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:35:38
	 */
	public Boolean updateRole(SysRoleModel role){
		if(role.getId() != null &&  StringUtils.hasText(role.getName())){
			Map<String,Object> map = new LinkedCaseInsensitiveMap<Object>();
			map.put("id", role.getId());
			map.put("name", role.getName());
			if(role.getDescription() != null){
				map.put("description", role.getDescription());
			}else{
				map.put("description", "");
			}
			if(role.getStatus() == null){
				role.setStatus((short) 0);
			}
			map.put("status", role.getStatus());
			
			if(sysRoleDao.updateRole(map)>0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 全局菜单权限列表
	 * @return
	 * @return List<SysAuthFuncModel>
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:35:51
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getPower(){
		SearchExpression se = SearchExpression.forLoadAll(SysAuthFuncModel.class);
		se.setOrderBy("rank asc");
		SysAuthFuncModel func = new SysAuthFuncModel();
		func.setId(0);
		func.setName("菜单");
		func.setParentKeyId(0);
		func.setType((byte) 1);
		List<SysAuthFuncModel> list = sysRoleDao.list(se);
		list.add(func);
		return list;
		
	}
	
	public List<SysAuthFuncModel> RolePower(Integer id){
		if(id != null){
			return sysRoleDao.getRolePowerListById(id);
		}
		return null;
	}
	
	/**
	 * 角色权限修改
	 * @param id
	 * @param param
	 * @return
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:36:13
	 */
	public Boolean editRolePower(Integer id,int[] param){
		if( id != null){
				Map<String,Object> roleId = new LinkedCaseInsensitiveMap<>();
				roleId.put("id", id);
				sysRoleDao.executeUpdate("delRolePower", roleId);
				if(param.length>0){
					WriteTranSession session = sysRoleDao.getTransactionManager().openRWTran(false);
					String sql = "INSERT INTO sys_role_func(Role_Id,Func_Id) VALUES (:roleId,:funcId) ";
					CompiledSQL cs = sysRoleDao.getTransactionManager().getCompiledSQLBuilder().buildCompiledSQL(SysRoleFuncModel.class,sql);
					SQLBatcher batcher = session.createCompiledSQLBatcher(cs);
					for (int funcId : param) {
						Map<String,Object> map  = new LinkedCaseInsensitiveMap<Object>();
						map.put("roleId", id);
						map.put("funcId", funcId);
						batcher.addNewBatchParams(map);
						
					}
					try {
						batcher.executeBatch();
						session.commit();
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("批量新增失败："+e.getMessage());
					}finally{
						session.close();
					}
				}
				return true;
		}
		return false;
	}
}
