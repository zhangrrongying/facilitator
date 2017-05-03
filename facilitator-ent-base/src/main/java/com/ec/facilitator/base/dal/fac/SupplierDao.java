package com.ec.facilitator.base.dal.fac;

import java.util.HashMap;
import java.util.List;

import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.Terms;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.fac.FacProjectTypeModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

/**
 * 供应商数据访问层
 * @author 张荣英
 * @date 2017年4月18日 上午11:00:22
 */
@Component
public class SupplierDao extends SpringGuzzBaseDao {
	
	/**
	 * 查询供应商
	 * @param name
	 * @param page
	 * @param limit
	 * @return
	 * @return JQGridResponseModel<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午9:54:16
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<FacSupplierModel> getSupplierList(String name,int page,int limit){
		JQGridResponseModel<FacSupplierModel> result = new JQGridResponseModel<FacSupplierModel>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(page == 0){
			page = page + 1;
		}
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		params.put("name", name);
		int totalSize = (int)this.findCell00("queryBkdSupplierSize", params, "int");
		List<FacSupplierModel> supplierList = this.list("queryBkdSuppliers",params);
		result.setRecords(totalSize);
		result.setRows(supplierList);
		result.setPage(page);
		result.setTotal(totalSize%limit==0?totalSize/limit:totalSize/limit+1);
		return result;
	} 
	
	/**
	 * 查询项目类型
	 * @return
	 * @return List<FacProjectTypeModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午9:55:32
	 */
	@SuppressWarnings("unchecked")
	public List<FacProjectTypeModel> getProjectType(){
		SearchExpression se = SearchExpression.forLoadAll(FacProjectTypeModel.class);
		return this.list(se);
	}
	
	/**
	 * 查询项目
	 * @param name
	 * @param projectTypeId
	 * @param page
	 * @param limit
	 * @return
	 * @return JQGridResponseModel<FacProjectModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午10:19:47
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<FacProjectModel> getProjectList(String name,int projectTypeId,int page,int limit){
		JQGridResponseModel<FacProjectModel> result = new JQGridResponseModel<FacProjectModel>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(page == 0){
			page = page + 1;
		}
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		params.put("name", name);
		params.put("projectTypeId", projectTypeId);
		int totalSize = (int)this.findCell00("queryBkdProjectSize", params, "int");
		List<FacProjectModel> supplierList = this.list("queryBkdProjects",params);
		result.setRecords(totalSize);
		result.setRows(supplierList);
		result.setPage(page);
		result.setTotal(totalSize%limit==0?totalSize/limit:totalSize/limit+1);
		return result;
	} 
	
	/**
	 * 查询中标项目
	 * @param name
	 * @param projectTypeId
	 * @param page
	 * @param limit
	 * @return
	 * @return JQGridResponseModel<FacProjectModel>
	 * @author 张荣英
	 * @date 2017年4月26日 下午9:11:00
	 */
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<FacProjectModel> getProjectBidList(String name,String supplierName,int projectTypeId,int page,int limit){
		JQGridResponseModel<FacProjectModel> result = new JQGridResponseModel<FacProjectModel>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(page == 0){
			page = page + 1;
		}
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		params.put("name", name);
		params.put("supplierName", supplierName);
		params.put("projectTypeId", projectTypeId);
		int totalSize = (int)this.findCell00("queryBkdProjectBidSize", params, "int");
		List<FacProjectModel> supplierList = this.list("queryBkdProjectBids",params);
		result.setRecords(totalSize);
		result.setRows(supplierList);
		result.setPage(page);
		result.setTotal(totalSize%limit==0?totalSize/limit:totalSize/limit+1);
		return result;
	} 
	
	/**
	 * 查询可招标的项目
	 * @return
	 * @return List<FacProjectModel>
	 * @author 张荣英
	 * @date 2017年5月2日 下午9:00:17
	 */
	@SuppressWarnings("unchecked")
	public List<FacProjectModel> getProjectBids(){
		HashMap<String, Object> params = new HashMap<String, Object>();
		return this.list("getProjectBids",params);
	}
	
	/**
	 * 查询供应商
	 * @return
	 * @return List<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年5月3日 下午1:35:35
	 */
	@SuppressWarnings("unchecked")
	public List<FacSupplierModel> getSupplierst(){
		SearchExpression se = SearchExpression.forLoadAll(FacSupplierModel.class);
		return this.list(se);
	}
}
