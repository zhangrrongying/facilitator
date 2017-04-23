package com.ec.facilitator.base.bal.fac;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.dal.fac.SupplierDao;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
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
}
