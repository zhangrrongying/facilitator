package com.ec.facilitator.base.dal.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.ec.facilitator.base.model.system.SysAuthFuncModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

@Repository
public class SysRoleDao extends SpringGuzzBaseDao{
	
	/**
	 * 更新角色
	 * @param map
	 * @return
	 * @return int
	 * @author Tang
	 * @date 2016年7月8日 上午9:35:32
	 */
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public int updateRole(Map<String,Object>map){
		return this.executeUpdate("updateRole", map);
	}
	
	/**
	 * 根据角色ID查询菜单权限
	 * @return
	 * @return List<SysAuthFuncModel>
	 * @author Tang
	 * @date 2016年7月8日 上午9:35:45
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthFuncModel> getRolePowerListById(int id){
		Map<String,Object> map = new LinkedCaseInsensitiveMap<Object>();
		map.put("id", id);
		return this.list("getRolePowerListById",map);
	}
}
