package com.ec.facilitator.base.bal.fac;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.dal.fac.SupplierDao;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacProjectModel;
import com.ec.facilitator.base.model.fac.FacProjectScoreModel;
import com.ec.facilitator.base.model.fac.FacProjectScoreRelativeModel;
import com.ec.facilitator.base.model.fac.FacProjectTypeModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.model.fac.FacSupplierProjectRelativeModel;
import com.ec.facilitator.base.model.fac.JQGridProjectModel;
import com.ec.facilitator.base.model.fac.JQGridSupplierModel;
import com.ec.facilitator.base.util.WebConfig;

/**
 * 供应商业务逻辑层
 * @author 张荣英
 * @date 2017年4月18日 上午10:55:32
 */
@Component
public class SupplierBiz {
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	private ApplicationContext springContext;
	
	@Autowired
	private WebConfig webConfig;
	
	/**
	 * 查询供应商列表
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年4月18日 上午11:21:56
	 */
	public JQGridResponseModel<FacSupplierModel> getSupplierList(JQGridSupplierModel requestModel){
		return supplierDao.getSupplierList(requestModel.getName(),requestModel.getPage(), requestModel.getRows());
	}
	
	/**
	 * 根据ID查询供应商
	 * @param id
	 * @return
	 * @return FacSupplierModel
	 * @author 张荣英
	 * @date 2017年4月18日 上午11:23:40
	 */
	public FacSupplierModel getModelById(int id){
		FacSupplierModel supplier = (FacSupplierModel)supplierDao.findObjectByPK(FacSupplierModel.class, id);
		supplier.setLicenseImgs(formatImgs(supplier.getLicenseImgs()));
		supplier.setIdentityCardImgs(formatImgs(supplier.getIdentityCardImgs()));
		supplier.setCertificateImgs(formatImgs(supplier.getCertificateImgs()));
		return supplier;
	}
	
	/**
	 * 根据ID查询项目
	 * @param id
	 * @return
	 * @return FacProjectModel
	 * @author 张荣英
	 * @date 2017年4月25日 下午10:04:22
	 */
	public FacProjectModel getProjectById(int id){
		FacProjectModel project = (FacProjectModel)supplierDao.findObjectByPK(FacProjectModel.class, id);
		return project;
	}
	
