package com.ec.facilitator.site.reset;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.fac.SupplierBiz;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.fac.FacProjectTypeModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.model.fac.JQGridProjectModel;
import com.ec.facilitator.base.model.fac.JQGridSupplierModel;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;

@RestController
public class SupplierResetController {	
	
		@Resource
		SupplierBiz supplierBiz;
	
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/supplier/list.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<FacSupplierModel> getSupplierList(@RequestBody JQGridSupplierModel requestModel) throws Exception {
			return supplierBiz.getSupplierList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/supplier/save.json", method = RequestMethod.POST)
		public BooleanResultModel saveSupplier(@RequestBody FacSupplierModel supplier) throws Exception {
			return supplierBiz.saveSupplier(AuthManager.getCurrentAuthData().getId(),supplier);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/list.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<FacProjectModel> getProjectList(@RequestBody JQGridProjectModel requestModel) throws Exception {
			return supplierBiz.getProjectList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/type.json", method = RequestMethod.GET)
		public List<FacProjectTypeModel> getProjectType() throws Exception {
			return supplierBiz.getProjectType();
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/save.json", method = RequestMethod.POST)
		public BooleanResultModel saveProject(@RequestBody FacProjectModel project) throws Exception {
			return supplierBiz.saveProject(AuthManager.getCurrentAuthData().getId(),project);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/bid/list.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<FacProjectModel> getProjectBidList(@RequestBody JQGridProjectModel requestModel) throws Exception {
			return supplierBiz.getProjectBidList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/bid.json", method = RequestMethod.GET)
		public List<FacProjectModel> getBidProjects() throws Exception {
			return supplierBiz.getProjectBids();
		}
}
