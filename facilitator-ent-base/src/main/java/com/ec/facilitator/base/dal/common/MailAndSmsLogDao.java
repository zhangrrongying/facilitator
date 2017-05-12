package com.ec.facilitator.base.dal.common;

import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.Terms;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.model.common.MailAndSmsLogModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

@Component
public class MailAndSmsLogDao extends SpringGuzzBaseDao {
	
	public String insertLog(MailAndSmsLogModel model){
		return (String)this.insert(model);
	}
	
	public boolean updateLog(MailAndSmsLogModel model){
		return this.update(model);
	}
	
	public MailAndSmsLogModel getLog(String id){
		SearchExpression se = SearchExpression.forClass(MailAndSmsLogModel.class);
	  	se.and(Terms.eq("logId", id)) ;
	  	return (MailAndSmsLogModel)this.findObject(se);
	}

}
