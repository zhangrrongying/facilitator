package com.ec.facilitator.site.reset;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.fac.SupplierBiz;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacProjectBidRequestModel;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.fac.FacProjectScoreModel;
import com.ec.facilitator.base.model.fac.FacProjectScoreRelativeModel;
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
		@RequestMapping(value = "/project/update_status.json", method = RequestMethod.POST)
		public BooleanResultModel updateProject(@RequestParam("id") int id) throws Exception {
			return supplierBiz.updateProject(AuthManager.getCurrentAuthData().getId(),id);
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
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/bid.json", method = RequestMethod.POST)
		public BooleanResultModel bidProjects(@RequestBody FacProjectBidRequestModel requestModel) throws Exception {
			return supplierBiz.projectBid(requestModel.getProjectId(),requestModel.getBidType());
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/score.json", method = RequestMethod.GET)
		public List<FacProjectScoreModel> getProjectScore() throws Exception {
			return supplierBiz.getProjectScore();
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/project/score/save.json", method = RequestMethod.POST)
		public BooleanResultModel saveProjectScore(@RequestBody List<FacProjectScoreRelativeModel> scoreList) throws Exception {
			return supplierBiz.saveProjectScore(scoreList);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/project/del.json",method=RequestMethod.POST)
		public BooleanResultModel delProject(@RequestParam("projectIds") String projectIds){
			BooleanResultModel br = new BooleanResultModel();
			br.setResult( supplierBiz.delProject(projectIds));
			return br;
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value="/supplier/del.json",method=RequestMethod.POST)
		public BooleanResultModel delSupplier(@RequestParam("supplierIds") String supplierIds){
			BooleanResultModel br = new BooleanResultModel();
			br.setResult( supplierBiz.delSupplier(supplierIds));
			return br;
			
		}
}