	/**
	 * 保存供应商
	 * @param userId
	 * @param supplier
	 * @return
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @date 2017年4月23日 下午2:28:40
	 */
	public BooleanResultModel saveSupplier(int userId,FacSupplierModel supplier){
		BooleanResultModel result = new BooleanResultModel();
		if(supplier.getId() == 0){
			supplier.setCreateUser(userId);
			supplier.setCreateTime(new Date());
			supplierDao.insert(supplier);
		}else{
			FacSupplierModel facSupplierModel = (FacSupplierModel)supplierDao.findObjectByPK(FacSupplierModel.class, supplier.getId());
			supplier.setCreateUser(facSupplierModel.getCreateUser());
			supplier.setCreateTime(facSupplierModel.getCreateTime());
			supplierDao.update(supplier);
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 格式化图片路径
	 * @param imgs
	 * @return
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月23日 下午7:17:41
	 */
	public String formatImgs(String imgs) {
		return this.formatImgs(webConfig.getImgHost(), imgs);
	}
	
	/**
	 * 
	 * @param host
	 *            图片的主机地址
	 * @param imgs
	 *            需要格式化的图片相对路径
	 * @return 返回格式化后的图片绝对路径
	 */
	private String formatImgs(String host, String imgs) {
		if (host != null && host.length() > 0 && imgs != null) {
			String[] arrImg = imgs.split(",");
			String endImags = "";
			for (int i = 0; i < arrImg.length; i++) {
				arrImg[i] = host + arrImg[i];
				if (i == arrImg.length - 1) {
					endImags = endImags + arrImg[i];
				} else {
					endImags = endImags + arrImg[i] + ",";
				}
			}
			return endImags;
		}
		return imgs;
	}
	
	/**
	 * 查询项目List
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午10:13:28
	 */
	public JQGridResponseModel<FacProjectModel> getProjectList(JQGridProjectModel requestModel){
		return supplierDao.getProjectList(requestModel.getName(),requestModel.getProjectTypeId()==null?0:requestModel.getProjectTypeId(),requestModel.getPage(), requestModel.getRows());
	}
	
	/**
	 * 查询项目类型
	 * @return
	 * @return List<FacProjectTypeModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午9:56:05
	 */
	public List<FacProjectTypeModel> getProjectType(){
		return supplierDao.getProjectType();
	}
	
	/**
	 * 保存项目
	 * @param userId
	 * @param project
	 * @return
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @date 2017年4月25日 下午10:17:26
	 */
	public BooleanResultModel saveProject(int userId,FacProjectModel project){
		BooleanResultModel result = new BooleanResultModel();
		if(project.getId() == 0){
			project.setCreateUser(userId);
			project.setCreateTime(new Date());
			project.setStatus((short) 1);
			supplierDao.insert(project);
		}else{
			FacProjectModel projectModel = (FacProjectModel)supplierDao.findObjectByPK(FacProjectModel.class, project.getId());
			project.setCreateUser(projectModel.getCreateUser());
			project.setCreateTime(projectModel.getCreateTime());
			supplierDao.update(project);
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查询中标项目List
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年4月24日 下午10:13:28
	 */
	public JQGridResponseModel<FacProjectModel> getProjectBidList(JQGridProjectModel requestModel){
		return supplierDao.getProjectBidList(requestModel.getName(),requestModel.getSupplierName(),requestModel.getProjectTypeId()==null?0:requestModel.getProjectTypeId(),requestModel.getPage(), requestModel.getRows());
	}
	
	/**
	 * 查询可招标的项目
	 * @return
	 * @return List<FacProjectModel>
	 * @author 张荣英
	 * @date 2017年5月2日 下午9:01:19
	 */
	public List<FacProjectModel> getProjectBids(){
		return supplierDao.getProjectBids();
	}
	
	/**
	 * 查询项目评分指标
	 * @return
	 * @return List<FacProjectScoreModel>
	 * @author 张荣英
	 * @date 2017年5月12日 下午3:41:31
	 */
	public List<FacProjectScoreModel> getProjectScore(){
		return supplierDao.getProjectScore();
	}
	
	/**
	 * 招标
	 * @param projectId
	 * @param bidType
	 * @return
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @date 2017年5月3日 下午1:41:05
	 */
	public BooleanResultModel projectBid(int projectId,int bidType){
		BooleanResultModel result = new BooleanResultModel();
		List<FacSupplierModel> suppliers = supplierDao.getSupplierst();
		List<FacSupplierModel> newSuppliers = new ArrayList<FacSupplierModel>();
		if(bidType == 1){
			newSuppliers = createRandomList(suppliers, 1);
			
		}else if(bidType == 2){
			newSuppliers = createRandomList(createRandomList(suppliers, 2), 1);
		}
		FacSupplierProjectRelativeModel a = new FacSupplierProjectRelativeModel();
		a.setBidTime(new Date());
		a.setProjectId(projectId);
		a.setSupplierId(newSuppliers.get(0).getId());
		supplierDao.insert(a);
		FacProjectModel project = (FacProjectModel)supplierDao.findObjectByPK(FacProjectModel.class, projectId);
		project.setStatus((short) 2);
		supplierDao.update(project);
		result.setResult(true);
		result.setMsg(newSuppliers.get(0).getName());
		return result;
	}
	
	/**
	 * 从list中随机抽取元素 
	 * @param list
	 * @param n
	 * @return
	 * @return List<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年5月3日 下午1:37:29
	 */
	private List<FacSupplierModel> createRandomList(List<FacSupplierModel> list, int n) {  
        // TODO Auto-generated method stub  
        Map<Integer, Object> map = new HashMap<Integer, Object>();  
        List<FacSupplierModel> listNew = new ArrayList<FacSupplierModel>();  
        if(list.size()<=n){  
            return list;  
        }else{  
            while(map.size()<n){  
                int random = (int) (Math.random() * list.size());  
                if (!map.containsKey(random)) {  
                    map.put(random, "");  
                    listNew.add(list.get(random));  
                }  
            }  
            return listNew;  
        }  
    }
	
	/**
	 * 保存评分
	 * @param scoreList
	 * @return
	 * @return BooleanResultModel
	 * @author 张荣英
	 * @date 2017年5月31日 下午10:12:06
	 */
	public BooleanResultModel saveProjectScore(List<FacProjectScoreRelativeModel> scoreList){
		BigDecimal scoreSum = new BigDecimal(0);
		FacProjectModel project = supplierDao.getProjectById(scoreList.get(0).getProjectId());
		for(FacProjectScoreRelativeModel score : scoreList){
			scoreSum = scoreSum.add(score.getScore());
			supplierDao.insert(score);
		}
		
		project.setStatus((short) 5);
		supplierDao.update(project);
		FacSupplierModel supplier = (FacSupplierModel)supplierDao.findObjectByPK(FacSupplierModel.class, project.getSupplierId());
		supplier.setScoreLevel(scoreSum.divide(new BigDecimal(scoreList.size()), 2, BigDecimal.ROUND_HALF_UP));
		supplierDao.update(supplier);
		BooleanResultModel result = new BooleanResultModel();
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除项目
	 * @param projectIds
	 * @return
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年5月31日 下午11:43:02
	 */
	public Boolean delProject(String projectIds){
		return supplierDao.delProject(projectIds);
	}
	
	/**
	 * 删除供应商
	 * @param projectIds
	 * @return
	 * @return Boolean
	 * @author 张荣英
	 * @date 2017年5月31日 下午11:43:02
	 */
	public Boolean delSupplier(String supplierIds){
		return supplierDao.delSupplier(supplierIds);
	}
}
